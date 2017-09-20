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

    private Date startedDateFrom;

    private Date startedDateTo;

    private Date startedDate;
    private Date endedDate;

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    public Date getStartedDateFrom() {
        return startedDateFrom;
    }
    public void setStartedDateFrom(Date startedDateFrom)
    {
        this.startedDateFrom = startedDateFrom;
    }
    public Date getStartedDateTo()
    {
        return startedDateTo;
    }
    public void setStartedDateTo(Date startedDateTo)
    {
        this.startedDateTo = startedDateTo;
    }


}
