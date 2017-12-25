/*
 * Created on 11 Jul 2017 ( Time 12:24:51 )
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
 * Persistent class for entity stored in table "counseling_session"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="counseling_session", catalog="hello" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="CounselingSessionEntity.countAll", query="SELECT COUNT(x) FROM CounselingSessionEntity x" )
} )
@DynamicInsert
@DynamicUpdate
public class CounselingSessionEntity implements Serializable {

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
    @Column(name="room_id", length=100)
    private String     roomId       ;

    @Column(name="counseling_field", length=500)
    private String     counselingField ;

    @Column(name="member_type")
    private Short      memberType   ;

    @Column(name="os_name", length=100)
    private String     osName       ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="start_time", nullable=false)
    private Date       startTime    ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="end_time")
    private Date       endTime      ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="cancel_metting_time")
    private Date       cancelMettingTime ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="entry_counselor_time")
    private Date       entryCounselorTime ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="entry_user_time")
    private Date       entryUserTime ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="exit_counselor_time")
    private Date       exitCounselorTime ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="exit_user_time")
    private Date       exitUserTime ;

    @Column(name="point")
    private Float      point        ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="refund_date")
    private Date       refundDate   ;

    @Column(name="status")
    private Short      status       ;

    @Column(name="counsel_type")
    private Short      counselType  ;

    @Column(name="counseling_time_type", nullable=false)
    private Short      counselingTimeType ;

    @Column(name="satisfaction_rating")
    private Short      satisfactionRating ;

    @Column(name="is_delete")
    private Integer    isDelete     ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date       createDate   ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date       updateDate   ;

    @Column(name="result_code", nullable=false)
    private Short      resultCode   ;

    @Column(name="disconnect_count")
    private Integer disconnectCount;
    
    @Column(name="pre_room_id", length=100)
    private String     preRoomId       ;
    
	// "userId" (column "user_id") is not defined by itself because used as FK in a link 
	// "counselorId" (column "counselor_id") is not defined by itself because used as FK in a link 
	// "paymentId" (column "payment_id") is not defined by itself because used as FK in a link 


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name="payment_id", referencedColumnName="id")
    private PaymentHistoryEntity paymentHistory;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private UserEntity user        ;

    @ManyToOne
    @JoinColumn(name="counselor_id", referencedColumnName="id")
    private CounselorEntity counselor   ;



    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public CounselingSessionEntity() {
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
    //--- DATABASE MAPPING : room_id ( VARCHAR ) 
    public void setRoomId( String roomId ) {
        this.roomId = roomId;
    }
    public String getRoomId() {
        return this.roomId;
    }

    //--- DATABASE MAPPING : counseling_field ( VARCHAR ) 
    public void setCounselingField( String counselingField ) {
        this.counselingField = counselingField;
    }
    public String getCounselingField() {
        return this.counselingField;
    }

    //--- DATABASE MAPPING : member_type ( SMALLINT ) 
    public void setMemberType( Short memberType ) {
        this.memberType = memberType;
    }
    public Short getMemberType() {
        return this.memberType;
    }

    //--- DATABASE MAPPING : os_name ( VARCHAR ) 
    public void setOsName( String osName ) {
        this.osName = osName;
    }
    public String getOsName() {
        return this.osName;
    }

    //--- DATABASE MAPPING : start_time ( DATETIME ) 
    public void setStartTime( Date startTime ) {
        this.startTime = startTime;
    }
    public Date getStartTime() {
        return this.startTime;
    }

    //--- DATABASE MAPPING : end_time ( DATETIME ) 
    public void setEndTime( Date endTime ) {
        this.endTime = endTime;
    }
    public Date getEndTime() {
        return this.endTime;
    }

    //--- DATABASE MAPPING : cancel_metting_time ( DATETIME ) 
    public void setCancelMettingTime( Date cancelMettingTime ) {
        this.cancelMettingTime = cancelMettingTime;
    }
    public Date getCancelMettingTime() {
        return this.cancelMettingTime;
    }

    //--- DATABASE MAPPING : entry_counselor_time ( DATETIME ) 
    public void setEntryCounselorTime( Date entryCounselorTime ) {
        this.entryCounselorTime = entryCounselorTime;
    }
    public Date getEntryCounselorTime() {
        return this.entryCounselorTime;
    }

    //--- DATABASE MAPPING : entry_user_time ( DATETIME ) 
    public void setEntryUserTime( Date entryUserTime ) {
        this.entryUserTime = entryUserTime;
    }
    public Date getEntryUserTime() {
        return this.entryUserTime;
    }

    //--- DATABASE MAPPING : exit_counselor_time ( DATETIME ) 
    public void setExitCounselorTime( Date exitCounselorTime ) {
        this.exitCounselorTime = exitCounselorTime;
    }
    public Date getExitCounselorTime() {
        return this.exitCounselorTime;
    }

    //--- DATABASE MAPPING : exit_user_time ( DATETIME ) 
    public void setExitUserTime( Date exitUserTime ) {
        this.exitUserTime = exitUserTime;
    }
    public Date getExitUserTime() {
        return this.exitUserTime;
    }

    //--- DATABASE MAPPING : point ( FLOAT ) 
    public void setPoint( Float point ) {
        this.point = point;
    }
    public Float getPoint() {
        return this.point;
    }

    //--- DATABASE MAPPING : refund_date ( DATETIME ) 
    public void setRefundDate( Date refundDate ) {
        this.refundDate = refundDate;
    }
    public Date getRefundDate() {
        return this.refundDate;
    }

    //--- DATABASE MAPPING : status ( SMALLINT UNSIGNED ) 
    public void setStatus( Short status ) {
        this.status = status;
    }
    public Short getStatus() {
        return this.status;
    }

    //--- DATABASE MAPPING : counsel_type ( SMALLINT ) 
    public void setCounselType( Short counselType ) {
        this.counselType = counselType;
    }
    public Short getCounselType() {
        return this.counselType;
    }

    //--- DATABASE MAPPING : counseling_time_type ( SMALLINT ) 
    public void setCounselingTimeType( Short counselingTimeType ) {
        this.counselingTimeType = counselingTimeType;
    }
    public Short getCounselingTimeType() {
        return this.counselingTimeType;
    }

    //--- DATABASE MAPPING : satisfaction_rating ( SMALLINT UNSIGNED ) 
    public void setSatisfactionRating( Short satisfactionRating ) {
        this.satisfactionRating = satisfactionRating;
    }
    public Short getSatisfactionRating() {
        return this.satisfactionRating;
    }

    //--- DATABASE MAPPING : is_delete ( INT ) 
    public void setIsDelete( Integer isDelete ) {
        this.isDelete = isDelete;
    }
    public Integer getIsDelete() {
        return this.isDelete;
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

    //--- DATABASE MAPPING : result_code ( SMALLINT UNSIGNED ) 
    public void setResultCode( Short resultCode ) {
        this.resultCode = resultCode;
    }
    public Short getResultCode() {
        return this.resultCode;
    }

    public Integer getDisconnectCount() {
		return disconnectCount;
	}

	public void setDisconnectCount(Integer disconnectCount) {
		this.disconnectCount = disconnectCount;
	}

	
	public String getPreRoomId() {
		return preRoomId;
	}
	public void setPreRoomId(String preRoomId) {
		this.preRoomId = preRoomId;
	}
	
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setPaymentHistory( PaymentHistoryEntity paymentHistory ) {
        this.paymentHistory = paymentHistory;
    }
    public PaymentHistoryEntity getPaymentHistory() {
        return this.paymentHistory;
    }

    public void setUser( UserEntity user ) {
        this.user = user;
    }
    public UserEntity getUser() {
        return this.user;
    }

    public void setCounselor( CounselorEntity counselor ) {
        this.counselor = counselor;
    }
    public CounselorEntity getCounselor() {
        return this.counselor;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(roomId);
        sb.append("|");
        sb.append(counselingField);
        sb.append("|");
        sb.append(memberType);
        sb.append("|");
        sb.append(osName);
        sb.append("|");
        sb.append(startTime);
        sb.append("|");
        sb.append(endTime);
        sb.append("|");
        sb.append(cancelMettingTime);
        sb.append("|");
        sb.append(entryCounselorTime);
        sb.append("|");
        sb.append(entryUserTime);
        sb.append("|");
        sb.append(exitCounselorTime);
        sb.append("|");
        sb.append(exitUserTime);
        sb.append("|");
        sb.append(point);
        sb.append("|");
        sb.append(refundDate);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(counselType);
        sb.append("|");
        sb.append(counselingTimeType);
        sb.append("|");
        sb.append(satisfactionRating);
        sb.append("|");
        sb.append(isDelete);
        sb.append("|");
        sb.append(createDate);
        sb.append("|");
        sb.append(updateDate);
        sb.append("|");
        sb.append(resultCode);
        sb.append("|");
        sb.append(disconnectCount);
        sb.append("|");
        sb.append(preRoomId);
        return sb.toString(); 
    } 

}
