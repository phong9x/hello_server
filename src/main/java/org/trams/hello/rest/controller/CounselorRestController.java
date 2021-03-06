/*
 * Created on 7 thg 12 2016 ( Time 10:36:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.rest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.trams.hello.bean.CounselingSession;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.CounselorScheduleSetting;
import org.trams.hello.bean.PaymentHistory;
import org.trams.hello.bean.SearchHistory;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.UserQuestionaire;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.VoucherUser;
import org.trams.hello.bean.api.ReservationTime;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.TestResultEntity;
import org.trams.hello.bean.jpa.UserCertificateEntity;
import org.trams.hello.bean.jpa.UserQuestionaireEntity;
import org.trams.hello.bean.web.admin.CounselorSortPaper;
import org.trams.hello.business.event.CounselingSessionRealtimeAddedEvent;
import org.trams.hello.business.service.CommentService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorBiographyService;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.CounselorScheduleSettingService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PaymentHistoryService;
import org.trams.hello.business.service.RtcService;
import org.trams.hello.business.service.SearchHistoryService;
import org.trams.hello.business.service.TestResultService;
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.UserEducationService;
import org.trams.hello.business.service.UserQuestionaireService;
import org.trams.hello.business.service.UserReceiveMessageService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.VoucherUserService;
import org.trams.hello.data.repository.jpa.CounselorJpaRepository;
import org.trams.hello.rest.common.AbstractRestController;
import org.trams.hello.rest.common.AuthorizationToken;
import org.trams.hello.rest.common.Utils;
import org.trams.hello.rest.common.UtilsController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.utils.ConstantUtils;
import org.trams.hello.web.common.utils.EncryptionUtils;
import org.trams.hello.web.common.utils.JsonUtils;

/**
 * Spring MVC controller for 'UserNotification' management.
 */
@RequestMapping("/v1/public/counselor")
@Controller
public class CounselorRestController extends AbstractRestController {

	private static final RestTemplate restTemplate = new RestTemplate();

