/*
 * Created on 13 May 2017 ( Time 15:44:18 )
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
public class Questionnaire implements Serializable {

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
    @Size( min = 1, max = 255 )
    private String title;

    @NotNull
    @Size( min = 1, max = 50 )
    private String version;

    @NotNull
    private Integer questionNumber;


    private Integer selfDiagnosisField;


    private Short type;


    private Short isShow;


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
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setVersion( String version ) {
        this.version = version;
    }
    public String getVersion() {
        return this.version;
    }

    public void setQuestionNumber( Integer questionNumber ) {
        this.questionNumber = questionNumber;
    }
    public Integer getQuestionNumber() {
        return this.questionNumber;
    }

    public void setSelfDiagnosisField( Integer selfDiagnosisField ) {
        this.selfDiagnosisField = selfDiagnosisField;
    }
    public Integer getSelfDiagnosisField() {
        return this.selfDiagnosisField;
    }

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setIsShow( Short isShow ) {
        this.isShow = isShow;
    }
    public Short getIsShow() {
        return this.isShow;
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
        sb.append(title);
        sb.append("|");
        sb.append(version);
        sb.append("|");
        sb.append(questionNumber);
        sb.append("|");
        sb.append(selfDiagnosisField);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(isShow);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
