package org.trams.hello.common.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.Notification;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserNotification;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.PushNotificationEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.business.event.CounselingSessionBeforeStartEvent;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.NotificationService;
import org.trams.hello.business.service.PaymentProfitService;
import org.trams.hello.business.service.PushNotificationService;
import org.trams.hello.business.service.RequestUserPsychologicalService;
import org.trams.hello.business.service.RtcService;
import org.trams.hello.business.service.UserNotificationService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.mapping.UserServiceMapper;
import org.trams.hello.common.mail.Mail;
import org.trams.hello.push.service.impl.SendPushServiceImpl;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.utils.DataUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class ExecuteJob {
	@Autowired
	private SendPushServiceImpl sendPushServiceImpl;
	@Autowired
	private CounselingSessionService counselingSessionService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserServiceMapper userServiceMapper;
	@Autowired
	private RtcService rtcService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private PaymentProfitService paymentProfitService;
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	@Autowired
	private RequestUserPsychologicalService requestUserPsychologicalService;
	@Autowired
	private PushNotificationService pushNotificationService;
	@Autowired
	private UserNotificationService userNotificationService;

	/*******************************************************
	 *
	 * Job that runs every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void rtcJob() {

		// filter every Counseling Session that not counsel
		// and startTime is less than 10 mins
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 10);

		Map<String, Object> params = new HashMap<>();
		params.put("page", 1);
		params.put("size", Integer.MAX_VALUE);
		params.put("statuses", new Short[] { 1 });
		params.put("counselTypes", new Short[] { 2 });
		params.put("scheduledTime", calendar.getTime());
		params.put("rooms", "NULL");

		Page<CounselingSessionEntity> filter = counselingSessionService.filter(params);
		List<CounselingSessionEntity> sessionEntities = filter.getContent();

		// create rtc room and update Counseling Session
		if (!sessionEntities.isEmpty()) {
			for (CounselingSessionEntity cse : sessionEntities) {

				Map<String, Object> rtcParams = new HashMap<>(1);
				rtcParams.put("title", UUID.randomUUID().toString());
				try {
					Map<String, Object> room = rtcService.createRoom(rtcParams);
					Map<String, Object> roomInfo = (Map<String, Object>) room.get("room");

					cse.setRoomId(String.valueOf(roomInfo.get("roomId")));
					counselingSessionService.saveOrUpdate(cse);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*******************************************************
	 *
	 * Job that runs every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void sendSmsReservation() {

		//send sms to counselor
		// filter every Counseling Session that not counsel
		// and startTime is less than 5 min and greater than 4 min
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 5);
		List<CounselingSessionEntity> list = counselingSessionService.listNotificaionByMinutes(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		
		// send sms
		for (CounselingSessionEntity cse : list) {
			applicationEventPublisher.publishEvent(new CounselingSessionBeforeStartEvent(this, cse , ApplicationDefine.SMS_Type.Counseling_Before_5Minutes_FromReservation.getCode()));
		}

	}
	
//	private List<CounselingSessionEntity> getCounselingSessionBeforeMinute(int minute){
//		Map<String, Object> params = new HashMap<>();
//		params.put("page", 1);
//		params.put("size", Integer.MAX_VALUE);
//		params.put("statuses", new Short[] { 1 });
//		params.put("counselTypes", new Short[] { 2 });
//		
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.MINUTE, minute);
//		calendar.set(Calendar.SECOND, 0);
//		params.put("scheduledTime", calendar.getTime());
//		//System.out.println(DateFormatUtils.format(calendar.getTime(), "yyyyMMdd HH:mm:ss"));
//		calendar.add(Calendar.MINUTE, -1);
//		params.put("fromNow"		, DataUtils.parseStringFromDate(calendar.getTime(), DataUtils.DatePattern.YYYYMMDDHHMMSS.getPattern()));
//		
//		Page<CounselingSessionEntity> filter = counselingSessionService.filter(params);
//		//System.out.println("total: " + filter.getTotalElements());
//		return filter.getContent();
//	}


	/*******************************************************
	 *
	 * Job that runs every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void sendNoitificationRevertionByMinute() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 2);
		List<CounselingSessionEntity> list = counselingSessionService.listNotificaionByMinutes(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		for (CounselingSessionEntity i : list) {
			applicationEventPublisher.publishEvent(new CounselingSessionBeforeStartEvent(this, i, ApplicationDefine.SMS_Type.Counseling_Before_2Minutes_FromReservation.getCode()));
			String title = "2분 후 심리상담이 시작됩니다.";
			String message = "지금부터 상담실 입장이 가능합니다.";
			notificationService.createNotification(i.getUser(), i.getUser().getUsername(), title, message, i.getId(), (short) ApplicationDefine.Notification_Type.Reservation.getCode(), null);
		}
	}

	/*******************************************************
	 *
	 * Job that runs every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void updateResvertionFinish() {
		counselingSessionService.updateCounselingFinished();
		//counselingSessionService.updateCounselingNotFinished();
		requestUserPsychologicalService.updateRequestNotPayment(new Date());
	}


	/*******************************************************
	 *
	 * Required Satisfaction Job: this runs first second every minute
	 *
	 *******************************************************/
//	@Scheduled(cron = "10 * * * * *")
//	public void sendNoitificationRequiredSatisfaction() {
		// Send Required Satisfaction
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.HOUR_OF_DAY, -3);
//		List<Object[]> list_required = counselingSessionService.listRequiredSatisfaction(c.get(Calendar.YEAR),
//				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE) );
//		for (Object[] i : list_required) {
//			notificationService.createNotificationUsers(Integer.parseInt(String.valueOf(i[3])) ,String.valueOf(i[0]),"심리상담 어떠셨나요?", "심리상담에 대한 만족도를 평가해주세요.", Integer.parseInt(String.valueOf(i[1])), (short) ApplicationDefine.Notification_Type.Satisfaction.getCode(),Integer.parseInt(String.valueOf(i[2])));
//		}

