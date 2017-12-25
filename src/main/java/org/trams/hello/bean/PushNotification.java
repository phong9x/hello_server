/*
 * Created on 22 May 2017 ( Time 10:13:16 )
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
public class PushNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Integer adminId;

    @Size( max = 200 )
    private String title;

    @Size( max = 500 )
    private String content;

    @Size( max = 100 )
    private String landingPage;
    
    private Integer landingPageId;

    @NotNull
    private Integer totalPush;

    @NotNull
    private Integer openApp;

    @NotNull
    private Integer totalPushSuccess;


    private Short typeSend;


    private Short typePush;


    private Short osType;


    private Short status;

    @Size( max = 65535 )
    private String userReceiveId;


    private Date startPushDate;


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
    
    
    public void setAdminId( Integer adminId ) {
        this.adminId = adminId;
    }
    public Integer getLandingPageId() {
		return landingPageId;
	}

	public void setLandingPageId(Integer landingPageId) {
		this.landingPageId = landingPageId;
	}

	public Integer getAdminId() {
        return this.adminId;
    }

    public void setTitle( String title ) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setLandingPage( String landingPage ) {
        this.landingPage = landingPage;
    }
    public String getLandingPage() {
        return this.landingPage;
    }

    public void setTotalPush( Integer totalPush ) {
        this.totalPush = totalPush;
    }
    public Integer getTotalPush() {
        return this.totalPush;
    }

    public void setOpenApp( Integer openApp ) {
        this.openApp = openApp;
    }
    public Integer getOpenApp() {
        return this.openApp;
    }

    public void setTotalPushSuccess( Integer totalPushSuccess ) {
        this.totalPushSuccess = totalPushSuccess;
    }
    public Integer getTotalPushSuccess() {
        return this.totalPushSuccess;
    }

    public void setTypeSend( Short typeSend ) {
        this.typeSend = typeSend;
    }
    public Short getTypeSend() {
        return this.typeSend;
    }

    public void setTypePush( Short typePush ) {
        this.typePush = typePush;
    }
    public Short getTypePush() {
        return this.typePush;
    }

    public void setOsType( Short osType ) {
        this.osType = osType;
    }
    public Short getOsType() {
        return this.osType;
    }

    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    public void setUserReceiveId( String userReceiveId ) {
        this.userReceiveId = userReceiveId;
    }
    public String getUserReceiveId() {
        return this.userReceiveId;
    }

    public void setStartPushDate( Date startPushDate ) {
        this.startPushDate = startPushDate;
    }
    public Date getStartPushDate() {
        return this.startPushDate;
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
        sb.append(adminId);
        sb.append("|");
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
