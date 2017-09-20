package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.Constants;
import com.banvien.myplatform.core.bean.SurveyBean;
import com.banvien.myplatform.core.dao.GenericDAO;
import com.banvien.myplatform.core.dao.SurveyDAO;
import com.banvien.myplatform.core.domain.Survey;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;
import com.banvien.myplatform.core.utils.DesEncrypterUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Timestamp;
import java.util.Date;

public class SurveyServiceImpl extends GenericServiceImpl<Survey, Integer> implements SurveyService {

    protected final Log logger = LogFactory.getLog(getClass());
    private SurveyDAO surveyDAO;

    public void setSurveyDAO (SurveyDAO surveyDAO)
    {
        this.surveyDAO = surveyDAO;
    }
    @Override
    protected GenericDAO<Survey, Integer> getGenericDAO()
    {
        return surveyDAO;
    }

    public Object[] search(SurveyBean searchBean) {
        return surveyDAO.search(searchBean.getPojo().getSurveyName(), searchBean.getPojo().getStatus(), searchBean.getStartedDateFrom(), searchBean.getStartedDateTo(), searchBean.getFirstItem(), searchBean.getMaxPageItems(), searchBean.getSortExpression(), searchBean.getSortDirection());
    }

    @Override
    public Survey findBySurveyName(String surveyName) throws ObjectNotFoundException {
        Survey res = surveyDAO.findEqualUnique("surveyName", surveyName);
        if (res == null) {
            throw new ObjectNotFoundException("Not found survey with survey name " + surveyName);
        }
        return res;
    }
    @Override
    public void updateItem(SurveyBean surveyBean) throws ObjectNotFoundException, DuplicateException {
        Survey dbItem = surveyDAO.findByIdNoAutoCommit(surveyBean.getPojo().getSurveyID());
        if (dbItem == null) {
            throw new ObjectNotFoundException("Not found survey " + surveyBean.getPojo().getSurveyID());
        }
        Survey pojo = surveyBean.getPojo();
        dbItem.setSurveyName(pojo.getSurveyName());
        dbItem.setStatus(pojo.getStatus());
        dbItem.setStartedDate(pojo.getStartedDate());
        dbItem.setEndedDate(pojo.getEndedDate());

        surveyDAO.update(dbItem);
    }

    @Override
    public void addItem(SurveyBean surveyBean) throws DuplicateException {
        Survey pojo = surveyBean.getPojo();
        surveyDAO.save(pojo);
        surveyBean.setPojo(pojo);
    }

    @Override
    public Integer deleteItems(String[] checkList) throws ObjectNotFoundException{
        Integer res = 0;
        if (checkList != null && checkList.length > 0) {
            res = checkList.length;
            for (String id : checkList) {
                Survey dbItem = surveyDAO.findByIdNoAutoCommit(Integer.valueOf(id));
                if (dbItem == null) {
                    throw new ObjectNotFoundException("Not found survey " + id);
                }
                dbItem.setStatus(Constants.SURVEY_DELETED);
                surveyDAO.update(dbItem);
            }
        }
        return res;
    }
}
