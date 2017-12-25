/*
 * Created on 24 May 2017 ( Time 11:40:00 )
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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Persistent class for entity stored in table "voucher_user"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="voucher_user", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="VoucherUserEntity.countAll", query="SELECT COUNT(x) FROM VoucherUserEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class VoucherUserEntity implements Serializable {

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
    @Column(name="user_id", nullable=false)
    private Integer    userId       ;

    @Column(name="admin_id", nullable=false)
    private Integer    adminId      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="from_date", nullable=false)
    private Date       fromDate     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="to_date", nullable=false)
    private Date       toDate       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="use_voucher_date")
    private Date       useVoucherDate ;

    @Column(name="status_use", nullable=false)
    private Short      statusUse    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Column(name="reason", length=300)
    private String     reason       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

	// "voucherId" (column "voucher_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
	@JsonIgnore
    @OneToMany(mappedBy="voucherUser", targetEntity=PaymentHistoryEntity.class)
    private List<PaymentHistoryEntity> listOfPaymentHistory;

    @ManyToOne
    @JoinColumn(name="voucher_id", referencedColumnName="id")
    private VoucherEntity voucher     ;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public VoucherUserEntity() {
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
    //--- DATABASE MAPPING : user_id ( INT ) 
    public void setUserId( Integer userId ) {
        this.userId = userId;
    }
    public Integer getUserId() {
        return this.userId;
    }

    //--- DATABASE MAPPING : admin_id ( INT ) 
    public void setAdminId( Integer adminId ) {
        this.adminId = adminId;
    }
    public Integer getAdminId() {
        return this.adminId;
    }

    //--- DATABASE MAPPING : from_date ( DATETIME ) 
    public void setFromDate( Date fromDate ) {
        this.fromDate = fromDate;
    }
    public Date getFromDate() {
        return this.fromDate;
    }

    //--- DATABASE MAPPING : to_date ( DATETIME ) 
    public void setToDate( Date toDate ) {
        this.toDate = toDate;
    }
    public Date getToDate() {
        return this.toDate;
    }

    //--- DATABASE MAPPING : use_voucher_date ( DATETIME ) 
    public void setUseVoucherDate( Date useVoucherDate ) {
        this.useVoucherDate = useVoucherDate;
    }
    public Date getUseVoucherDate() {
        return this.useVoucherDate;
    }

    //--- DATABASE MAPPING : status_use ( SMALLINT ) 
    public void setStatusUse( Short statusUse ) {
        this.statusUse = statusUse;
    }
    public Short getStatusUse() {
        return this.statusUse;
    }

    //--- DATABASE MAPPING : create_date ( DATETIME ) 
    public void setCreateDate( Date createDate ) {
        this.createDate = createDate;
    }
    public Date getCreateDate() {
        return this.createDate;
    }

    //--- DATABASE MAPPING : reason ( VARCHAR ) 
    public void setReason( String reason ) {
        this.reason = reason;
    }
    public String getReason() {
        return this.reason;
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
    public void setListOfPaymentHistory( List<PaymentHistoryEntity> listOfPaymentHistory ) {
        this.listOfPaymentHistory = listOfPaymentHistory;
    }
    public List<PaymentHistoryEntity> getListOfPaymentHistory() {
        return this.listOfPaymentHistory;
    }

    public void setVoucher( VoucherEntity voucher ) {
        this.voucher = voucher;
    }
    public VoucherEntity getVoucher() {
        return this.voucher;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(userId);
        sb.append("|");
        sb.append(adminId);
        sb.append("|");
        sb.append(fromDate);
        sb.append("|");
        sb.append(toDate);
        sb.append("|");
        sb.append(useVoucherDate);
        sb.append("|");
        sb.append(statusUse);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(reason);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}