	@Resource
	private CounselorFieldService counselorFieldService;
	@Resource
	private VoucherUserService voucherUserService;
	@Resource
	private VoucherService voucherService;
	@Resource
	private UserCertificateService userCertificateService;
	@Resource
	private UserEducationService userEducationService;
	@Resource
	private CounselorBiographyService counselorBiographyService;
	@Resource
	private CounselorService counselorService;
	@Resource
	private CounselorJpaRepository counselorJpaRepository;
	@Resource
	private UserService userService;
	@Resource
	private UserReceiveMessageService userReceiveMessageService;
	@Resource
	private CommentService commentService;
	@Resource
	private CounselingSessionService counselingSessionService;
	@Resource
	private RtcService rtcService;
	@Resource
	private PaymentHistoryService paymentHistoryService;
	@Resource
	private UserQuestionaireService userQuestionaireService;
	@Autowired
	private CounselorScheduleSettingService counselorScheduleSettingService;
	@Autowired
	private TestResultService testResultService;
	@Resource
	private ApplicationEventPublisher applicationEventPublisher;
	@Resource
	private SearchHistoryService searchHistoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> filter(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "checkCounselor", required = false) Integer[] counsel_field,
			@RequestHeader(value = "token", required = false) String token) {
		try {
			Page<CounselorEntity> listPage = null;
			boolean isActive = false;
			Integer roleId = 0;
			UserItem u = new UserItem();
			if (!key.isEmpty()) {
				SearchHistory s = new SearchHistory();
				s.setCreateDate(new Date());
				s.setKeyword(key);
				s.setUserId(u.getId());
				searchHistoryService.create(s);
			}

			try {
				u = EncryptionUtils.jwtParse(token, UserItem.class);
				roleId = Integer.valueOf(u.getRole());
			} catch (Exception e) {
				e.printStackTrace();
			}

			/* get list all */
			listPage = getListPage(listPage, counsel_field, page, roleId, key);
			/*create 2 list - silver, gole , diamond in list all*/
			List<CounselorEntity> counselorDiamond = new ArrayList<>();
			List<CounselorEntity> counselorGoleAndSilver = new ArrayList<>();
			List<CounselorEntity> listAll = listPage.getContent();
			int sizeList = listAll.size();
			for (int h = 0; h < sizeList; h++) {
				if (listAll.get(h).getLevel() == ApplicationDefine.COUNSELOR_LEVEL_DIAMOND) {
					counselorDiamond.add(listAll.get(h));
				} else {
					counselorGoleAndSilver.add(listAll.get(h));
				}
			}
			/*get latest mindcheck resut of user logined and order it*/
			UserQuestionaireEntity latestMindcheckResult = userQuestionaireService.getLatestMindcheckResult(u.getId());
			Float point = (float) 0;
			
			// dangerous : check
			Short dangerous = 0;
			if (latestMindcheckResult != null) {
				point = latestMindcheckResult.getPoint();
				TestResultEntity t = testResultService.getTestResultByUserQuestionaireIdAndQuestionId(latestMindcheckResult.getId(), 61);
				if (t != null) {
					dangerous = t.getAnswer().getDangerous();
				}
			}
			
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, 1);
			if (counselorDiamond.size() > 0) {
				counselorDiamond	= checkOnline(counselorDiamond, isActive);
				checkOnlineAndStatusReservation(counselorDiamond, isActive, now);
			}
			
			if (counselorGoleAndSilver.size() > 0) {
				counselorGoleAndSilver	= checkOnline(counselorGoleAndSilver, isActive);
				checkOnlineAndStatusReservation(counselorGoleAndSilver, isActive, now);
			}
			
			if ((dangerous == 1) || (point >= 18) ) {
				UtilsController.orderListByMincheck(counselorDiamond, counselorGoleAndSilver, isActive);
			} else {
				UtilsController.orderListBy(counselorDiamond, counselorGoleAndSilver, isActive);
			}
			
			/*add 2 list to 1 list*/
			List<CounselorEntity> listFinal = new ArrayList<>();
			listFinal.addAll(counselorDiamond);
			listFinal.addAll(counselorGoleAndSilver);
			
			/*create properties for format json old*/
			HashMap<String, Object> ret = new HashMap<>();
			ret.put("content", listFinal);
			ret.put("totalPages", 1);
			ret.put("last", true);
			ret.put("totalElements", listFinal.size());
			ret.put("size", 30);
			ret.put("number", 0);
			List<CounselorSortPaper> lPapers = new ArrayList<>();
			lPapers.add(new CounselorSortPaper("DESC", "level", false, "NATIVE", false));
			ret.put("sort", lPapers);
			ret.put("first", true);
			ret.put("numberOfElements", listFinal.size());

			return SUCCESS(ret);

		} catch (Exception e) {
			e.printStackTrace();
			return FAIL(e.getMessage());
		}
	}
	
   public void checkOnlineAndStatusReservation(List<CounselorEntity> list,boolean isActive, Calendar now ) {
	   for (CounselorEntity cd : list) {
			Integer isRecd = counselorJpaRepository.isReservation(cd.getId(), now.getTime());
			if (isRecd == null) {
				isRecd = 0;
			}
			cd.setIsReservation(isRecd);
		}
   }

	public Page<CounselorEntity> getListPage(Page<CounselorEntity> listPage, Integer[] counsel_field, Integer page, Integer roleId, String key) {
		if (counsel_field.length == 1 && counsel_field[0] == 0) {
			listPage = counselorService.listSuggetPagingBy_CounlorFieldAndStatus(page, 30);
		} else {
			Short[] status = new Short[2];
			Short activeCode;
			if (roleId == ApplicationDefine.USER_ROLE_MONITERING) {
				status[0] = ApplicationDefine.Counselor_Status.NormalNotDisplay.getCode();
				status[1] = ApplicationDefine.Counselor_Status.NormalDisplay.getCode();
				activeCode = ApplicationDefine.Counselor_Actived.OFF.getCode();
			}else{
				status[0] = ApplicationDefine.Counselor_Status.NormalDisplay.getCode();
				status[1] = ApplicationDefine.Counselor_Status.WaitingRequestProfile.getCode();
				activeCode = ApplicationDefine.Counselor_Actived.ON.getCode();
			}
			
			if (counsel_field != null && counsel_field.length > 0 && !Arrays.asList(counsel_field).contains(100)) {
				if (key.equals("")) {
					listPage = counselorService.listPagingBy_CounlorFieldAndStatus(counsel_field, activeCode, status, page, 30);
				} else {
					listPage = counselorService.listPagingBy_CounlorFieldAndNickNameAndIntroduceAndStatus(counsel_field, key, activeCode, status, page, 30);
				}
			} else {
				if (key.equals("")) {
					listPage = counselorService.listPagingByStatusAndActived(status, activeCode, page, 30);
				} else {
					listPage = counselorService.listPagingBy_NickNameOrIntroduceAndStatus(key, status, activeCode, page, 30);
				}
			}
		}
		return listPage;
	}

	public List<CounselorEntity> checkOnline(List<CounselorEntity> list, boolean isActive) {
		log.debug("============================================================================");
		log.debug("checkOnline");
		if (list.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (CounselorEntity ce : list) {
				sb.append(ce.getId()).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
			
			log.debug("request: " + ConstantUtils.getConfig("counselor.domain") + "/api/v1/public/counselor/status?counselorIds=" + sb.toString());
			
			Map restResult = restTemplate.getForObject(ConstantUtils.getConfig("counselor.domain") + "/api/v1/public/counselor/status?counselorIds=" + sb.toString(), Map.class);
			Map<String, Object> css = (Map) restResult.get("data");
			
			log.debug("response: " + css);
			
			for (CounselorEntity ce : list) {
				ce.setGuidanceUsername(ce.getUser().getFullname());
				ce.setListOfCounselingSession(null);
				ce.setListOfCounselorField(null);
				ce.setListOfCounselorTemp(null);
				ce.setUser(null);
				ce.setCounselingCenter(null);
				ce.setBank(null);
				ce.setAccountBankHolder("");
				ce.setIsCounselingRestTime(1);
				// check online status
				ce.setStatusOnline(Integer.parseInt(String.valueOf(css.get(ce.getId().toString()))));
				
				//check online schedule
				Integer statusOnline = 0;
				if ((ce.getStatusOnline() == 1) && (ce.getCounselOnline() == 1)) {
					statusOnline = counselorService.getStatusRealTimeCounseling(ce.getId());
					
//					Map<String, Object> reservationParams = new HashMap<>();
//					reservationParams.put("page", 1);
//					reservationParams.put("size", 10);
//					reservationParams.put("counselorId", ce.getId());
//					reservationParams.put("reservationFrom", DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));
//					reservationParams.put("reservationTo", DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));
//					reservationParams.put("onlineTimeNoEmpty", true);
//					Page<CounselorScheduleSettingEntity> scheduleSettings = counselorScheduleSettingService.filter(reservationParams);
					//boolean isOnline = calculateStatusOnline(scheduleSettings, isActive, now, nowCheck, ce, isActiveCheck);
					
//					try {
//						if(!scheduleSettings.getContent().isEmpty() && !scheduleSettings.getContent().get(0).getOnlineTime().isEmpty()){
//							TreeMap<Float, ReservedAndCounselingType> listStartTimeCounselingSSByDay = counselingSessionService.listStartTimeCounselingSSByDay(now.getTime(), ce.getId());
//							isOnline = Utils.checkCounselorOnlineBusy(30, ce.getCounselingRestTime(), now, scheduleSettings.getContent().get(0).getOnlineTime(), listStartTimeCounselingSSByDay, isOnline);
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
				} 
				ce.setStatusOnline(statusOnline);
			}
		}
		log.debug("============================================================================");
		return list;
	}

//	public boolean calculateStatusOnline(Page<CounselorScheduleSettingEntity> scheduleSettings, boolean isActive, Calendar now, Calendar nowCheck, CounselorEntity ce, boolean isActiveCheck) {
//		if (!scheduleSettings.getContent().isEmpty()) {
//			CounselorScheduleSettingEntity csse = scheduleSettings.getContent().get(0);
//			if (csse.getOnlineTime() == null || csse.getOnlineTime().isEmpty()) {
//				System.out.println("Wrong input !");
//			} else {
//				/* convert to float list to time intervals list */
//				List<Float> reservationTimeFl = new ArrayList<>();
//				float fl;
//				for (String str : csse.getOnlineTime().split(";")) {
//					fl = Float.valueOf(str);
//					reservationTimeFl.add(fl);
//				}
//				List<ReservationTime> reservationTimes = Utils.convertTimeIntervalsList(reservationTimeFl);
//
//				
//				// check now time in reservation setting
//				isActive = false;
//				for (ReservationTime re : reservationTimes) {
//					int hoursFrom = (int) Math.floor(re.getFromDate());
//					int minutesFrom = (int) (((double) Math.round((re.getFromDate() - ((int) Math.floor(re.getFromDate()))) * 10) / 10)* 100);
//					int hoursTo = (int) Math.floor(re.getToDate());
//					int minutesTo = (int) (((double) Math.round((re.getToDate() - ((int) Math.floor(re.getToDate()))) * 10) / 10) * 100);
//
//					int nowTime = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE);
//
//					if (((hoursTo * 60 + minutesTo) >= nowTime) && ((hoursFrom * 60 + minutesFrom) <= nowTime)) {
//						isActive = true;
//						break;
//					}
//				}
//                
//				// check now time in reservation time or rest time
//				for (ReservationTime reCheck : reservationTimes) {
//                	int timeFromreCheck = (int) Math.floor(reCheck.getFromDate()) * 60 + (int) (((double)Math.round((reCheck.getFromDate() - ((int) Math.floor(reCheck.getFromDate()))) * 10) / 10) * 100);
//        			int timeToreCheck = (int) Math.floor(reCheck.getToDate()) * 60 +(int) (((double)Math.round((reCheck.getToDate() - ((int) Math.floor(reCheck.getToDate()))) * 10) / 10) * 100) ;
//        			int timeNowCheck = nowCheck.getTime().getHours() * 60 + nowCheck.getTime().getMinutes();
//        			if (timeNowCheck > timeFromreCheck && timeNowCheck < timeToreCheck) {
//        				timeFromreCheck = timeNowCheck;
//					}
//        			
//        			int restTime = 30 + ce.getCounselingRestTime();
//        			
//                	List<CounselingSessionEntity> listCheck = counselingSessionService.listCounselingSessionByFromAndToDate(timeFromreCheck , timeToreCheck, now.getTime(), ce.getId());
//                	if (listCheck.size() == 0) {
//						if ((timeToreCheck - timeFromreCheck) >= (30)) {
//							isActiveCheck = true;
//						} else {
//							isActiveCheck = false;
//						}
//					} else {
//						CounselingSessionEntity listCheck_0 = listCheck.get(0);
//						int timeFromreCheck_0 = listCheck_0.getStartTime().getHours() * 60 + listCheck_0.getStartTime().getMinutes();
//            			int timeToreCheck_0 = listCheck_0.getEndTime().getHours() * 60 + listCheck_0.getEndTime().getMinutes();
//						
//						if ((timeFromreCheck_0 - timeFromreCheck >= restTime) ) {
//							isActiveCheck = true;
//						} else {
//							isActiveCheck = false;
//						}
//						
//						if (listCheck.size() > 1) {
//							if (!isActiveCheck) {
//								for (int i = 1; i < listCheck.size(); i++) {
//									CounselingSessionEntity listCheck_i = listCheck.get(i);
//									int timeFromreCheck_i = listCheck_i.getStartTime().getHours() * 60 + listCheck_i.getStartTime().getMinutes();
//			            			int timeToreCheck_i = listCheck_i.getEndTime().getHours() * 60 + listCheck_i.getEndTime().getMinutes();
//			            			
//									timeFromreCheck = timeToreCheck_0;
//									timeFromreCheck_0 = timeFromreCheck_i;
//									timeToreCheck_0 = timeToreCheck_i;
//									if ((timeFromreCheck_0 - timeFromreCheck >= restTime) || (timeToreCheck - timeToreCheck_0 >= restTime) ) {
//										isActiveCheck = true;
//									} else {
//										isActiveCheck = false;
//									}
//									
//									if (isActiveCheck) {
//										break;
//									}
//								}
//							}
//						}
//					}
//                	
//                	if (isActiveCheck) {
//						break;
//					}
//				}
//			}
//        }
//		
//		if (isActive == true && isActiveCheck == true) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> detail(@PathVariable Integer id, HttpServletRequest httpRequest) {
		try {

			CounselorEntity c = counselorService.findByOne(id);
			List<CounselorBiographyEntity> list_biograpy = counselorBiographyService.listPagingByCounselorId(id);
			Page<UserCertificateEntity> list_cert = userCertificateService.listPagingByUserId(1, Integer.MAX_VALUE, id);

			String token = httpRequest.getHeader("token");
			UserItem u = AuthorizationToken.convertToObject(token);
			System.out.println("Mobile: " + JsonUtils.toString(u));

			HashMap<String, Object> ret = new HashMap<String, Object>();
			HashMap<String, Object> counselor = new HashMap<String, Object>();
			ret.put("counselor", counselor);

			counselor.put("thumbnailUrl", c.getThumbnailUrl());
			counselor.put("thumbnailUrl", c.getThumbnailUrl());
			counselor.put("counselorName", c.getUser().getFullname());
			counselor.put("point", c.getPoints());
			counselor.put("likes", c.getLikes());
			counselor.put("counselOnline", c.getCounselOnline());
			counselor.put("level", c.getLevel());
			counselor.put("introduce", c.getIntroduce());
			counselor.put("canComment", false);
			Calendar now = Calendar.getInstance();
			boolean isActive = false;
			Calendar nowCheck  = Calendar.getInstance();
			boolean isActiveCheck = false;

			// calculate is online status
			Map restResult = restTemplate.getForObject(ConstantUtils.getConfig("counselor.domain") + "/api/v1/public/counselor/status?counselorIds=" + c.getId(), Map.class);
			Map<String, Object> counselorsStatus = (Map) restResult.get("data");
			c.setStatusOnline(Integer.parseInt(String.valueOf(counselorsStatus.get(c.getId().toString()))));
			
			Integer statusOnline = 0;
			if ((c.getStatusOnline() == 1) && (c.getCounselOnline() == 1)) {
				statusOnline = counselorService.getStatusRealTimeCounseling(c.getId());
			}
			counselor.put("statusOnline", statusOnline);
			
//			if ((c.getStatusOnline() == 1) && (c.getCounselOnline() == 1)) {
//				Map<String, Object> reservationParams = new HashMap<>();
//				reservationParams.put("page", 1);
//				reservationParams.put("size", 10);
//				reservationParams.put("counselorId", c.getId());
//				reservationParams.put("reservationFrom",DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));
//				reservationParams.put("reservationTo",DataUtils.parseStringFromDate(now.getTime(), DataUtils.DatePattern.YYYYMMDD.getPattern()));
//
//				Page<CounselorScheduleSettingEntity> scheduleSettings = counselorScheduleSettingService.filter(reservationParams);
//				boolean isOnline = false;
//				try {
//					if(!scheduleSettings.getContent().isEmpty() && !scheduleSettings.getContent().get(0).getOnlineTime().isEmpty()){
//						TreeMap<Float, ReservedAndCounselingType> listStartTimeCounselingSSByDay = counselingSessionService.listStartTimeCounselingSSByDay(now.getTime(), c.getId());
//						isOnline = Utils.checkCounselorOnlineBusy(30, c.getCounselingRestTime(), now, scheduleSettings.getContent().get(0).getOnlineTime(), listStartTimeCounselingSSByDay, isOnline);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//				if (isOnline) {
//					counselor.put("statusOnline", 1);
//					c.setStatusOnline(1);
//				} else {
//					counselor.put("statusOnline", 0);
//					c.setStatusOnline(0);
//				}
//			} else {
//				counselor.put("statusOnline", 0);
//				c.setStatusOnline(0);
//			}

			String biography = "<ul style='list-style:none; padding-left:20px;'>";
			for (int i = 0; i < list_biograpy.size(); i++) {
				if (i < 5) {
					biography += "<li>&middot; " + list_biograpy.get(i).getContent() + "</li>";
				} else {
					break;
				}
			}
			if (list_cert.getContent() != null) {
				for (int i = 0; i < list_cert.getContent().size(); i++) {
					if (i < 4) {
						biography += "<li>&middot; " + list_cert.getContent().get(i).getName() + "</li>";
					} else {
						break;
					}
				}
			}
			if (c.getCounselNumber() != null) {
				biography += "<li>&middot; 상담 " + c.getCounselNumber() + "</li>";
			}

			biography += "</ul>";
			counselor.put("feeCounselor", c.getFeeCounselor());
			counselor.put("feeCounselor50minutes", c.getFeeCounselor50Minutes());
			if (c.getCounseling50Minutes() == null) {
				counselor.put("counseling_50_minutes", 0);
			} else {
				counselor.put("counseling_50_minutes", c.getCounseling50Minutes());
			}

			counselor.put("biography", biography);
			counselor.put("counselingSessionIdNoComment", 0);
			if (u != null) {
				User user = userService.findById(u.getId());
				counselor.put("voucherNumber", voucherUserService.totalVoucherNoUsedByUserId(user.getId()));

				Integer counselingSessionIdNoComment = counselingSessionService.getNewestCounselingSessionNotStatisficEvolution(user.getId(),id);

				if (counselingSessionIdNoComment != null) {
					counselor.put("canComment", true);
					counselor.put("counselingSessionIdNoComment", counselingSessionIdNoComment);
				}
			}

			ret.put("counselor", counselor);
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL(e.getMessage());
		}
	}

	@RequestMapping(value = "/counseling/checkpayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> checkpayment(@RequestParam Map<String, Object> params,
			HttpServletRequest httpRequest) {
		String token = httpRequest.getHeader("token");
		UserItem user = AuthorizationToken.convertToObject(token);

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		// TODO create real payment
		Map<String, Object> result = new HashMap<>();
		Calendar end = Calendar.getInstance();
		Integer counselorId = Integer.parseInt(params.get("counselorId").toString());
		Integer typeCounseling = Integer.parseInt(params.get("typeCounseling").toString());
		Counselor c = counselorService.findById(counselorId);
		if (c.getCounselingRestTime() == null) {
			end.add(Calendar.MINUTE, 35);
		} else {
			end.add(Calendar.MINUTE, (30 + c.getCounselingRestTime()));
		}
		User u = userService.findById(user.getId());

		int exists = counselingSessionService.countBy(new Date(), end.getTime(), counselorId);

		Integer countVoucher = voucherUserService.totalVoucherNoUsedByUserId(u.getId());

		if (exists > 0) {
			// this time is booked
			result.put("exists", 1);
			result.put("currentCoin", u.getCoin());
			result.put("currentVoucher", countVoucher);
			return SUCCESS(result, "현재 상담사가 상담중입니다.");
		}
		result.put("exists", 0);
		VoucherUser v = voucherUserService.getVoucherCanUse(user.getId(), typeCounseling);

		result.put("currentCoin", u.getCoin());
		result.put("currentVoucher", countVoucher);
		Integer fee = 0;
		if (u.getRoleId() == ApplicationDefine.USER_ROLE_MONITERING) {
			fee = 0;
		} else if (typeCounseling == 1) {
			fee = c.getFeeCounselor();
		} else {
			fee = c.getFeeCounselor50Minutes();
		}

		if (v != null || fee <= u.getCoin()) {
			// enough money and voucher
			result.put("payment", 1);
			//checking mindcheck 
			UserQuestionaire userQuestionaire = userQuestionaireService.getHistoryMindcheck(u.getId());
			if (userQuestionaire != null) {
				Date createDate = userQuestionaire.getCreateDate();
				Date getDate3MonthsAgo = Utils.getDate3MonthsAgo();
				SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
//				String createDateConvert = s.format(createDate);
//				String currentDateConvert = s.format(currentDate);
				if (createDate.after(getDate3MonthsAgo) && createDate.before(currentDate)) {
					result.put("showPopup", 3);
//					if (createDateConvert.equals(currentDateConvert)) {
//						result.put("showPopup", 5);
//					} else {
//						result.put("showPopup", 3);
//					}
				} else {
					result.put("showPopup", 4);
				}
			} else {
				result.put("showPopup", 4);
			}
			
			result.put("counselorId", c.getId());
			result.put("typeCounseling", typeCounseling);
			return SUCCESS(result);
		} else {
			// not enough money and voucher
			result.put("payment", 0);
		}

		return SUCCESS(result);
	}

	@RequestMapping(value = "/counseling/complete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> counselingRequest(@RequestParam Map<String, Object> params,
			HttpServletRequest httpRequest) {
		String token = httpRequest.getHeader("token");
		UserItem user = AuthorizationToken.convertToObject(token);

		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		Map<String, Object> room = rtcService
				.createRoom(Collections.singletonMap("roomTitle", UUID.randomUUID().toString()));
		// TODO create real payment
		Map<String, Object> result = new HashMap<>();
		Calendar end = Calendar.getInstance();
		Calendar endAndRestTime = Calendar.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");

		Integer counselorId = Integer.parseInt(params.get("counselorId").toString());
		Integer typeCounseling = Integer.parseInt(params.get("typeCounseling").toString());
		String osName = "android";
		try {
			osName = String.valueOf(params.get("osName"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		Counselor c = counselorService.findById(counselorId);
		if (typeCounseling == 1) {
			end.add(Calendar.MINUTE, 30);
			endAndRestTime.add(Calendar.MINUTE, 30);
		} else {
			end.add(Calendar.MINUTE, 50);
			endAndRestTime.add(Calendar.MINUTE, 50);
		}
		System.out.println("endTime:" + s.format(end.getTime()));

		if (c.getCounselingRestTime() == null) {
			endAndRestTime.add(Calendar.MINUTE, 15);
		} else {
			endAndRestTime.add(Calendar.MINUTE, (5 + c.getCounselingRestTime()));
		}

		User u = userService.findById(user.getId());

		Integer countVoucher = voucherUserService.totalVoucherNoUsedByUserId(u.getId());
		int exists = counselingSessionService.countBy(new Date(), endAndRestTime.getTime(), counselorId);
		if (exists > 0) {
			result.put("exists", 1);
			result.put("currentCoin", u.getCoin());
			result.put("currentVoucher", countVoucher);
			return SUCCESS(result);
		}
		result.put("exists", 0);

		VoucherUser v = voucherUserService.getVoucherCanUse(u.getId(), typeCounseling);

		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setUserId(user.getId());
		paymentHistory.setStatus((short) 1);
		paymentHistory.setTypeUse(ApplicationDefine.PaymentTypeUse.Consultation.getCode());
		paymentHistory.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode());
		paymentHistory.setCreateDate(new Date());
		paymentHistory.setUpdateDate(new Date());
		if (v != null) {
			paymentHistory.setTypeCoin(ApplicationDefine.Payment_TypeCoin.Voucher.getCode());
			paymentHistory.setCoin(0);
			paymentHistory.setCurrentCoin(u.getCoin());
			paymentHistory.setVoucherUserId(v.getId());
			try {
				Voucher voucher = voucherService.findById(v.getVoucherId());
				paymentHistory.setCoin(voucher.getFee());
			} catch (Exception e) {
				// TODO: handle exception
			}

			v.setStatusUse(ApplicationDefine.VoucherUser_Status.Used.getCode());
			v.setUpdateDate(new Date());
			v.setUseVoucherDate(new Date());

			voucherUserService.update(v);
			userService.updateVoucherNumberByUserId(user.getId());
		} else {
			Integer fee = 0;
			if (u.getRoleId() == ApplicationDefine.USER_ROLE_MONITERING) {
				fee = 0;
			} else if (typeCounseling == 1) {
				fee = c.getFeeCounselor();
			} else {
				fee = c.getFeeCounselor50Minutes();
			}

			if (fee > u.getCoin()) {
				result.put("payment", 0);
				result.put("currentCoin", u.getCoin());
				result.put("currentVoucher", countVoucher);
				return SUCCESS(result);
			} else {
				int coin = 0;
				int currentCoin = 0;

				if (u.getRoleId() == ApplicationDefine.USER_ROLE_MONITERING) {
					currentCoin = u.getCoin();
				} else {
					Integer[] userCoin = userService.updateCoin(u, -fee);
					coin = userCoin[0];
					currentCoin = userCoin[1];
				}

				paymentHistory.setCoin(coin);
				paymentHistory.setCurrentCoin(currentCoin);
				paymentHistory.setTypeCoin(ApplicationDefine.Payment_TypeCoin.Coin.getCode());
			}
		}

		paymentHistory.setReason(ApplicationDefine.Payment_Reson.Counseling.getCode());
		PaymentHistory payment = paymentHistoryService.create(paymentHistory);
		result.put("currentCoin", payment.getCurrentCoin());
		result.put("currentVoucher", countVoucher);
		CounselingSession session = new CounselingSession();
		session.setCounselorId(counselorId);
		session.setUserId(user.getId());
		session.setStartTime(currentDate);
		System.out.println("endTime2222:" + s.format(end.getTime()));
		session.setEndTime(end.getTime());
		session.setEntryCounselorTime(null);
		session.setEntryUserTime(null);
		session.setExitUserTime(null);
		session.setExitCounselorTime(null);
		session.setCreateDate(currentDate);
		session.setRoomId(((Map<String, Object>) room.get("room")).get("roomId").toString());
		session.setStatus((short) 1);
		session.setCounselType((short) 1);
		session.setPaymentId(payment.getId());
		session.setOsName(osName);
		if (typeCounseling == 1) {
			session.setCounselingTimeType((short) 30);
		} else {
			session.setCounselingTimeType((short) 50);
		}
		session.setDisconnectCount(0);
		CounselingSession counselingSession = counselingSessionService.create(session);

		result.put("sessionId", counselingSession.getId());
		result.put("roomId", counselingSession.getRoomId());
		result.put("counselorId", counselingSession.getCounselorId());

		applicationEventPublisher.publishEvent(new CounselingSessionRealtimeAddedEvent(this,
				counselingSession.getUserId(), counselingSession.getCounselorId(), counselingSession.getId()));

		return SUCCESS(result);
	}

	@RequestMapping(value = "/counseling", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> counseling(@RequestParam Map<String, Object> params,
			HttpServletRequest httpRequest) {
		String token = httpRequest.getHeader("token");
		UserItem user = AuthorizationToken.convertToObject(token);
		if (user != null) {
			// Calendar _65minFromNow = Calendar.getInstance();
			// _65minFromNow.add(Calendar.HOUR_OF_DAY, 1);
			// _65minFromNow.add(Calendar.MINUTE, 5);
			//
			// Integer isBusy =
			// counselingSessionService.countCounselingSessionHaveCounselYet(user.getId(),
			// _65minFromNow.getTime());
			// if (isBusy > 0) {
			// return FAIL("해당 상담사는 현재 실시간 상담이 불가능합니다.");
			// }

			Map<String, Object> result = new HashMap<>();
			Integer counselingId = Integer.parseInt(params.get("counselingSessionId").toString());
			Integer reconnectCount = Integer.parseInt(params.get("reconnectCount").toString());
			CounselingSession c = counselingSessionService.findById(counselingId);
			if(c.getStatus().equals(ApplicationDefine.CounselingSessionStatus.COUNSELD.getCode())){
				return FAIL("상담이 종료되었습니다.");	
			}else{
				
				if (c.getDisconnectCount() < reconnectCount) {
					c.setDisconnectCount(reconnectCount);
					counselingSessionService.update(c);
				}
				
				result.put("roomId", c.getRoomId());
				return SUCCESS(result);	
			}
		} else {
			return FAIL("You need login!");
		}

	}

	@RequestMapping(value = "/counseling/room", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> newRoom(@RequestParam Map<String, Object> params) {
		Integer counselingSessionId = Integer.parseInt(params.get("counselingSessionId").toString());
		Integer reconnectCount = Integer.parseInt(params.get("reconnectCount").toString());
		CounselingSessionEntity cse = counselingSessionService.findOne(counselingSessionId);
		try {
			if (cse != null && cse.getDisconnectCount() < reconnectCount) {
				cse.setDisconnectCount(reconnectCount);
				cse = counselingSessionService.saveOrUpdate(cse);
			}
			HashMap<String, Object> ret = new HashMap<>();
			ret.put("roomId", cse.getRoomId());

			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("fail get room id");
		}
	}

	@RequestMapping(value = "/getCounselingTimeType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> getCounselingTimeType(@RequestParam Map<String, Object> params) {
		Integer counselorId = Integer.parseInt(params.get("counselorId").toString());
		try {
			Calendar endAndRestTime30 = Calendar.getInstance();
			Calendar endAndRestTime50 = Calendar.getInstance();
			Calendar now = Calendar.getInstance();
			CounselorScheduleSetting schedule = counselorScheduleSettingService
					.findCounselorScheduleSettingByReservationDate(endAndRestTime30.getTime(), counselorId);
			
			HashMap<String, Object> ret = new HashMap<>();
			if (schedule != null && schedule.getOnlineTime() != null) {
				Counselor c = counselorService.findById(counselorId);
				endAndRestTime30.add(Calendar.MINUTE, 30);
				endAndRestTime50.add(Calendar.MINUTE, 50);

				if (c.getCounselingRestTime() == null) {
					endAndRestTime30.add(Calendar.MINUTE, 15);
					endAndRestTime50.add(Calendar.MINUTE, 15);
				} else {
					endAndRestTime30.add(Calendar.MINUTE, (5 + c.getCounselingRestTime()));
					endAndRestTime50.add(Calendar.MINUTE, (5 + c.getCounselingRestTime()));
				}
				int exists = counselingSessionService.countByNow(now.getTime(), counselorId);
				int exists30 = counselingSessionService.countBy(now.getTime() , endAndRestTime30.getTime(), counselorId);
				int exists50 = counselingSessionService.countBy(now.getTime() , endAndRestTime50.getTime(), counselorId);

				List<Float> listReservationTime = new ArrayList<>();
				Float f1;
				for (String str : schedule.getOnlineTime().split(";")) {
					f1 = Float.valueOf(str);
					listReservationTime.add(f1);
				}

				List<ReservationTime> reservationTimes = Utils.convertRealTimeIntervalsToList(listReservationTime, c.getCounselingRestTime());
				boolean isActive50 = false;
				boolean isActive30 = false;
				
				Float fromTime = Utils.convertHourToFloat(now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE)); 
 				Float toTime30 = Utils.convertHourToFloat(endAndRestTime30.get(Calendar.HOUR_OF_DAY), endAndRestTime30.get(Calendar.MINUTE)); 
				Float toTime50 = Utils.convertHourToFloat(endAndRestTime50.get(Calendar.HOUR_OF_DAY), endAndRestTime50.get(Calendar.MINUTE)); 
				for (ReservationTime re : reservationTimes) {
					if (re.getFromDate() <= fromTime && fromTime <= re.getToDate() && re.getFromDate() <= toTime30 && toTime30 <= re.getToDate() ) {
						isActive30 = true;
					}

					if (re.getFromDate() <= fromTime && fromTime <= re.getToDate() && re.getFromDate() <= toTime50 && toTime50 <= re.getToDate() ) {
						isActive50 = true;
					}
				}
				
				System.out.println(new Date());
				if (exists30 == 0 & isActive30) {
					ret.put("canCounseling30", "1");
				} else {
					ret.put("canCounseling30", "0");
				}

				if (exists50 == 0 & isActive50) {
					if(c.getCounseling50Minutes().equals((short)0)){
						ret.put("canCounseling50", "0");
					}else{
						ret.put("canCounseling50", "1");
					}
				} else {
					ret.put("canCounseling50", "0");
				}
				
				if(exists > 0){
					ret.put("exists", "1");
				}else{
					ret.put("exists", "0");
				}
			}else{
				ret.put("exists", "0");
				ret.put("canCounseling30", "0");
				ret.put("canCounseling50", "0");
			}
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("Error:" + e.getMessage());
		}
	}


}
