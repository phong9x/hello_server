/*
 * Created on 8 thg 12 2016 ( Time 10:33:06 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.BanAccountHistory;
import org.trams.hello.bean.BusinessVoucher;
import org.trams.hello.bean.PaymentHistory;
//--- Entities
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.VoucherUser;
import org.trams.hello.bean.jpa.BanAccountHistoryEntity;
import org.trams.hello.bean.jpa.BusinessEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.VoucherUserEntity;
import org.trams.hello.business.service.BanAccountHistoryService;
import org.trams.hello.business.service.BusinessService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.PaymentHistoryService;
import org.trams.hello.business.service.QuestionService;
import org.trams.hello.business.service.QuestionnaireService;
import org.trams.hello.business.service.TestResultService;
import org.trams.hello.business.service.UserBusinessService;
import org.trams.hello.business.service.UserQuestionaireService;
import org.trams.hello.business.service.UserRoleService;
//--- Services 
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.VoucherUserService;
import org.trams.hello.common.mail.Mail;
import org.trams.hello.common.mail.Mail.EmailTemplate;
import org.trams.hello.web.bean.search.SearchMember;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'User' management.
 */
@Controller
@RequestMapping("/admin/user/member")
public class MemberAdminController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "user";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE = 30;

	private static String nav = "member";

	// --- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE = "admin/user/member/create";
	private static final String JSP_TEST_RESULT = "admin/user/member/test_result";
	private static final String JSP_LIST = "admin/user/member/list";
	private static final String JSP_EDIT = "admin/user/member/edit";
	private static final String JSP_DETAIL = "admin/user/member/detail";
	private static final String JSP_LOGIN = "redirect:/login";

	// --- Main entity service
	@Resource
	private UserService userService; // Injected by Spring
	// --- Other service(s)
	@Resource
	private UserRoleService userRoleService; // Injected by Spring

	@Resource
	private BusinessService businessService; // Injected by Spring

	@Resource
	private QuestionnaireService questionnaireService;

	@Resource
	private QuestionService questionService;

	@Resource
	private TestResultService testResultService;

	@Resource
	private UserQuestionaireService userQuestionaireService;

	@Resource
	private PaymentHistoryService paymentHistoryService;

	@Resource
	private CounselingSessionService counselingSessionService;

	@Resource
	private VoucherUserService voucherUserService;

	@Resource
	private BanAccountHistoryService banAccountHistoryService;
	
	@Resource
	private UserBusinessService userBusinessService;
	
	@Resource
	private VoucherService voucherService;

	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public MemberAdminController() {
		super(MemberAdminController.class, MAIN_ENTITY_NAME);
		log("UserAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@ModelAttribute SearchMember searchMember,
			@RequestParam(value ="page", defaultValue="1") Integer page,
			HttpServletRequest request,
			HttpSession session, Model model) {
		Page<UserEntity> listPage = null;
		listPage = userService.filter( searchMember, page, PAGE_SIZE);
		List<BusinessEntity> list_business = businessService.findAllOrderBy();
		model.addAttribute(MAIN_LIST_NAME, listPage);
		model.addAttribute("list_business", list_business);
		model.addAttribute("s", searchMember);
		model.addAttribute("activePage", nav);
		new Pager<UserEntity>(listPage).setSetting(model, request);
		return JSP_LIST;
	}
 
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(Model model, HttpSession session, 
			@PathVariable("id") Integer id) {
		userService.updateHeartByUserId(id);
		UserEntity user = userService.findOne(id);
		List<BusinessVoucher> list_voucher = voucherService.listBussinessVoucherByUserId(id);
		model.addAttribute("list_voucher", list_voucher);
		List<BanAccountHistoryEntity> list_ban = banAccountHistoryService.listByUserId(id);
		if (list_ban.size() > 0) {
			model.addAttribute("list_ban", list_ban);
		}
		Calendar c = Calendar.getInstance();
		List<VoucherUserEntity> listPage = voucherUserService.listPagingByUserIdAndStatus(id, (short) 0);
		model.addAttribute("total_counsel_have_yet",counselingSessionService.countCounselingSessionHaveCounselYet(id, new Date()));
		model.addAttribute("total_payment", paymentHistoryService.totallistPagingByUserId(id));

		model.addAttribute("total_counsel", counselingSessionService.countCounselingSession(id));
		model.addAttribute("total_check", userQuestionaireService.totalTestMindCheckByUserId(id));
		
//		if (user.getBusiness() != null) {
//			List<ContractEntity> list_contract = contractService.findByUserId(id, new Date());
//			model.addAttribute("m", list_contract);
//		}
//		List<UserBusinessCustom> list_business = userBusinessService.listBusinessByUserId(id);
//		if (user.getDayOfBirth() != null) {
//			Calendar dayOfBirth = c.getInstance();
//			dayOfBirth.setTime(user.getDayOfBirth());
//			model.addAttribute("yearBirth", dayOfBirth.get(Calendar.YEAR));
//			model.addAttribute("monthBirth", dayOfBirth.get(Calendar.MONTH) + 1);
//			model.addAttribute("dayBirth", dayOfBirth.get(Calendar.DATE));
//		}
//		if(user.getBusiness() != null && user.getBusiness().getParentBusinessId() != null){
//			model.addAttribute("parentBusiness", businessService.findById(user.getBusiness().getParentBusinessId()));
//		}
		
		model.addAttribute("nowYear", c.get(Calendar.YEAR));
		model.addAttribute("m", user);
		model.addAttribute("list", listPage);
		model.addAttribute("activePage", nav);
		return JSP_DETAIL;
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
	public String detail_post(Model model, HttpSession session, @PathVariable("id") Integer id,
			@RequestParam(value = "cancel_banned", defaultValue = "0") Integer cancel_banned,
			@RequestParam(value = "banned_account", defaultValue = "0") Integer banned_account,
			@RequestParam(value = "delete_account", defaultValue = "0") Integer delete_account,
			@RequestParam(value = "delete_voucher", defaultValue = "0") Integer delete_voucher,
			@RequestParam(value = "delete_voucher_id", defaultValue = "") Integer[] delete_voucher_id,
			@RequestParam(value = "edit", defaultValue = "0") Integer edit,
			@RequestParam(value = "reason", defaultValue = "") String reason,
			@RequestParam(value = "withdrawalReason", defaultValue = "") String withdrawalReason,
			@RequestParam(value = "gender", defaultValue = "1") Short gender,
			@RequestParam(value = "fullname", defaultValue = "") String fullname,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "yearBirth", defaultValue = "") Integer year,
			@RequestParam(value = "monthBirth", defaultValue = "") Integer month,
			@RequestParam(value = "dayBirth", defaultValue = "") Integer day) {
		User user = userService.findById(id);
		UserItem admin = Login.getAdminLogin(session);
		if (cancel_banned == 1 && user.getBannedNumber() > 0) {
			user.setBannedNumber(user.getBannedNumber() - 1);
			banAccountHistoryService.deleteFirstByUser(id);
			user.setStatusActive(ApplicationDefine.UserStatusActive.NORMAL.getCode());
			userService.update(user);
		} else if (banned_account == 1) {
			BanAccountHistory b = new BanAccountHistory();
			b.setAdminBanId(admin.getId());
			b.setCreateDate(new Date());
			b.setUserId(id);
			b.setReason(reason);
			banAccountHistoryService.create(b);
			List<BanAccountHistoryEntity> list = banAccountHistoryService.listByUserId(id);
			user.setBannedNumber(list.size());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 7);
			user.setBannedLastTime(new Date());
			user.setStatusActive(ApplicationDefine.UserStatusActive.BANNED.getCode());
			user.setExpiredBannedTime(c.getTime());
			userService.update(user);
			HashMap<String, Object> params = new HashMap<>();
			params.put("fullname", user.getFullname());
			params.put("email", user.getEmail());
			params.put("banNumber", user.getBannedNumber());
			String reasonStr ="";
			for (BanAccountHistoryEntity i : list) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(i.getCreateDate());
				reasonStr +="<p style='padding-left: 10px;margin: 0;'>"+i.getReason()+" (발생 일시 : "+cal.get(Calendar.YEAR)+"년 "+(cal.get(Calendar.MONTH)+1)+"월 "+cal.get(Calendar.DAY_OF_MONTH)+"일)</p>";
			}
			
			params.put("reason", reasonStr);
			Mail.sendEmailTemplate(user.getEmail(), params, EmailTemplate.BAN_ACCOUNT_MEMBER, "[Hello]"+user.getFullname()+ApplicationDefine.EmailSubject.BAN_ACCOUNT_MEMBER.getCode());
		} else if (delete_account == 1) {
			Integer totalCounseling = counselingSessionService.countCounselingSessionHaveCounselYet(id, new Date());
			if(totalCounseling == 0){
				//delete hello coin
				userService.updateCoinByUserId(id, user.getCoin(), ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode() , ApplicationDefine.PaymentTypeUse.Manual_deduction.getCode(), null);
				//delete heart
				userService.updateHeartByUserId(id, user.getHeart(), ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode() , ApplicationDefine.PaymentHeart_Status.WithdrawAccount.getCode(), null);
				//delete voucher
				voucherUserService.deleteVouchertByUserId(id);
				
				Calendar c = Calendar.getInstance();
				user.setVoucherNumber(0);
				user.setHeart(0);
				user.setCoin(0);
				user.setStatusActive(ApplicationDefine.UserStatusActive.WITHDRAWAL.getCode());
				user.setWithdrawalDate(new Date());
				user.setTypeWithdrawalReason((short)3);
				user.setHeart(0);
				user.setIsDelete(1);
				userService.update(user);
			}
			
			
			
		} else if(delete_voucher > 0){
			if(delete_voucher_id.length > 0){
				voucherUserService.deleteVouchert(delete_voucher_id);
			}
			userService.updateVoucherNumberByUserId(user.getId());
		}else{
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setGender(gender);
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, (month-1));
			c.set(Calendar.DATE, day);
			user.setDayOfBirth(c.getTime());
			userService.update(user);
		}
		return "redirect:/" + JSP_DETAIL + "/" + id;
	}

	@RequestMapping("/updateCoin/{id}")
	public String updateCoin(Model model, HttpSession session, 
			@PathVariable("id") Integer id,
			
			@RequestParam(value = "update", defaultValue = "") String update,
			@RequestParam(value = "type_hellocoin", defaultValue = "") Integer type_hellocoin,
			@RequestParam(value = "coin", defaultValue = "") Integer coin,
			@RequestParam(value = "payment_reason_hellocoin", defaultValue = "") String payment_reason_hellocoin,
			
			@RequestParam(value = "type_heart", defaultValue = "") Integer type_heart,
			@RequestParam(value = "heart", defaultValue = "") Integer heart,
			@RequestParam(value = "payment_reason_heart", defaultValue = "") String payment_reason_heart,
			
			@RequestParam(value = "type_heart_voucher", defaultValue = "") Integer type_heart_voucher,
			@RequestParam(value = "counseling_number", defaultValue = "") Integer counseling_number,
			@RequestParam(value = "payment_reason_heart_voucher", defaultValue = "") String payment_reason_heart_voucher,
			
			@RequestParam(value = "businessVoucherId", defaultValue = "") Integer businessVoucherId,
			@RequestParam(value = "numberBusineesVoucher", defaultValue = "") Integer numberBusineesVoucher

	) {
		try {
			User user = userService.findById(id);
			UserItem admin = Login.getAdminLogin(session);
			if (update.equals("coin")) {
				PaymentHistory r = new PaymentHistory();
				r.setCoin(coin);
				if(user.getCoin() == null){
					user.setCoin(0);
				}
				if (type_hellocoin == 0) {
					r.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Recharge.getCode());
					r.setTypeUse(ApplicationDefine.Recharge_TypeUse.ManualPayments.getCode());
					user.setCoin(user.getCoin()+coin);
					r.setCurrentCoin(user.getCoin());
				} else if (type_hellocoin == 1) {
					r.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode());
					r.setTypeUse(ApplicationDefine.PaymentTypeUse.Manual_deduction.getCode());
					Integer minusCoin = user.getCoin() - coin;
					if(minusCoin <= 0){
						r.setCoin(user.getCoin());
						user.setCoin(0);
					}else{
						user.setCoin(minusCoin);
					}
				}
				r.setCurrentCoin(user.getCoin());
				r.setStatus((short)1);
				r.setAdminId(admin.getId());
				r.setCreateDate(new Date());
				r.setMoney(0);
				r.setReason(payment_reason_hellocoin);
				r.setUpdateDate(new Date());
				r.setUserId(id);
				r.setTypeCoin((short) 1);
				paymentHistoryService.create(r);
			}else if (update.equals("heart")) {
				PaymentHistory r = new PaymentHistory();
				r.setCoin(coin);
				if(user.getHeart() == null){
					user.setHeart(0);
				}
				if (type_heart == 0) {
					r.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Recharge.getCode());
					r.setTypeUse(ApplicationDefine.Recharge_TypeUse.ManualPayments.getCode());
					user.setHeart(user.getHeart()+heart);
				} else if (type_heart == 1) {
					r.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Payment.getCode());
					r.setTypeUse(ApplicationDefine.PaymentTypeUse.Manual_deduction.getCode());
					Integer minusHeart = user.getHeart() - heart;
					if(minusHeart <= 0){
						r.setCoin(user.getCoin());
						user.setHeart(0);
					}else{
						user.setHeart(minusHeart);
					}
				}
				r.setStatus((short)1);
				r.setAdminId(admin.getId());
				r.setCoin(heart);
				r.setCreateDate(new Date());
				r.setMoney(0);
				r.setUpdateDate(new Date());
				r.setUserId(id);
				r.setTypeCoin((short) 2);
				paymentHistoryService.create(r);
			}else if (update.equals("heart_voucher")){
				if (counseling_number > 0) {
					for (int i = 0; i < counseling_number; i++) {
						Voucher hert_voucher = voucherService.findByTypeHeart((short)2);
						VoucherUser v = new VoucherUser();
						v.setVoucherId(hert_voucher.getId());
						v.setCreateDate(new Date());
						v.setStatusUse((short)0);
						v.setFromDate(new Date());
						Calendar c = Calendar.getInstance();
						c.add(Calendar.DAY_OF_YEAR, hert_voucher.getDateNumber());
						v.setToDate(c.getTime());
						v.setUpdateDate(new Date());
						v.setUserId(id);
						v.setAdminId(admin.getId());
						v.setReason(payment_reason_heart_voucher);
						voucherUserService.create(v);
					}
				}
				user.setVoucherNumber(voucherUserService.totalVoucherNoUsedByUserId(user.getId()));
				userService.updateVoucherNumberByUserId(user.getId());
			}else if (update.equals("businessvoucher") && businessVoucherId != null) {
				if(numberBusineesVoucher > 0){
					for (int i = 0; i < numberBusineesVoucher; i++) {
						Voucher vc = voucherService.findById(businessVoucherId);
						VoucherUser v = new VoucherUser();
						v.setCreateDate(new Date());
						v.setStatusUse((short)0);
						if(vc.getTypeTime() == 1){
							v.setFromDate(vc.getFromTime());
							v.setToDate(vc.getToTime());
						}else{
							v.setFromDate(new Date());
							Calendar c = Calendar.getInstance();
							c.add(Calendar.DAY_OF_YEAR, vc.getDateNumber());
							v.setToDate(c.getTime());
						}
						v.setVoucherId(businessVoucherId);
						v.setUpdateDate(new Date());
						v.setUserId(id);
						v.setAdminId(admin.getId());
						voucherUserService.create(v);
					}
				}
				user.setVoucherNumber(voucherUserService.totalVoucherNoUsedByUserId(user.getId()));
			}
			userService.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/" + JSP_DETAIL + "/" + id;
	}

	@RequestMapping(value = "/member_voucher/delete", method = RequestMethod.POST)
	public String voucher(Model model, HttpSession session, @PathVariable("id") Integer id,
			@RequestParam(value = "voucher_id", defaultValue = "") Integer[] voucher_id) {

		voucherUserService.deleteVouchert(voucher_id);
		return "redirect:/" + JSP_DETAIL + "/" + id;
	}

	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(HttpSession session, @PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);
		try {
			model.addAttribute("item", user);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch (Exception e) {
			return JSP_LIST;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@RequestParam(value = "edit", defaultValue = "0") Integer edit, @ModelAttribute User item,
			HttpSession session, @PathVariable("id") Integer id, Model model) {
		User user = userService.findById(id);
		try {
			user.setUsername(item.getUsername());
			user.setPassword(item.getPassword());
			user.setPasswordTemp(item.getPasswordTemp());
			user.setNickname(item.getNickname());
			user.setFullname(item.getFullname());
			user.setGender(item.getGender());
			user.setDayOfBirth(item.getDayOfBirth());
			user.setPhone(item.getPhone());
			user.setEmail(item.getEmail());
			user.setAddress(item.getAddress());
			user.setToken(item.getToken());
			user.setFcmToken(item.getFcmToken());
			user.setPushFcmToken(item.getPushFcmToken());
			user.setOsName(item.getOsName());
			user.setOsVersion(item.getOsVersion());
			user.setDeviceId(item.getDeviceId());
			user.setDeviceVersion(item.getDeviceVersion());
			user.setDeviceName(item.getDeviceName());
			user.setConfirmBusinessTime(item.getConfirmBusinessTime());
			user.setLastLogin(item.getLastLogin());
			user.setBannedLastTime(item.getBannedLastTime());
			user.setBannedNumber(item.getBannedNumber());
			user.setChargeNumber(item.getChargeNumber());
			user.setTestPsychologicalNumber(item.getTestPsychologicalNumber());
			user.setHeart(item.getHeart());
			user.setCoin(item.getCoin());
			user.setOnline(item.getOnline());
			user.setWithdrawalReason(item.getWithdrawalReason());
			user.setTypeWithdrawalReason((short)3);
			user.setIsDelete(item.getIsDelete());
			user.setUpdateDate(new Date());
			user = userService.update(user);
			model.addAttribute("item", user);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/" + JSP_EDIT + "/" + id;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model model, HttpSession session, @PathVariable("id") Integer id) {

		userService.delete(id);
		return "redirect:/" + JSP_LIST;
	}

}