package com.banvien.myplatform.web.validator.admin;

import com.banvien.myplatform.core.bean.SurveyBean;
import com.banvien.myplatform.core.domain.Survey;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.service.SurveyService;
import com.banvien.myplatform.web.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * <p>Validator for Survey manipulation</p>
 * <p>The Origin based code was generated at Sat Sep 29 11:27:04 ICT 2012</p>
 *
 * Credit to @author Portal Generatior v1.1 / Generate a complete Spring/Hibernate and Spring MVC webapp
 */
@Component
public class SurveyValidator extends ApplicationObjectSupport implements Validator {
    @Autowired
    private SurveyService surveyService;

    public boolean supports(Class<?> aClass)
    {
        return SurveyBean.class.isAssignableFrom(aClass);
    }
    public void validate(Object o, Errors errors) {
        SurveyBean cmd = (SurveyBean)o;
        trimingFields(cmd);
        validateRequiredValues(cmd, errors);
        validateDuplicate(cmd, errors);

    }
    private void validateRequiredValues(SurveyBean cmd, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "pojo.status", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.status")}, "non-empty value required.");
        ValidationUtils.rejectIfEmpty(errors, "pojo.surveyName", "errors.required", new String[] {this.getMessageSourceAccessor().getMessage("label.survey")}, "non-empty value required.");

    }

    private void trimingFields(SurveyBean cmd) {
        if(StringUtils.isNotEmpty(cmd.getPojo().getSurveyName())) {
            cmd.getPojo().setSurveyName(cmd.getPojo().getSurveyName().trim());
        }
    }

    private void validateDuplicate(SurveyBean cmd, Errors errors) {
        //Username
        if(StringUtils.isNotBlank(cmd.getPojo().getSurveyName())) {
            try {
                Survey survey = this.surveyService.findBySurveyName(cmd.getPojo().getSurveyName());
                if(survey != null) {
                    if(cmd.getPojo().getSurveyID() != null &&
                            !survey.getSurveyID().equals(cmd.getPojo().getSurveyID()))
                        errors.rejectValue("pojo.surveyName", "error.duplicated", new String[] {this.getMessageSourceAccessor().getMessage("admin.survey.form.surveyname")}, "Value has been chosen.");
                }
            } catch (ObjectNotFoundException ex) {
                // true
            }
        }
    }
}
