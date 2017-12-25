/*
 * Created on 10 8월 2017 ( Time 14:45:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package org.trams.hello.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.Date;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "company_user_voucher_history"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="company_user_voucher_history", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CompanyUserVoucherHistoryEntity.countAll", query="SELECT COUNT(x) FROM CompanyUserVoucherHistoryEntity x" )
} )
public class CompanyUserVoucherHistoryEntity implements Serializable {

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
    @Column(name="aimmed_user_id", nullable=false, length=256)
    private String     aimmedUserId ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable=false)
    private Date       createDate   ;

	// "businessSubId" (column "business_sub_id") is not defined by itself because used as FK in a link 
	// "userId" (column "user_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="business_sub_id", referencedColumnName="id")
    private BusinessSubEntity businessSub ;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CompanyUserVoucherHistoryEntity() {
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
    //--- DATABASE MAPPING : aimmed_user_id ( VARCHAR ) 
    public void setAimmedUserId( String aimmedUserId ) {
        this.aimmedUserId = aimmedUserId;
    }
    public String getAimmedUserId() {
        return this.aimmedUserId;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
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

    public void setBusinessSub( BusinessSubEntity businessSub ) {
        this.businessSub = businessSub;
    }
    public BusinessSubEntity getBusinessSub() {
        return this.businessSub;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(aimmedUserId);
        sb.append("|");
        sb.append(createDate);
        return sb.toString(); 
    } 

}
