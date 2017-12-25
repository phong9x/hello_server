/*
 * Created on 21 Jun 2017 ( Time 15:54:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@DynamicInsert
@DynamicUpdate
public class UserQuestionaire implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer userId;


    private Integer counselorId;


    private Integer questionaireId;


    private Integer counselingSessionId;


    private Float point;


    private Integer selfDiagnosisResultsId;

    @Size( max = 500 )
    private String comment;

    @NotNull
    private Short recommend;


    private Short type;


    private Date updateDate;

    @Size( max = 50 )
    private String osName;


    private Date createDate;



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
    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setCounselorId( Integer counselorId ) {
        this.counselorId = counselorId;
    }
    public Integer getCounselorId() {
        return this.counselorId;
    }

    public void setQuestionaireId( Integer questionaireId ) {
        this.questionaireId = questionaireId;
    }
    public Integer getQuestionaireId() {
        return this.questionaireId;
    }

    public void setCounselingSessionId( Integer counselingSessionId ) {
        this.counselingSessionId = counselingSessionId;
    }
    public Integer getCounselingSessionId() {
        return this.counselingSessionId;
    }

    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    public void setSelfDiagnosisResultsId( Integer selfDiagnosisResultsId ) {
        this.selfDiagnosisResultsId = selfDiagnosisResultsId;
    }
    public Integer getSelfDiagnosisResultsId() {
        return this.selfDiagnosisResultsId;
    }

    public void setComment( String comment ) {
        this.comment = comment;
    }
    public String getComment() {
        return this.comment;
    }

    public void setRecommend( Short recommend ) {
        this.recommend = recommend;
    }
    public Short getRecommend() {
        return this.recommend;
    }

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setOsName( String osName ) {
        this.osName = osName;
    }
    public String getOsName() {
        return this.osName;
    }

    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(userId);
        sb.append("|");
        sb.append(counselorId);
        sb.append("|");
        sb.append(questionaireId);
        sb.append("|");
        sb.append(counselingSessionId);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(selfDiagnosisResultsId);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(recommend);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(osName);
        sb.append("|");
        sb.append(createDate);
        return sb.toString(); 
    } 


}
