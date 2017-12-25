/*
 * Created on 22 May 2017 ( Time 10:13:16 )
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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Persistent class for entity stored in table "push_notification"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="push_notification", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PushNotificationEntity.countAll", query="SELECT COUNT(x) FROM PushNotificationEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class PushNotificationEntity implements Serializable {

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

    @Column(name="landing_page", length=100)
    private String     landingPage  ;
    
    @Column(name="landing_page_id")
    private Integer     landingPageId  ;

    @Column(name="total_push", nullable=false)
    private Integer    totalPush    ;

    @Column(name="open_app", nullable=false)
    private Integer    openApp      ;

    @Column(name="total_push_success", nullable=false)
    private Integer    totalPushSuccess ;

    @Column(name="type_send")
    private Short      typeSend     ;

    @Column(name="type_push")
    private Short      typePush     ;

    @Column(name="os_type")
    private Short      osType       ;

    @Column(name="status")
    private Short      status       ;

    @Column(name="user_receive_id")
    private String     userReceiveId ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_push_date")
    private Date       startPushDate ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "adminId" (column "admin_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="admin_id", referencedColumnName="id")
    private UserEntity user        ;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PushNotificationEntity() {
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

    //--- DATABASE MAPPING : landing_page ( VARCHAR ) 
    public void setLandingPage( String landingPage ) {
        this.landingPage = landingPage;
    }
    public String getLandingPage() {
        return this.landingPage;
    }

    //--- DATABASE MAPPING : total_push ( INT ) 
    public void setTotalPush( Integer totalPush ) {
        this.totalPush = totalPush;
    }
    public Integer getTotalPush() {
        return this.totalPush;
    }

    //--- DATABASE MAPPING : open_app ( INT ) 
    public void setOpenApp( Integer openApp ) {
        this.openApp = openApp;
    }
    public Integer getOpenApp() {
        return this.openApp;
    }

    public Integer getLandingPageId() {
		return landingPageId;
	}

	public void setLandingPageId(Integer landingPageId) {
		this.landingPageId = landingPageId;
	}

	//--- DATABASE MAPPING : total_push_success ( INT ) 
    public void setTotalPushSuccess( Integer totalPushSuccess ) {
        this.totalPushSuccess = totalPushSuccess;
    }
    public Integer getTotalPushSuccess() {
        return this.totalPushSuccess;
    }

    //--- DATABASE MAPPING : type_send ( SMALLINT ) 
    public void setTypeSend( Short typeSend ) {
        this.typeSend = typeSend;
    }
    public Short getTypeSend() {
        return this.typeSend;
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

    //--- DATABASE MAPPING : user_receive_id ( TEXT ) 
    public void setUserReceiveId( String userReceiveId ) {
        this.userReceiveId = userReceiveId;
    }
    public String getUserReceiveId() {
        return this.userReceiveId;
    }

    //--- DATABASE MAPPING : start_push_date ( DATETIME ) 
    public void setStartPushDate( Date startPushDate ) {
        this.startPushDate = startPushDate;
    }
    public Date getStartPushDate() {
        return this.startPushDate;
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
        sb.append(landingPage);
        sb.append("|");
        sb.append(totalPush);
        sb.append("|");
        sb.append(openApp);
        sb.append("|");
        sb.append(totalPushSuccess);
        sb.append("|");
        sb.append(typeSend);
        sb.append("|");
        sb.append(typePush);
        sb.append("|");
        sb.append(osType);
        sb.append("|");
        sb.append(status);
        // attribute 'userReceiveId' not usable (type = String Long Text)
        sb.append("|");
        sb.append(startPushDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
