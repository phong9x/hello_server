/*
 * Created on 10 thg 2 2017 ( Time 11:49:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.PaymentHistory;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.PaymentHistoryEntity;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.PaymentHistoryService;
//--- Services 
import org.trams.hello.business.service.UserService;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.Pager;
import org.trams.hello.web.common.utils.DataUtils;

/**
 * Spring MVC controller for 'Recharge' management.
 */
@Controller
@RequestMapping("/admin/payment_voucher/recharge")
public class RechargeAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "recharge";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "recharge";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "admin/payment_voucher/recharge/create";
	private static final String JSP_LIST   = "admin/payment_voucher/recharge/list";
	private static final String JSP_EDIT   = "admin/payment_voucher/recharge/edit";
	private static final String JSP_DETAIL   = "admin/payment_voucher/recharge/detail";

	
	
	@Resource
    private PaymentHistoryService paymentHistoryService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private UserService userService; // Injected by Spring
	
	@Resource
    private CategoryService categoryService;

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public RechargeAdminController() {
		super(RechargeAdminController.class, MAIN_ENTITY_NAME );
		log("RechargeAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="id") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="startTime",defaultValue="") String startTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="os",defaultValue="") String[] os,
			@RequestParam(value="payment",defaultValue="") Short[] payment,
			@RequestParam(value="status",defaultValue="") Short[] status,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		if(key==null){
			key="";
		}
		List<CategoryEntity> list_category = categoryService.findByType((short)5);
		Page<PaymentHistoryEntity> listPage = null;
		Date start = null;
		Date end = null;
		if(startTime != null){
			start = DataUtils.parseDate(startTime, "yyyy/MM/dd");
		}
		if(endTime != null){
			end = DataUtils.parseDate(endTime, "yyyy/MM/dd");
		}
		
		listPage = paymentHistoryService.filterRecharge(start, end, os, payment, status, type, key, page, PAGE_SIZE);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("os", os);
		model.addAttribute("status", status);
		model.addAttribute("payment", payment);
		model.addAttribute("key", key);
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("list_category", list_category);
		model.addAttribute("type", type);
		model.addAttribute("activePage", nav);
		new Pager<PaymentHistoryEntity>(listPage).setSetting(model, request);		
		return JSP_LIST;
	}

	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id) {
		PaymentHistoryEntity recharge = paymentHistoryService.findOne(id);
		model.addAttribute("item", recharge);
		return JSP_DETAIL;
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
	public String post(
	@RequestParam(value="status",defaultValue="") Short status,
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id) {
		if(status != null){
			PaymentHistory recharge = paymentHistoryService.findById(id);
			recharge.setStatus(status);
			recharge.setRefundDate(new Date());
			paymentHistoryService.update(recharge);
		}
		
		return "redirect:/"+JSP_LIST;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	
	
	
}
