/*
 * Created on 25 thg 1 2017 ( Time 13:43:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.Business;
import org.trams.hello.bean.BusinessSub;
//--- Entities
import org.trams.hello.bean.CounselingSession;
import org.trams.hello.bean.PaymentHistory;
import org.trams.hello.bean.User;
import org.trams.hello.bean.VoucherUser;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.web.admin.ReservationRow;
import org.trams.hello.business.service.BusinessService;
import org.trams.hello.business.service.BusinessSubService;
//--- Services 
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PaymentHistoryService;
import org.trams.hello.business.service.ReservationService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.VoucherUserService;
import org.trams.hello.web.bean.search.SearchReservation;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'CounselingSession' management.
 */
@Controller
@RequestMapping("/admin/operation/reservation")
public class ReservationAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "counselingSession";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "reservation";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "admin/operation/reservation/create";
	private static final String JSP_LIST   = "admin/operation/reservation/list";
	private static final String JSP_EDIT   = "admin/operation/reservation/edit";
	private static final String JSP_DETAIL   = "admin/operation/reservation/detail";

	private static final String URL_LIST   = "/admin/operation/reservation/list";
	
	//--- Main entity service
	@Resource
    private CounselingSessionService counselingSessionService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private PaymentHistoryService paymentHistoryService; // Injected by Spring
	@Resource
    private UserService userService; // Injected by Spring
	@Resource
    private CounselorService counselorService; // Injected by Spring
	@Resource
    private BusinessService businessService;
	
	@Resource
	private VoucherUserService voucherUserService;
	
	@Resource
    private BusinessSubService businessSubService;
	
	@Resource
    private ReservationService reservationService;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public ReservationAdminController() {
		super(ReservationAdminController.class, MAIN_ENTITY_NAME );
		log("ReservationAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@ModelAttribute SearchReservation searchReservation,
			HttpServletRequest request,
			HttpSession session,
			Model model) {
		try {
			List<Business> bussinessList = businessService.findAll();
			model.addAttribute("bussinessList", bussinessList);
			
			List<BusinessSub> manageYearList = new ArrayList<BusinessSub>();
			List<BusinessSub> businessSubList = new ArrayList<BusinessSub>();
			Integer businessId = searchReservation.getBusinessId();
			if(businessId > 0){
				manageYearList = businessSubService.findByBusinessId(businessId);
				model.addAttribute("manageYearList", manageYearList);
				if(searchReservation.getManageYear() > 0){
					businessSubList = businessSubService.findByBusinessIdAndManageYear(businessId, searchReservation.getManageYear());
					model.addAttribute("businessSubList", businessSubList);
				}
			}
			
			List<ReservationRow> list = reservationService.searchReservationList(searchReservation, PAGE_SIZE);
			Long listCount = reservationService.reservationListCount(searchReservation);
			new Pager<ReservationRow>(list).setListSetting(model, URL_LIST, listCount, searchReservation.getCommon().getPage(), PAGE_SIZE);
			model.addAttribute("activePage", nav);
			model.addAttribute("searchReservation", searchReservation);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSP_LIST;
	}

	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id) {
		CounselingSessionEntity counselingSession = counselingSessionService.findOne(id);
		if(counselingSession.getUser().getUserRole().getId() == 7){
			Business child_bussiness = businessService.findById(counselingSession.getUser().getId());
			if(child_bussiness.getParentBusinessId() > 0){
				Business parent_bussiness = businessService.findById(child_bussiness.getParentBusinessId());
				model.addAttribute("b", parent_bussiness);
			}
			model.addAttribute("sb", child_bussiness);
		}
		model.addAttribute("item", counselingSession);
		return JSP_DETAIL;
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
	public String detail_post(
	Model model, 
	HttpSession session,
	@RequestParam(value="cancelCounseling", defaultValue="0") Integer cancelCounseling,
	@PathVariable("id") Integer id) {
		if(cancelCounseling > 0){
			CounselingSession cs = counselingSessionService.findById(id);
			cs.setStatus(ApplicationDefine.CounselingSession_Status.RESERVATION_CANCEL.getCode());
			cs.setCancelMettingTime(new Date());
			counselingSessionService.update(cs);
			if(cs.getPaymentId() != null){
				PaymentHistory p = paymentHistoryService.findById(cs.getPaymentId());
				if (p.getTypeCoin() == 3 && p.getVoucherUserId() != null) {
					VoucherUser v = voucherUserService.findById(p.getVoucherUserId());
					v.setStatusUse((short) ApplicationDefine.VoucherUser_Status.CanUse.getCode());
					v.setUseVoucherDate(null);
					voucherUserService.update(v);
					userService.updateVoucherNumberByUserId(cs.getUserId());
					p.setVoucherUserId(null);
					p.setStatus((short)ApplicationDefine.Payment_Status.Cancel.getCode());
					paymentHistoryService.update(p);
				}else{
					User u = userService.findById(cs.getUserId());
					Integer[] userCoin = userService.updateCoin(u, p.getCoin());
					p.setUserId(cs.getUserId());
					p.setStatus((short) 1);
					p.setTypeCoin(ApplicationDefine.Payment_TypeCoin.Coin.getCode());
					p.setTypeUse(ApplicationDefine.Recharge_TypeUse.CancelReservation.getCode());
					p.setReason(ApplicationDefine.Recharge_Reson.CancelReservation.getCode());
					p.setTypePayment(ApplicationDefine.Payment_TypePaymentStatus.Recharge.getCode());
					p.setCoin(userCoin[0]);
					p.setCurrentCoin(userCoin[1]);
					p.setCreateDate(new Date());
					p.setUpdateDate(new Date());
					p.setId(null);
					paymentHistoryService.create(p);
				}
			}
		}
		return "redirect:/"+ JSP_DETAIL+"/"+id;
	}
	


}
