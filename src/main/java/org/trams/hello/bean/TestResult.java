/*
 * Created on 21 Apr 2017 ( Time 14:57:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@DynamicInsert
@DynamicUpdate
public class TestResult implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Integer questionId;


    private Integer answerId;


    private Integer selfDiagnosisAnswerId;


    private Float point;

    @NotNull
    private Integer userQuestionaireId;


    private Date createDate;


    private Date updateDate;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setQuestionId( Integer questionId ) {
        this.questionId = questionId;
    }
    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setAnswerId( Integer answerId ) {
        this.answerId = answerId;
    }
    public Integer getAnswerId() {
        return this.answerId;
    }

    public void setSelfDiagnosisAnswerId( Integer selfDiagnosisAnswerId ) {
        this.selfDiagnosisAnswerId = selfDiagnosisAnswerId;
    }
    public Integer getSelfDiagnosisAnswerId() {
        return this.selfDiagnosisAnswerId;
    }

    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    public void setUserQuestionaireId( Integer userQuestionaireId ) {
        this.userQuestionaireId = userQuestionaireId;
    }
    public Integer getUserQuestionaireId() {
        return this.userQuestionaireId;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(questionId);
        sb.append("|");
        sb.append(answerId);
        sb.append("|");
        sb.append(selfDiagnosisAnswerId);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(userQuestionaireId);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
