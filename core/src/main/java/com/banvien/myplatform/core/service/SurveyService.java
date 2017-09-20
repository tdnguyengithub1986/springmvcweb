package com.banvien.myplatform.core.service;

import com.banvien.myplatform.core.bean.SurveyBean;
import com.banvien.myplatform.core.domain.Survey;
import com.banvien.myplatform.core.exception.DuplicateException;
import com.banvien.myplatform.core.exception.ObjectNotFoundException;

public interface SurveyService extends GenericService<Survey, Integer>{
    public Object[] search(SurveyBean searchBean);

    Survey findBySurveyName(String surveyName) throws ObjectNotFoundException;

    void updateItem(SurveyBean surveyBean) throws ObjectNotFoundException, DuplicateException;

    void addItem(SurveyBean surveyBean) throws DuplicateException;

    Integer deleteItems(String[] checkList) throws ObjectNotFoundException;
}
