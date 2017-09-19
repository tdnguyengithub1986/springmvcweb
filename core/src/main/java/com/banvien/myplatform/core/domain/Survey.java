package com.banvien.myplatform.core.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Pojo mapping TABLE survey</p>
 * <p></p>
 *
 * <p>Generated at date</p>
 * @author Portal Generatior v1.1 / Hibernate pojos and xml mapping files.
 *
 */
@Table(name = "Survey")
@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Survey implements Serializable {

    /**
     * Attribute surveyID.
     */
    @Column(name = "surveyID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer surveyID;

    /**
     * Attribute surveyName.
     */
    private String surveyName;

    /**
     * Attribute commencing, onGoing, completed.
     */
    private Byte status;

    /**
     * Attribute createdDate.
     */
    private Date createdDate;

    /**
     * Attribute modifiedDate.
     */
    private Date modifiedDate;

    private String modifiedBy;

    public Survey(){}

    public Survey(Integer surveyID){this.surveyID = surveyID;}

    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
