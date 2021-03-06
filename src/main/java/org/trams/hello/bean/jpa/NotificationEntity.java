/*
 * Created on 29 Jun 2017 ( Time 14:50:10 )
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Persistent class for entity stored in table "notification"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="notification", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="NotificationEntity.countAll", query="SELECT COUNT(x) FROM NotificationEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class NotificationEntity implements Serializable {

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
    @Column(name="title", length=200)
    private String     title        ;

    @Column(name="content", length=500)
    private String     content      ;

    @Column(name="type")
    private Short      type         ;

    @Column(name="type_push")
    private Short      typePush     ;

    @Column(name="os_type")
    private Short      osType       ;

    @Column(name="status")
    private Short      status       ;

    @Column(name="push_notification_id")
    private Integer pushNotificationId;
    
    @Column(name="information", length=200)
    private String     information  ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "userId" (column "user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;


	@Transient
    private boolean    isRead;

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public NotificationEntity() {
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
    //--- DATABASE MAPPING : title ( VARCHAR ) 
    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    //--- DATABASE MAPPING : content ( VARCHAR ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
    }

    //--- DATABASE MAPPING : type_push ( SMALLINT ) 
    public void setTypePush( Short typePush ) {
        this.typePush = typePush;
    }
    public Short getTypePush() {
        return this.typePush;
    }

    //--- DATABASE MAPPING : os_type ( SMALLINT ) 
    public void setOsType( Short osType ) {
        this.osType = osType;
    }
    public Short getOsType() {
        return this.osType;
    }

    //--- DATABASE MAPPING : status ( SMALLINT ) 
    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : information ( VARCHAR ) 
    public void setInformation( String information ) {
        this.information = information;
    }
    public String getInformation() {
        return this.information;
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


    public Integer getPushNotificationId() {
		return pushNotificationId;
	}

	public void setPushNotificationId(Integer pushNotificationId) {
		this.pushNotificationId = pushNotificationId;
	}

	//----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(title);
        sb.append("|");
        sb.append(content);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(typePush);
        sb.append("|");
        sb.append(osType);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(information);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
