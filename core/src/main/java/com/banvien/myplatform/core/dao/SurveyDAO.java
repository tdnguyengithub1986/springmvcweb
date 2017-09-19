package com.banvien.myplatform.core.dao;

import com.banvien.myplatform.core.domain.Survey;

import java.util.Date;

public interface SurveyDAO extends GenericDAO<Survey,Integer> {
    public Object[] search(Byte status, Date createdDateFrom,
                           Date createdDateTo, Integer firstItem, Integer maxItems, String sortExpression, String sortDirection);
}
