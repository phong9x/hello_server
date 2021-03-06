/*
 * Created on 29 Apr 2017 ( Time 10:52:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Persistent class for entity stored in table "user_certificate_temp"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="user_certificate_temp", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UserCertificateTempEntity.countAll", query="SELECT COUNT(x) FROM UserCertificateTempEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class UserCertificateTempEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Integer    id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="counselor_temp_id")
    private Integer    counselorTempId ;

    @Column(name="user_id")
    private Integer    userId       ;

    @Column(name="name", length=300)
    private String     name         ;

    @Column(name="file_url", length=300)
    private String     fileUrl      ;

    @Column(name="file_name", length=300)
    private String     fileName     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Column(name="type")
    private Short      type         ;

    @Column(name="author", length=300)
    private String     author       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="issued_date")
    private Date       issuedDate   ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UserCertificateTempEntity() {
		super();
    }
    
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
    //--- DATABASE MAPPING : counselor_temp_id ( INT ) 
    public void setCounselorTempId( Integer counselorTempId ) {
        this.counselorTempId = counselorTempId;
    }
    public Integer getCounselorTempId() {
        return this.counselorTempId;
    }

    //--- DATABASE MAPPING : user_id ( INT ) 
    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : file_url ( VARCHAR ) 
    public void setFileUrl( String fileUrl ) {
        this.fileUrl = fileUrl;
    }
    public String getFileUrl() {
        return this.fileUrl;
    }

    //--- DATABASE MAPPING : file_name ( VARCHAR ) 
    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return this.fileName;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : update_date ( DATETIME ) 
    public void setUpdateDate( Date updateDate ) {
        this.updateDate = updateDate;
    }
    public Date getUpdateDate() {
        return this.updateDate;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    //--- DATABASE MAPPING : author ( VARCHAR ) 
    public void setAuthor( String author ) {
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }

    //--- DATABASE MAPPING : issued_date ( DATETIME ) 
    public void setIssuedDate( Date issuedDate ) {
        this.issuedDate = issuedDate;
    }
    public Date getIssuedDate() {
        return this.issuedDate;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
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
