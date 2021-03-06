/*
 * Created on 13 Apr 2017 ( Time 16:55:49 )
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
 * Persistent class for entity stored in table "payment_profit"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="payment_profit", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="PaymentProfitEntity.countAll", query="SELECT COUNT(x) FROM PaymentProfitEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class PaymentProfitEntity implements Serializable {

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
    @Column(name="name", nullable=false, length=100)
    private String     name         ;

    @Column(name="month", nullable=false)
    private Integer    month        ;

    @Column(name="year", nullable=false)
    private Integer    year         ;

    @Column(name="coin", nullable=false)
    private Integer    coin         ;

    @Column(name="voucher", nullable=false)
    private Integer    voucher      ;

    @Column(name="percent", nullable=false)
    private Float      percent      ;

    @Column(name="profit", nullable=false)
    private Integer    profit       ;

    @Column(name="tax", nullable=false)
    private Float      tax          ;

    @Column(name="admin_refund")
    private Integer    adminRefund  ;

    @Column(name="status", nullable=false)
    private Short      status       ;

    @Column(name="type", nullable=false)
    private Short      type         ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable=false)
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


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public PaymentProfitEntity() {
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
    //--- DATABASE MAPPING : name ( VARCHAR ) 
    public void setName( String name ) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    //--- DATABASE MAPPING : month ( INT ) 
    public void setMonth( Integer month ) {
        this.month = month;
    }
    public Integer getMonth() {
        return this.month;
    }

    //--- DATABASE MAPPING : year ( INT ) 
    public void setYear( Integer year ) {
        this.year = year;
    }
    public Integer getYear() {
        return this.year;
    }

    //--- DATABASE MAPPING : coin ( INT ) 
    public void setCoin( Integer coin ) {
        this.coin = coin;
    }
    public Integer getCoin() {
        return this.coin;
    }

    //--- DATABASE MAPPING : voucher ( INT ) 
    public void setVoucher( Integer voucher ) {
        this.voucher = voucher;
    }
    public Integer getVoucher() {
        return this.voucher;
    }

    //--- DATABASE MAPPING : percent ( FLOAT ) 
    public void setPercent( Float percent ) {
        this.percent = percent;
    }
    public Float getPercent() {
        return this.percent;
    }

    //--- DATABASE MAPPING : profit ( INT ) 
    public void setProfit( Integer profit ) {
        this.profit = profit;
    }
    public Integer getProfit() {
        return this.profit;
    }

    //--- DATABASE MAPPING : tax ( FLOAT ) 
    public void setTax( Float tax ) {
        this.tax = tax;
    }
    public Float getTax() {
        return this.tax;
    }

    //--- DATABASE MAPPING : admin_refund ( INT ) 
    public void setAdminRefund( Integer adminRefund ) {
        this.adminRefund = adminRefund;
    }
    public Integer getAdminRefund() {
        return this.adminRefund;
    }

    //--- DATABASE MAPPING : status ( SMALLINT ) 
    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
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
        sb.append(name);
        sb.append("|");
        sb.append(month);
        sb.append("|");
        sb.append(year);
        sb.append("|");
        sb.append(coin);
        sb.append("|");
        sb.append(voucher);
        sb.append("|");
        sb.append(percent);
        sb.append("|");
        sb.append(profit);
        sb.append("|");
        sb.append(tax);
        sb.append("|");
        sb.append(adminRefund);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 

}
