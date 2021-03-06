/*
 * Created on 20 thg 2 2017 ( Time 14:11:59 )
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
public class CounselingMemo implements Serializable {

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
    @Size( min = 1, max = 1000 )
    private String memo;


    private Date createDate;


    private Date updateDate;


    private Integer counselorId;


    private Integer userId;



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
    public void setMemo( String memo ) {
        this.memo = memo;
    }
    public String getMemo() {
        return this.memo;
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

    public void setCounselorId( Integer counselorId ) {
        this.counselorId = counselorId;
    }
    public Integer getCounselorId() {
        return this.counselorId;
    }

    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(memo);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(counselorId);
        sb.append("|");
        sb.append(userId);
        return sb.toString(); 
    } 


}
