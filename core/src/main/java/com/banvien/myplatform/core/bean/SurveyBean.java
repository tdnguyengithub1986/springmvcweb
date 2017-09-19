package com.banvien.myplatform.core.bean;

import com.banvien.myplatform.core.domain.Survey;

import java.util.Date;

public class SurveyBean extends AbstractBean<Survey> {

    /**
     *
     */
    private static final long serialVersionUID = 5172852127241583233L;

    public SurveyBean(){
        this.pojo = new Survey();
    }

    private Date createdDateFrom;

    private Date createdDateTo;

    public Date getCreatedDateFrom() {
        return createdDateFrom;
    }
    public void setCreatedDateFrom(Date createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }
    public Date getCreatedDateTo() {
        return createdDateTo;
    }
    public void setCreatedDateTo(Date createdDateTo) {
        this.createdDateTo = createdDateTo;
    }


}
