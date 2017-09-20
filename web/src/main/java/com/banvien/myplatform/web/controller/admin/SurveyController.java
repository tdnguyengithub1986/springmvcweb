package com.banvien.myplatform.web.controller.admin;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.bean.SurveyBean;
import com.banvien.myplatform.core.domain.Survey;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.SurveyService;
import com.banvien.myplatform.web.editor.CustomDateEditor;
import com.banvien.myplatform.web.util.RequestUtil;
import com.banvien.myplatform.web.validator.admin.SurveyValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class SurveyController extends ApplicationObjectSupport {
    private transient final Log log = LogFactory.getLog(getClass());

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyValidator surveyValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
    }

    @RequestMapping(value="/admin/survey/edit.html", method=RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute(Constants.FORM_MODEL_KEY) SurveyBean surveyBean, BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView("/admin/survey/edit");
        Survey pojo = surveyBean.getPojo();
        String crudaction = surveyBean.getCrudaction();
        if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.ACTION_INSERT_UPDATE)) {
            try {
                surveyValidator.validate(surveyBean, bindingResult);
                if(!bindingResult.hasErrors()) {
                    if(pojo.getSurveyID() != null && pojo.getSurveyID() > 0) {
                        surveyService.updateItem(surveyBean);
                        mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.update.successful"));
                    } else {
                        surveyService.addItem(surveyBean);
                        mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.add.successful"));
                    }

                    mav.addObject("success", true);

                }
            }catch (ObjectNotFoundException oe) {
                logger.error(oe.getMessage(), oe);
                mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.exception.keynotfound"));
            }catch (DuplicateException de) {
                logger.error(de.getMessage(), de);
                mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.exception.duplicate"));
            }catch(Exception e) {
                logger.error(e.getMessage(), e);
                mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("general.exception.msg"));
            }
        }
        mav.addObject(Constants.FORM_MODEL_KEY, surveyBean);
        return mav;
    }

    @RequestMapping(value="/admin/survey/edit.html", method=RequestMethod.GET)
    public ModelAndView edit(SurveyBean surveyBean) {
        ModelAndView mav = new ModelAndView("/admin/survey/edit");
        if(surveyBean.getPojo().getSurveyID() != null && surveyBean.getPojo().getSurveyID() > 0) {
            try {
                Survey itemObj = surveyService.findById(surveyBean.getPojo().getSurveyID());

                surveyBean.setPojo(itemObj);
            }
            catch (Exception e) {
                logger.error("Could not found the survey " + surveyBean.getPojo().getSurveyID(), e);
            }
        }

        mav.addObject(Constants.FORM_MODEL_KEY, surveyBean);
        return mav;
    }
    @RequestMapping({"/admin/survey/list.html"})
    public ModelAndView list(SurveyBean bean, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/admin/survey/list");
        if(StringUtils.isNotBlank(bean.getCrudaction()) && bean.getCrudaction().equals(Constants.ACTION_DELETE)) {
            Integer totalDeleted = 0;
            try {
                totalDeleted = surveyService.deleteItems(bean.getCheckList());
                mav.addObject("totalDeleted", totalDeleted);
            }catch (Exception e) {
                log.error(e.getMessage());
                mav.addObject(Constants.MESSAGE_RESPONSE_MODEL_KEY, getMessageSourceAccessor().getMessage("database.multipledelete.exception"));
            }
        }
        executeSearch(bean, request);
        mav.addObject(Constants.LIST_MODEL_KEY, bean);
        return mav;
    }

    private void executeSearch(SurveyBean bean, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, bean);

        Object[] results = surveyService.search(bean);
        bean.setListResult((List<Survey>)results[1]);
        bean.setTotalItems(Integer.valueOf(results[0].toString()));
    }
}
