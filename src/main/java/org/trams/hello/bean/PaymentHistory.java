/*
 * Created on 14 Apr 2017 ( Time 15:35:15 )
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
public class PaymentHistory implements Serializable {

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


    private Integer adminId;

    @Size( max = 50 )
    private String dealNumber;


    private Integer money;


    private Integer coin;


    private Integer currentCoin;

    @NotNull
    private Short status;

    @NotNull
    private Short typeCoin;

    @Size( max = 500 )
    private String tid;


    private Integer typePayment;


    private Integer typeUse;


    private Integer paymentMethodId;

    @Size( max = 50 )
    private String reason;


    private Integer voucherUserId;

    @Size( max = 10 )
    private String osName;

    @Size( max = 65535 )
    private String accountInfo;


    private Date refundDate;


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

    public void setAdminId( Integer adminId ) {
        this.adminId = adminId;
    }
    public Integer getAdminId() {
        return this.adminId;
    }

    public void setDealNumber( String dealNumber ) {
        this.dealNumber = dealNumber;
    }
    public String getDealNumber() {
        return this.dealNumber;
    }

    public void setMoney( Integer money ) {
        this.money = money;
    }
    public Integer getMoney() {
        return this.money;
    }

    public void setCoin( Integer coin ) {
        this.coin = coin;
    }
    public Integer getCoin() {
        return this.coin;
    }

    public void setCurrentCoin( Integer currentCoin ) {
        this.currentCoin = currentCoin;
    }
    public Integer getCurrentCoin() {
        return this.currentCoin;
    }

    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    public void setTypeCoin( Short typeCoin ) {
        this.typeCoin = typeCoin;
    }
    public Short getTypeCoin() {
        return this.typeCoin;
    }

    public void setTid( String tid ) {
        this.tid = tid;
    }
    public String getTid() {
        return this.tid;
    }

    public void setTypePayment( Integer typePayment ) {
        this.typePayment = typePayment;
    }
    public Integer getTypePayment() {
        return this.typePayment;
    }

    public void setTypeUse( Integer typeUse ) {
        this.typeUse = typeUse;
    }
    public Integer getTypeUse() {
        return this.typeUse;
    }

    public void setPaymentMethodId( Integer paymentMethodId ) {
        this.paymentMethodId = paymentMethodId;
    }
    public Integer getPaymentMethodId() {
        return this.paymentMethodId;
    }

    public void setReason( String reason ) {
        this.reason = reason;
    }
    public String getReason() {
        return this.reason;
    }

    public void setVoucherUserId( Integer voucherUserId ) {
        this.voucherUserId = voucherUserId;
    }
    public Integer getVoucherUserId() {
        return this.voucherUserId;
    }

    public void setOsName( String osName ) {
        this.osName = osName;
    }
    public String getOsName() {
        return this.osName;
    }

    public void setAccountInfo( String accountInfo ) {
        this.accountInfo = accountInfo;
    }
    public String getAccountInfo() {
        return this.accountInfo;
    }

    public void setRefundDate( Date refundDate ) {
        this.refundDate = refundDate;
    }
    public Date getRefundDate() {
        return this.refundDate;
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
        sb.append(adminId);
        sb.append("|");
        sb.append(dealNumber);
        sb.append("|");
        sb.append(money);
        sb.append("|");
        sb.append(coin);
        sb.append("|");
        sb.append(currentCoin);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(typeCoin);
        sb.append("|");
        sb.append(tid);
        sb.append("|");
        sb.append(typePayment);
        sb.append("|");
        sb.append(typeUse);
        sb.append("|");
        sb.append(paymentMethodId);
        sb.append("|");
        sb.append(reason);
        sb.append("|");
        sb.append(voucherUserId);
        sb.append("|");
        sb.append(osName);
        // attribute 'accountInfo' not usable (type = String Long Text)
        sb.append("|");
        sb.append(refundDate);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        return sb.toString(); 
    } 


}
