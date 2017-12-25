/*
 * Created on 25 8월 2017 ( Time 17:32:31 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for entity stored in table "notice"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="notice", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="NoticeEntity.countAll", query="SELECT COUNT(x) FROM NoticeEntity x" )
} )
public class NoticeEntity implements Serializable {

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
    @Column(name="title", nullable=false, length=200)
    private String     title        ;

    @Column(name="content", nullable=false)
    private String     content      ;

    @Column(name="business_type")
    private Short      businessType ;

    @Column(name="os_type", nullable=false, length=50)
    private String     osType       ;

    @Column(name="view_number", nullable=false)
    private Integer    viewNumber   ;

    @Column(name="is_show", nullable=false)
    private Short      isShow       ;

    @Column(name="type")
    private Short      type         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Column(name="show_android")
    private Boolean    showAndroid  ;

    @Column(name="show_ios")
    private Boolean    showIos      ;

    @Column(name="show_web")
    private Boolean    showWeb      ;

	// "adminId" (column "admin_id") is not defined by itself because used as FK in a link 
	// "businessId" (column "business_id") is not defined by itself because used as FK in a link 

    @Transient
	private String adminName;
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="notice", targetEntity=UserNoticeEntity.class)
    private List<UserNoticeEntity> listOfUserNotice;

    @ManyToOne
    @JoinColumn(name="admin_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="business_id", referencedColumnName="id")
    private BusinessEntity business    ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public NoticeEntity() {
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

    //--- DATABASE MAPPING : content ( LONGTEXT ) 
    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    //--- DATABASE MAPPING : business_type ( SMALLINT ) 
    public void setBusinessType( Short businessType ) {
        this.businessType = businessType;
    }
    public Short getBusinessType() {
        return this.businessType;
    }

    //--- DATABASE MAPPING : os_type ( VARCHAR ) 
    public void setOsType( String osType ) {
        this.osType = osType;
    }
    public String getOsType() {
        return this.osType;
    }

    //--- DATABASE MAPPING : view_number ( INT ) 
    public void setViewNumber( Integer viewNumber ) {
        this.viewNumber = viewNumber;
    }
    public Integer getViewNumber() {
        return this.viewNumber;
    }

    //--- DATABASE MAPPING : is_show ( SMALLINT ) 
    public void setIsShow( Short isShow ) {
        this.isShow = isShow;
    }
    public Short getIsShow() {
        return this.isShow;
    }

    //--- DATABASE MAPPING : type ( SMALLINT ) 
    public void setType( Short type ) {
        this.type = type;
    }
    public Short getType() {
        return this.type;
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

    //--- DATABASE MAPPING : show_android ( BIT ) 
    public void setShowAndroid( Boolean showAndroid ) {
        this.showAndroid = showAndroid;
    }
    public Boolean getShowAndroid() {
        return this.showAndroid;
    }

    //--- DATABASE MAPPING : show_ios ( BIT ) 
    public void setShowIos( Boolean showIos ) {
        this.showIos = showIos;
    }
    public Boolean getShowIos() {
        return this.showIos;
    }

    //--- DATABASE MAPPING : show_web ( BIT ) 
    public void setShowWeb( Boolean showWeb ) {
        this.showWeb = showWeb;
    }
    public Boolean getShowWeb() {
        return this.showWeb;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfUserNotice( List<UserNoticeEntity> listOfUserNotice ) {
        this.listOfUserNotice = listOfUserNotice;
    }
    public List<UserNoticeEntity> getListOfUserNotice() {
        return this.listOfUserNotice;
    }

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setBusiness( BusinessEntity business ) {
        this.business = business;
    }
    public BusinessEntity getBusiness() {
        return this.business;
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
        // attribute 'content' not usable (type = String Long Text)
        sb.append("|");
        sb.append(businessType);
        sb.append("|");
        sb.append(osType);
        sb.append("|");
        sb.append(viewNumber);
        sb.append("|");
        sb.append(isShow);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(showAndroid);
        sb.append("|");
        sb.append(showIos);
        sb.append("|");
        sb.append(showWeb);
        return sb.toString(); 
    } 

}
