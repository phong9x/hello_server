/*
 * Created on 31 Mar 2017 ( Time 09:49:32 )
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
public class Comment implements Serializable {

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
    private Integer userId;

    @NotNull
    private Integer counselorId;

    @NotNull
    private Integer counselingSessionId;

    @NotNull
    @Size( min = 1, max = 500 )
    private String comment;

    @NotNull
    private Integer point;


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

    public void setCounselingSessionId( Integer counselingSessionId ) {
        this.counselingSessionId = counselingSessionId;
    }
    public Integer getCounselingSessionId() {
        return this.counselingSessionId;
    }

    public void setComment( String comment ) {
        this.comment = comment;
    }
    public String getComment() {
        return this.comment;
    }

    public void setPoint( Integer point ) {
        this.point = point;
    }
    public Integer getPoint() {
        return this.point;
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
        sb.append(userId);
        sb.append("|");
        sb.append(counselorId);
        sb.append("|");
        sb.append(counselingSessionId);
        sb.append("|");
        sb.append(comment);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
