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
public class UserCertificateTemp implements Serializable {

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
    private Integer userId;

    @Size( max = 300 )
    private String name;

    @Size( max = 300 )
    private String fileUrl;

    @Size( max = 300 )
    private String fileName;


    private Date createDate;


    private Date updateDate;


    private Short type;

    @Size( max = 300 )
    private String author;


    private Date issuedDate;



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

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
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

    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }

    public void setIssuedDate( Date issuedDate ) {
        this.issuedDate = issuedDate;
    }
    public Date getIssuedDate() {
        return this.issuedDate;
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
        sb.append(userId);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(fileUrl);
        sb.append("|");
        sb.append(fileName);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(author);
        sb.append("|");
        sb.append(issuedDate);
        return sb.toString(); 
    } 


}