//	}

	/*******************************************************
	 *
	 * Job that runs first second every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void sendNoitificationRevertionOneHour() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, 1);
		List<CounselingSessionEntity> list_one_hour = counselingSessionService.listNotificaionByHour(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		for (CounselingSessionEntity i : list_one_hour) {
			String title = "심리상담 1시간 전입니다.";
			String message = "[" + i.getCounselor().getUser().getFullname() + "] 심리상담사가 [" + i.getUser().getFullname() + "] 님과의 만남을 준비하고 있습니다.";
			notificationService.createNotification(i.getUser(), i.getUser().getUsername(), title, message, i.getId(), (short) ApplicationDefine.Notification_Type.Reservation.getCode(), null);
			applicationEventPublisher.publishEvent(new CounselingSessionBeforeStartEvent(this, i , ApplicationDefine.SMS_Type.Counseling_Before_1Hour_FromReservation.getCode()));
		}

	}

	/*******************************************************
	 *
	 * Job that runs first second every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	public void sendNoitificationRevertionSixHour() {
		Calendar c = Calendar.getInstance();
		c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, 6);
		List<CounselingSessionEntity> list3 = counselingSessionService.listNotificaionByHour(c.get(Calendar.YEAR),
				c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
		for (CounselingSessionEntity i : list3) {
			String title = "심리상담 6시간 전입니다.";
			String message = "[" + i.getCounselor().getUser().getFullname() + "] 심리상담사가 [" + i.getUser().getFullname() + "]님과의 만남을 준비하고 있습니다.";
			notificationService.createNotification(i.getUser(), i.getUser().getUsername(), title, message, i.getId(), (short) ApplicationDefine.Notification_Type.Reservation.getCode(),null);

		}
	}
	
	/*******************************************************
	 *
	 * Job that runs first second every minute
	 *
	 *******************************************************/
	@Scheduled(cron = "0 * * * * *")
	//@Scheduled(fixedRate = 60000)
	public void pushNotification() {
		//System.out.println(DataUtils.parseStringFromDate(new Date(), "yyyy-MM-dd HH:mm:00"));
		List<PushNotificationEntity> listPush = pushNotificationService.findByDateAndStatus(DataUtils.parseStringFromDate(new Date(), "yyyy-MM-dd HH:mm:00"), (short)0);
		for (PushNotificationEntity p : listPush) {
			JsonArray jArray = new JsonParser().parse(p.getUserReceiveId()).getAsJsonArray();
			
			Notification n =notificationService.createByPush(p);
			Integer totalPushSuccess = 0;
	    	for (int i=0;i<jArray.size();i++) {
	    		JsonObject jsonObject = jArray.get(i).getAsJsonObject();
	    	    Integer id = Integer.valueOf(jsonObject.get("id").toString());
				String result = sendPushServiceImpl.sendTargetOneAndroid(id, jsonObject.get("username").toString().replace("\"", ""), p.getTitle(), p.getContent(), p.getLandingPageId(), p.getTypePush(), null, n.getId());
				if(result.trim().equals("200")){
					try {
						UserNotification un = new UserNotification();
						un.setCreateDate(new Date());
						un.setIsRead((short) 0);
						un.setNotificationId(n.getId());
						un.setUpdateDate(new Date());
						un.setUserId(id);
						un.setUuid("");
						un = userNotificationService.create(un);
					} catch (Exception e) {
						e.printStackTrace();
					}
					totalPushSuccess+=1;
				}
	    	}
	    	p.setStatus(ApplicationDefine.Push_Status.Success.getCode());
	    	p.setTotalPushSuccess(totalPushSuccess);
	    	pushNotificationService.update(p);
		}
	}

	/*******************************************************
	 *
	 * Job that runs first day every month
	 *
	 *******************************************************/
	@Scheduled(cron = "0 0 0 * * *")
	public void sendMailDormat() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -10);
		List<UserEntity> list = userService.listByLastLogin(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,
				c.get(Calendar.DATE));
		String timeout = (c.get(Calendar.YEAR) + 1) + "년 " + c.get(Calendar.MONTH)+1 + "월 " + c.get(Calendar.DAY_OF_MONTH)
				+ "일";
		for (UserEntity i : list) {
			HashMap<String, Object> params = new HashMap<>();
			params.put("username", i.getUsername());
			params.put("fullname", i.getFullname());
			params.put("email", i.getEmail());
			params.put("createDate", timeout);
			Mail.sendEmailTemplate(i.getEmail(), params, Mail.EmailTemplate.CHANGE_ACCOUNT_TO_DORMANT_ACCOUNT, ApplicationDefine.EmailSubject.CHANGE_ACCOUNT_TO_DORMANT_ACCOUNT.getCode());
		}
		c = Calendar.getInstance();
		c.add(Calendar.MONTH, -12);
		List<UserEntity> list2 = userService.listByLastLogin(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1,
				c.get(Calendar.DATE));
		for (UserEntity i : list2) {
			User u = userServiceMapper.mapUserEntityToUser(i);
			u.setStatusActive(ApplicationDefine.UserStatusActive.DORMAT.getCode());
			userService.update(u);
		}
	}

	@Scheduled(cron = "0 0 0 1 * *")
	public void removeHeart() {
		Calendar c = Calendar.getInstance();
		c = Calendar.getInstance();
		c.add(Calendar.MONTH, -6);
		List<UserEntity> listNoChangeHeart = userService.listByChangeHeartDate(c.getTime());
		if(listNoChangeHeart != null){
			for (UserEntity i : listNoChangeHeart) {
				User user = userServiceMapper.mapUserEntityToUser(i);
				if(user.getHeart() >0){
			        userService.updateHeartByUserId(user.getId(), user.getHeart(), ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode(), ApplicationDefine.RechargeHeart_Status.ManualRechargeByAdmin.getCode(),"");
				}
			}
		}
	}
	
	@Scheduled(cron = "0 1 0 * * *")
	public void updateInfomationUserWithdrawl() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		System.out.println(DataUtils.parseStringFromDate(c.getTime(), "yyyy/MM/dd"));
		List<User> list = userService.getUserWithDrawlByDay(c.getTime());
		for (User user : list) {
			user.setUsername(user.getUsername()+"(탈퇴)");
			user.setPhone("0"+user.getPhone());
			user.setEmail(user.getEmail()+"(탈퇴)");
			user.setBusinessSubId(null);
			user.setPasswordTemp(null);
			user.setAddress(null);
			user.setOsName(null);
			user.setOsVersion(null);
			user.setAppVesion(null);
			user.setAppVesionId(null);
			user.setDeviceId(null);
			user.setDeviceVersion(null);
			user.setDeviceName(null);
			user.setLastLogin(null);
			user.setChangeHeartDate(null);
			userService.update(user);
		}
		
	}



}