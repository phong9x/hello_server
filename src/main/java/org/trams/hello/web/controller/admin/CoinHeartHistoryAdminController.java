/*
 * Created on 15 thg 2 2017 ( Time 16:45:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//--- Entities
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PaymentHistory;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.VoucherUser;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.CoinHistory;
import org.trams.hello.business.service.CategoryService;
//--- Services 
import org.trams.hello.business.service.PaymentHistoryService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.VoucherUserService;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'CoinHeartHistory' management.
 */
@Controller
@RequestMapping("/admin/payment_voucher")
public class CoinHeartHistoryAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "coinHistory";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "coin";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_COIN_LIST   = "admin/payment_voucher/coinHistory/list";
	
	private static final String JSP_HEART_LIST   = "admin/payment_voucher/heartHistory/list";
	
	private static final String JSP_HEART_VOUCHER_LIST   = "admin/payment_voucher/heart_voucher/list";
	
	@Resource
    private PaymentHistoryService paymentHistoryService;
	
	@Resource
    private VoucherUserService voucherUserService;
	
	@Resource
    private VoucherService voucherService;
	
	//--- Other service(s)
	@Resource
    private UserService userService; // Injected by Spring
	
	@Resource
    private CategoryService categoryService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public CoinHeartHistoryAdminController() {
		super(CoinHeartHistoryAdminController.class, MAIN_ENTITY_NAME );
		log("CoinHeartHistoryAdminController created.");
	}

	@RequestMapping(value ="/coinHistory/list", method = RequestMethod.GET)
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="id") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="startTime",defaultValue="") String startTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="payment_reason",defaultValue="") Short[] payment_reason,
			@RequestParam(value="recharge_reason",defaultValue="") Short[] recharge_reason,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		
		if(key==null){
			key="";
		}
		
		PageCustom<CoinHistory> listPage = paymentHistoryService.listCoinManegerment(startTime, endTime, payment_reason, recharge_reason, type, key, page, PAGE_SIZE);
		List<CategoryEntity> list_payment = categoryService.findByType((short)7);
		List<CategoryEntity> list_recharge = categoryService.findByType((short)9);
		
		if(listPage.getOther().get(0) != null){
			model.addAttribute("totalAddTable", Long.valueOf(listPage.getOther().get(0).toString()) );
		}
		if(listPage.getOther().get(1) != null){
			model.addAttribute("totalDeducationTable", Long.valueOf(listPage.getOther().get(1).toString()));
		}
		model.addAttribute("list_payment", list_payment);
		model.addAttribute("list_recharge", list_recharge);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("payment_reason", payment_reason);
		model.addAttribute("recharge_reason", recharge_reason);
		model.addAttribute("totalAdd", paymentHistoryService.totalAddCoinBy_Type(ApplicationDefine.Payment_TypeCoin.Coin.getCode()));
		model.addAttribute("totalDeducation", paymentHistoryService.totalDeductionCoinBy_Type(ApplicationDefine.Payment_TypeCoin.Coin.getCode()));
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("now", new Date());
		model.addAttribute("activePage", nav);
		new Pager<CoinHistory>(listPage).setManualSetting(model, request);
		return JSP_COIN_LIST;
	}
	
	
	@RequestMapping(value ="/coinHistory/list", method = RequestMethod.POST)
	public String list_post(
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "update", defaultValue = "") String update,
			@RequestParam(value = "type_hellocoin", defaultValue = "") Integer type_hellocoin,
			@RequestParam(value = "coin", defaultValue = "") Integer coin,
			@RequestParam(value = "payment_reason_hellocoin", defaultValue = "") String payment_reason_hellocoin,
			HttpSession session,
			Model model) {
		
		return "redirect:/"+ JSP_COIN_LIST;
	}

	
	@RequestMapping(value ="/heartHistory/list", method = RequestMethod.GET)
	public String heartHistory(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="id") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="startTime",defaultValue="") String startTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="payment_reason",defaultValue="") Short[] payment_reason,
			@RequestParam(value="recharge_reason",defaultValue="") Short[] recharge_reason,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		
		if(key==null){
			key="";
		}
		PageCustom<CoinHistory> listPage = paymentHistoryService.listHeartManegerment(startTime, endTime, payment_reason, recharge_reason, type, key, page, PAGE_SIZE);
		List<CategoryEntity> list_payment = categoryService.findByType((short)10);
		List<CategoryEntity> list_recharge = categoryService.findByType((short)11);
		
		if(listPage.getOther().get(0) != null){
			model.addAttribute("totalAddTable", Long.valueOf(listPage.getOther().get(0).toString()) );
		}
		if(listPage.getOther().get(1) != null){
			model.addAttribute("totalDeducationTable", Long.valueOf(listPage.getOther().get(1).toString()));
		}
		model.addAttribute("list_payment", list_payment);
		model.addAttribute("list_recharge", list_recharge);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("payment_reason", payment_reason);
		model.addAttribute("recharge_reason", recharge_reason);
		model.addAttribute("totalAdd", paymentHistoryService.totalAddCoinBy_Type(ApplicationDefine.Payment_TypeCoin.Heart.getCode()));
		model.addAttribute("totalDeducation", paymentHistoryService.totalDeductionCoinBy_Type(ApplicationDefine.Payment_TypeCoin.Heart.getCode()));
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("now", new Date());
		model.addAttribute("activePage", nav);
		new Pager<CoinHistory>(listPage).setManualSetting(model, request);
		return JSP_HEART_LIST;
	}
	
	
	@RequestMapping(value ="/heartHistory/list", method = RequestMethod.POST)
	public String heart_history_post(
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "update", defaultValue = "") String update,
			@RequestParam(value = "type_hellocoin", defaultValue = "") Integer type_hellocoin,
			@RequestParam(value = "coin", defaultValue = "") Integer coin,
			@RequestParam(value = "payment_reason_hellocoin", defaultValue = "") String payment_reason_hellocoin,
			HttpSession session,
			Model model) {
		
		return "redirect:/"+ JSP_HEART_LIST;
	}
	
	
	@RequestMapping("/coinHistory/update")
	public String updateCoin(Model model, HttpSession session, 
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "type_hellocoin", defaultValue = "") Integer type_hellocoin,
			@RequestParam(value = "coin", defaultValue = "") Integer coin,
			@RequestParam(value = "payment_reason_hellocoin", defaultValue = "") String payment_reason_hellocoin,
			
			@RequestParam(value = "type_heart", defaultValue = "") Integer type_heart,
			@RequestParam(value = "heart", defaultValue = "") Integer heart,
			@RequestParam(value = "payment_reason_heart", defaultValue = "") String payment_reason_heart,
			
			@RequestParam(value = "type_heart_voucher", defaultValue = "") Integer type_heart_voucher,
			@RequestParam(value = "counseling_number", defaultValue = "") Integer counseling_number,
			@RequestParam(value = "payment_reason_heart_voucher", defaultValue = "") String payment_reason_heart_voucher,
			
			@RequestParam(value = "update", defaultValue = "") String update
	) {
		try {
			User user = userService.findById(userId);
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
				r.setUserId(userId);
				r.setTypeCoin((short) 1);
				paymentHistoryService.create(r);
				return "redirect:/"+ JSP_COIN_LIST;
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
				r.setReason(payment_reason_heart);
				r.setUpdateDate(new Date());
				r.setUserId(userId);
				r.setTypeCoin((short) 2);
				paymentHistoryService.create(r);
				return "redirect:/"+ JSP_HEART_LIST;
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
						v.setUserId(userId);
						v.setAdminId(admin.getId());
						v.setReason(payment_reason_heart_voucher);
						voucherUserService.create(v);
					}
				}
				user.setVoucherNumber(voucherUserService.totalVoucherNoUsedByUserId(user.getId()));
				userService.updateVoucherNumberByUserId(user.getId());
				userService.update(user);
				return "redirect:/"+ JSP_HEART_VOUCHER_LIST;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/" + JSP_COIN_LIST;
	}

}
