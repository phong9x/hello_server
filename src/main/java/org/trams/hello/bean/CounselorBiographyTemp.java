/*
 * Created on 29 Apr 2017 ( Time 10:52:30 )
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
public class CounselorBiographyTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer counselorTempId;

    @NotNull
    private Integer counselorId;

    @NotNull
    @Size( min = 1, max = 500 )
    private String content;

    @NotNull
    @Size( min = 1, max = 500 )
    private String fileUrl;

    @NotNull
    @Size( min = 1, max = 500 )
    private String fileName;

    @NotNull
    private Date createDate;

    @NotNull
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
    public void setCounselorTempId( Integer counselorTempId ) {
        this.counselorTempId = counselorTempId;
    }
    public Integer getCounselorTempId() {
        return this.counselorTempId;
    }

    public void setCounselorId( Integer counselorId ) {
        this.counselorId = counselorId;
    }
    public Integer getCounselorId() {
        return this.counselorId;
    }

    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setFileUrl( String fileUrl ) {
        this.fileUrl = fileUrl;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
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
        sb.append(counselorTempId);
        sb.append("|");
        sb.append(counselorId);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(fileUrl);
        sb.append("|");
        sb.append(fileName);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
