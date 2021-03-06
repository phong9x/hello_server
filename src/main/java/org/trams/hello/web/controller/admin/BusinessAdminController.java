/*
 * Created on 26 thg 12 2016 ( Time 14:11:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.trams.hello.bean.Business;
import org.trams.hello.bean.BusinessCustom;
import org.trams.hello.bean.BusinessSub;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PromotionPage;
import org.trams.hello.bean.PromotionPageLink;
import org.trams.hello.bean.User;
import org.trams.hello.bean.Voucher;
import org.trams.hello.bean.jpa.BusinessEntity;
import org.trams.hello.bean.jpa.BusinessSubEntity;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.PromotionPageEntity;
import org.trams.hello.bean.jpa.PromotionPageLinkEntity;
import org.trams.hello.bean.jpa.UserBusinessEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.VoucherEntity;
import org.trams.hello.bean.web.admin.BusinessSubDate;
import org.trams.hello.business.service.BusinessService;
import org.trams.hello.business.service.BusinessSubService;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.PromotionPageLinkService;
import org.trams.hello.business.service.PromotionPageService;
import org.trams.hello.business.service.UserBusinessService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VoucherService;
import org.trams.hello.business.service.mapping.BusinessServiceMapper;
import org.trams.hello.business.service.mapping.UserServiceMapper;
import org.trams.hello.business.service.mapping.VoucherServiceMapper;
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Pager;
import org.trams.hello.web.common.utils.EncryptionUtils;
import org.trams.hello.web.common.utils.ExcelUtils;
import org.trams.hello.web.common.utils.FileUtils;

//--- Common classes
//--- Entities
//--- Services

/**
 * Spring MVC controller for 'Business' management.
 */
@Controller
@RequestMapping("/admin/user/business")
public class BusinessAdminController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "business";
	private static final String MAIN_LIST_NAME = "list";

	private static String nav = "business";

	// --- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE = "admin/user/business/create";
	private static final String JSP_LIST = "admin/user/business/list";
	private static final String JSP_SUB_LIST = "admin/user/business/sub";
	private static final String JSP_SUB_CREATE = "admin/user/business/sub/create";
	private static final String JSP_SUB_MEMBER_LIST = "admin/user/business/sub/member";
	private static final String JSP_EDIT = "admin/user/business/edit";
	private static final String JSP_PROMOTION = "admin/user/business/promotion";
	private static final String JSP_DETAIL = "admin/user/business/detail";
	private static final String JSP_SUB_DETAIL = "admin/user/business/sub/detail";

	
	private static final String URL_SUB_LIST = "/admin/user/business/sub";
	private static final String URL_SUB_MEMBER_LIST = "/admin/user/business/sub/member/";
	
	// --- Main entity service
	@Resource
	private BusinessService businessService; // Injected by Spring
	
	@Resource
	private BusinessServiceMapper businessServiceMapper; // Injected by Spring
	@Resource
	private UserServiceMapper userServiceMapper; // Injected by Spring
	// --- Other service(s)
	@Resource
	private UserService userService; // Injected by Spring

	@Resource
	private CategoryService categoryService;

	@Resource
	private PromotionPageService promotionPageService;

	@Resource
	private PromotionPageLinkService promotionPageLinkService;

	@Resource
	private VoucherService voucherService;

	@Resource
	private VoucherServiceMapper voucherServiceMapper;

	@Resource
	ServletContext servletContext;

	@Resource
	private BusinessSubService businessSubService;
	
	@Resource
	private UserBusinessService userBusinessService;
	
	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public BusinessAdminController() {
		super(BusinessAdminController.class, MAIN_ENTITY_NAME);
		log("BusinessAdminController created.");
	}

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "business_type", defaultValue = "") Short[] business_type,
			@RequestParam(value = "type", defaultValue = "company_name") String type,
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "orderBy", defaultValue = "create_date") String orderBy,
			@RequestParam(value = "delete", defaultValue = "0") Integer delete, HttpServletRequest request,
			HttpSession session, Model model) {
		if (key == null) {
			key = "";
		}
		PageCustom<BusinessCustom> listPage =  businessService.listPagingBy_BusineesTypeAndBusinessNameAndUsername(business_type, type,
					key, orderBy, page, PAGE_SIZE);
		model.addAttribute(MAIN_LIST_NAME, listPage);
		model.addAttribute("key", key);
		model.addAttribute("type", type);
		model.addAttribute("business_type", business_type);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("uri", request.getQueryString());
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/admin/user/business/list");
		new Pager<BusinessCustom>(listPage).setManualSetting(model, request);
		return JSP_LIST;
	}

	@RequestMapping(value = "/download_excel", method = RequestMethod.GET)
	public void exportExcel(HttpSession session, @ModelAttribute User user, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		log("Action 'create'");
		try {
			Page<BusinessEntity> list = businessService.listPaging(1, Integer.MAX_VALUE);
			List<String> header = new ArrayList<>();
			header.add("번호");
			header.add("기업명");
			header.add("기업 구분");
			header.add("아이디");
			header.add("기업 상세 분류");
			header.add("소속 회원");
			header.add("상담권 사용");
			header.add("상담 금액");
			List<List<Object>> list_data = null;
			if (list.getContent() != null) {
				list_data = new ArrayList<>();
				for (BusinessEntity i : list.getContent()) {
					List<Object> list_str = new ArrayList<Object>();
					list_str.add(String.valueOf(i.getId()));
					list_str.add(String.valueOf(i.getBusinessName()));
					if (i.getBusinessType() == 1) {
						list_str.add("일반기업");
					} else if (i.getUser().getUserRole().getId() == 2) {
						list_str.add("보험사");
					}
					list_str.add(String.valueOf(i.getUser().getUsername()));
					list_str.add(
							String.valueOf(i.getSubBusinessActive()) + "/" + String.valueOf(i.getTotalSubBusiness()));
					list_str.add(String.valueOf(i.getTotalMember()));
					list_str.add(String.valueOf(i.getTotalCounseling()));
					list_str.add(String.valueOf(i.getTotalFeeCounseling()));
					list_data.add(list_str);
				}
			}
			ExcelUtils.createExcelFile(request, response, "기업 상세 분류", header, list_data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, HttpSession session, @PathVariable("id") Integer id) {
		Business business = businessService.findById(id);
		model.addAttribute("business", business);
		return JSP_DETAIL;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpSession session, Model model) {
		log("Action 'create'");
		List<CategoryEntity> list_payment = categoryService.findByType((short) 4);
		model.addAttribute("list_payment", list_payment);
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(HttpSession session, 
			@ModelAttribute Business business, 
			@ModelAttribute User user, 
			Model model) {
		log("Action 'create'");
		try {
			Integer check = userService.findOneByUsername(user.getUsername());
			
			user.setPassword(EncryptionUtils.jwtBuild(user.getPassword()));
			user.setFullname(business.getBusinessName());
			user.setGender((short) 1);
			user.setPhone(business.getBusinessPhone());
			user.setEmail(business.getBusinessEmail());
			user.setBannedNumber(0);
			user.setChargeNumber(0);
			user.setTestPsychologicalNumber(0);
			user.setStatusActive((short) 1);
			user.setHeart(0);
			user.setVoucherNumber(0);
			user.setCoin(0);
			user.setOnline((short) 1);
			user.setIsDelete(0);
			user.setUpdateDate(new Date());
			user.setCreateDate(new Date());
			user.setRoleId(ApplicationDefine.USER_ROLE_BUSSINESS);
			business.setRegisterDate(new Date());
			business.setParentBusinessId(0);
			business.setUpdateDate(new Date());
			business.setCreateDate(new Date());
			
			if(check > 0){
				BusinessEntity be = new BusinessEntity();
				UserEntity ue = new UserEntity();
				businessServiceMapper.mapBusinessToBusinessEntity(business, be);
				userServiceMapper.mapUserToUserEntity(user, ue);
				be.setUser(ue);
				model.addAttribute("item", be);
				model.addAttribute("usernameExists", 1);
				EncryptionUtils encry = new EncryptionUtils();
				String password = encry.jwtParse(user.getPassword().trim(), String.class);
				model.addAttribute("password", password);
				return JSP_CREATE;
			}
			
			
			User user_create  = userService.create(user);
			business.setId(user_create.getId());
			business.setContractStatus((short)1);
			businessService.create(business);
			return "redirect:/" + JSP_LIST;
		} catch (Exception e) {
			e.printStackTrace();
			return JSP_CREATE;
		}
	}

	@RequestMapping(value = "/promotion/{id}", method = RequestMethod.GET)
	public String promotion(HttpSession session, @PathVariable("id") Integer id, Model model) {
		try {
			List<PromotionPageEntity> list = promotionPageService.getPromotionPageByBussinessId(id);
			if(list.size() > 0){
				model.addAttribute("item", list.get(0));
				List<PromotionPageLinkEntity> list_link = promotionPageLinkService.findByPromotionPageId(list.get(0).getId());
				model.addAttribute("list_link", list_link);
			}
			model.addAttribute("activePage", nav);
			return JSP_PROMOTION;
		} catch (Exception e) {
			e.printStackTrace();
			return JSP_PROMOTION;
		}
	}

	@RequestMapping(value = "/promotion/{id}", method = RequestMethod.POST)
	public String promotion_post(HttpSession session, @PathVariable("id") Integer id,
			@RequestParam(value = "logoFile") MultipartFile logoFile,
			@RequestParam(value = "backgroundFile") MultipartFile backgroundFile,
			@RequestParam(value = "decription", defaultValue = "") String decription,
			@RequestParam(value = "link", defaultValue = "") String[] link,
			@RequestParam(value = "title", defaultValue = "") String[] title,
			@RequestParam(value = "isShow", defaultValue = "") Integer isShow,
			@RequestParam(value = "idPromotionPage", defaultValue = "0") Integer idPromotionPage,
			Model model) {
		try {
			
			if(idPromotionPage > 0){
				PromotionPage item = promotionPageService.findById(idPromotionPage);
				if (backgroundFile.getSize() > 0) {
					item.setBackgroundUrl(FileUtils.saveFileOrigin(backgroundFile, servletContext));
					item.setBackgroundName(backgroundFile.getOriginalFilename());
				}
				if (logoFile.getSize() > 0) {
					item.setLogoUrl(FileUtils.saveFileOrigin(logoFile, servletContext));
					item.setLogoName(logoFile.getOriginalFilename());
				}
				item.setIsShow(isShow);
				promotionPageService.update(item);
			}else{
				PromotionPage item = new PromotionPage();
				if (backgroundFile.getSize() > 0) {
					item.setBackgroundUrl(FileUtils.saveFileOrigin(backgroundFile, servletContext));
					item.setBackgroundName(backgroundFile.getOriginalFilename());
				}
				if (logoFile.getSize() > 0) {
					item.setLogoUrl(FileUtils.saveFileOrigin(logoFile, servletContext));
					item.setLogoName(logoFile.getOriginalFilename());
				}
				item.setIsShow(isShow);
				item.setStatus((short)1);
				item.setBusinessId(id);
				PromotionPage p =promotionPageService.create(item);
				idPromotionPage = p.getId();
			}
			
			if (title.length > 0) {
				promotionPageLinkService.deleteByPromotionPageId(idPromotionPage);
				for (int j = 0; j < title.length; j++) {
					PromotionPageLink pl = new PromotionPageLink();
					pl.setCreateDate(new Date());
					try {
						if(link[j] == null ){
							pl.setLink("");
						}else{
							pl.setLink(link[j]);
						}
					} catch (Exception e) {
						pl.setLink("");
					}
					
					if(title[j] == null || title[j].equals("")){
						pl.setTitle("");
					}else{
						pl.setTitle(title[j]);
					}
					pl.setPromotionPageId(idPromotionPage);
					pl.setUpdateDate(new Date());
					promotionPageLinkService.create(pl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/" + JSP_PROMOTION + "/" + id;
	}
	
	@RequestMapping(value = "/promotion/reset/{id}", method = RequestMethod.POST)
	public String reset(HttpSession session, 
			@PathVariable("id") Integer id,
			Model model) {
		try {
			promotionPageLinkService.deleteByPromotionPageBy_BusinessId(id);
			promotionPageService.deletePromotionPageBy_BussinessId(id);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/" + JSP_PROMOTION + "/" + id;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(HttpSession session, @PathVariable("id") Integer id, Model model) {
		BusinessEntity business = businessService.findOne(id);
		try {
			EncryptionUtils encry = new EncryptionUtils();
			model.addAttribute("item", business);
			String password = encry.jwtParse(business.getUser().getPassword().trim(), String.class);
			List<CategoryEntity> list_payment = categoryService.findByType((short) 4);
			List<Object[]> list_sub = businessService.getTotalSubActiveAndTotalSubBy_BusinessId(id);
			model.addAttribute("subBusinessActive", list_sub.get(0)[0]);
			model.addAttribute("totalSubBusiness", list_sub.get(0)[1]);
			model.addAttribute("list_payment", list_payment);
			model.addAttribute("password", password);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			return JSP_LIST;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@RequestParam(value = "edit", defaultValue = "0") Integer edit, @ModelAttribute Business item,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "username", defaultValue = "") String username, HttpSession session,
			@PathVariable("id") Integer id, Model model) {
		Business business = businessService.findById(id);
		User user = userService.findById(id);
		try {
			business.setBusinessNo(item.getBusinessNo());
			business.setBusinessName(item.getBusinessName());
			business.setBusinessNumber(item.getBusinessNumber());
			business.setBusinessType(item.getBusinessType());
			business.setPaymentMethodId(item.getPaymentMethodId());
			business.setPaymentMethodName(item.getPaymentMethodName());
			business.setUpdateDate(new Date());
			business = businessService.update(business);
			String password_encrypt = EncryptionUtils.jwtBuild(password);
			if (!user.getPassword().equals(password_encrypt) || !user.getUsername().equals(username)) {
				user.setPassword(password_encrypt);
				user.setUsername(username);
				userService.update(user);
			}
			model.addAttribute("item", business);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/" + JSP_EDIT + "/" + id;
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(Model model, HttpSession session, @PathVariable("id") Integer id) {

		businessService.delete(id);
		return "redirect:/" + JSP_LIST;
	}

	@RequestMapping("/sub")
  	public String sub_list(@RequestParam(value = "page", defaultValue = "1") Integer page,
  			@RequestParam(value = "businessGeneralId", defaultValue = "0") Integer businessGeneralId,
  			@RequestParam(value = "businessInsuranceId", defaultValue = "0") Integer businessInsuranceId,
  			@RequestParam(value = "businessType", defaultValue = "1") Short businessType,
  			HttpServletRequest request,
  			HttpSession session, Model model) {
  		List<BusinessEntity> list_insurance = businessService.listPagingBy_BusinessType((short) 2);
  		List<BusinessEntity> list_general = businessService.listPagingBy_BusinessType((short) 1);
  		
  		Integer businessId = 0;
  		if(businessGeneralId.equals(0) && businessInsuranceId.equals(0)){
  			model.addAttribute("businessId", businessId);
  		}else{
  			if(businessType == 1){
  				businessId = businessGeneralId;
  			}else if (businessType == 2){
  				businessId = businessInsuranceId;
  			}
  			model.addAttribute("businessId", businessId);
  			
  			List<Map<String, Object>> list = businessSubService.findForlist(businessId, page, PAGE_SIZE);
  			Long totalCount = businessSubService.countForList(businessId);
  			new Pager<Map<String, Object>>(list).setListSetting(model, URL_SUB_LIST, totalCount, page, PAGE_SIZE);
  		}
  		
  		model.addAttribute("list_general", list_general);
  		model.addAttribute("list_insurance", list_insurance);
  		model.addAttribute("businessGeneralId", businessGeneralId);
  		model.addAttribute("businessInsuranceId", businessInsuranceId);
  		model.addAttribute("businessType", businessType);
  		model.addAttribute("activePage", nav);
  		
  		return JSP_SUB_LIST;
  	}

	@RequestMapping(value = "/sub/create", method = RequestMethod.GET)
	public String sub_create(HttpSession session, Model model,
			@RequestParam(value = "businessId", defaultValue = "0") Integer businessId) {
		log("Action 'create'");
		Calendar c = Calendar.getInstance();
		model.addAttribute("yearNow", c.get(Calendar.YEAR));
		model.addAttribute("activePage", nav);
		model.addAttribute("businessId", businessId);
		return JSP_SUB_CREATE;
	}

	@RequestMapping(value = "/sub/create", method = RequestMethod.POST)
	public String sub_create(HttpSession session, 
			@ModelAttribute BusinessSub businessSub,
			@ModelAttribute BusinessSubDate businessSubDate,
			@RequestParam(value = "voucherName", defaultValue = "") String voucherName,
			@RequestParam(value = "fee30", defaultValue = "") Integer fee30,
			@RequestParam(value = "fee50", defaultValue = "") Integer fee50,
			@RequestParam(value = "voucherNumber30", defaultValue = "") Integer voucherNumber30,
			@RequestParam(value = "voucherNumber50", defaultValue = "") Integer voucherNumber50,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "typeTime", defaultValue = "") Short typeTime,
			@RequestParam(value = "dateNumber", defaultValue = "") Integer dateNumber,
			Model model) {
		log("Action 'create'");
		try {
			businessSub.setId(0);
			businessSub.setContractStartDate(businessSubDate.getContractDate().getFromDate());
			businessSub.setContractEndDate(businessSubDate.getContractDate().getToDate());
			businessSub.setCreateDate(new Date());
			BusinessSub createdBusinessSub = businessSubService.create(businessSub);

			Date now = new Date();
			
			//create voucher for counseling 30 minutes
			Voucher voucher30Minutes  = new Voucher();
			voucher30Minutes.setName(voucherName);
			voucher30Minutes.setFee(fee30);
			voucher30Minutes.setNumber(voucherNumber30);
			voucher30Minutes.setTypeTime(typeTime);
			if(typeTime == 1){
				voucher30Minutes.setFromTime(businessSubDate.getVoucherDate().getFromDate());
				voucher30Minutes.setToTime(businessSubDate.getVoucherDate().getToDate());
			}else{
				voucher30Minutes.setDateNumber(dateNumber);
			}
			voucher30Minutes.setContent(content);
			voucher30Minutes.setId(0);
			voucher30Minutes.setBusinessSubId(createdBusinessSub.getId());
			voucher30Minutes.setCreateDate(now);
			voucher30Minutes.setUpdateDate(now);
			voucher30Minutes.setTypeVoucher((short) 1);
			voucher30Minutes.setUsed(0);
			voucher30Minutes.setCounselingTimeType(ApplicationDefine.Voucher_CounselingTimeType.Minutes30.getCode());
			voucherService.create(voucher30Minutes);
			
			//create voucher for counseling 50 minutes
			Voucher voucher50Minutes  = new Voucher();
			voucher50Minutes.setName(voucherName);
			voucher50Minutes.setFee(fee30);
			voucher50Minutes.setNumber(voucherNumber30);
			voucher50Minutes.setTypeTime(typeTime);
			voucher50Minutes.setContent(content);
			if(typeTime == 1){
				voucher50Minutes.setFromTime(businessSubDate.getVoucherDate().getFromDate());
				voucher50Minutes.setToTime(businessSubDate.getVoucherDate().getToDate());
			}else{
				voucher50Minutes.setDateNumber(dateNumber);
			}
			voucher50Minutes.setId(0);
			voucher50Minutes.setBusinessSubId(createdBusinessSub.getId());
			voucher50Minutes.setCreateDate(now);
			voucher50Minutes.setUpdateDate(now);
			voucher50Minutes.setTypeVoucher((short) 1);
			voucher50Minutes.setUsed(0);
			voucher50Minutes.setCounselingTimeType(ApplicationDefine.Voucher_CounselingTimeType.Minutes50.getCode());
			voucherService.create(voucher50Minutes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/" + JSP_SUB_LIST;
	}
	
	@RequestMapping(value = "/sub/detail/{id}", method = RequestMethod.GET)
	public String sub_detail(Model model, HttpSession session, 
			@PathVariable("id") Integer id) {
		BusinessSub businessSub = businessSubService.findById(id);
		model.addAttribute("item", businessSub);
		List<VoucherEntity> list_voucher = voucherService.listByBusinessId(id);
		
		for (VoucherEntity voucherEntity : list_voucher) {
			if(voucherEntity.getCounselingTimeType() == ApplicationDefine.Voucher_CounselingTimeType.Minutes30.getCode()){
				model.addAttribute("voucher30", voucherEntity);
			}else{
				model.addAttribute("voucher50", voucherEntity);
			}
		}
		
		Calendar c = Calendar.getInstance();
		model.addAttribute("yearNow", c.get(Calendar.YEAR));
		return JSP_SUB_DETAIL;
	}

	@RequestMapping(value = "/sub/detail/{id}", method = RequestMethod.POST)
	public String sub_detail_post(Model model, HttpSession session,
			@ModelAttribute BusinessSubEntity businessSubEntity,
			@ModelAttribute BusinessSubDate businessSubDate,
			@PathVariable("id") Integer businessSubId,
			
			@RequestParam(value = "voucherName", defaultValue = "") String voucherName,
			@RequestParam(value = "fee30", defaultValue = "") Integer fee30,
			@RequestParam(value = "fee50", defaultValue = "") Integer fee50,
			@RequestParam(value = "voucherNumber30", defaultValue = "") Integer voucherNumber30,
			@RequestParam(value = "voucherNumber50", defaultValue = "") Integer voucherNumber50,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "typeTime", defaultValue = "") Short typeTime,
			@RequestParam(value = "dateNumber", defaultValue = "") Integer dateNumber) {
		
		try {
			businessSubService.updateBusinessSub(businessSubId, businessSubEntity, businessSubDate);
			
			Voucher voucher30 = voucherService.findOneByBusinessIdAndCounselingTypeTime(businessSubId, ApplicationDefine.Voucher_CounselingTimeType.Minutes30.getCode());
			Voucher voucher50 = voucherService.findOneByBusinessIdAndCounselingTypeTime(businessSubId, ApplicationDefine.Voucher_CounselingTimeType.Minutes50.getCode());	
			Date now = new Date();
			if(voucher30 == null){
				voucher30  = new Voucher();
				voucher30.setName(voucherName);
				voucher30.setFee(fee30);
				voucher30.setNumber(voucherNumber30);
				voucher30.setTypeTime(typeTime);
				if(typeTime == 1){
					voucher30.setFromTime(businessSubDate.getVoucherDate().getFromDate());
					voucher30.setToTime(businessSubDate.getVoucherDate().getToDate());
				}else{
					voucher30.setDateNumber(dateNumber);
				}
				voucher30.setContent(content);
				voucher30.setId(0);
				voucher30.setBusinessSubId(businessSubId);
				voucher30.setCreateDate(now);
				voucher30.setUpdateDate(now);
				voucher30.setTypeVoucher((short) 1);
				voucher30.setUsed(0);
				voucher30.setCounselingTimeType(ApplicationDefine.Voucher_CounselingTimeType.Minutes30.getCode());
				voucherService.create(voucher30);
			}else{
				voucher30.setName(voucherName);
				voucher30.setContent(content);
				voucher30.setFee(fee30);
				voucher30.setNumber(voucherNumber30);
				voucher30.setTypeTime(typeTime);
				if(typeTime == 1){
					voucher30.setFromTime(businessSubDate.getVoucherDate().getFromDate());
					voucher30.setToTime(businessSubDate.getVoucherDate().getToDate());
				}else{
					voucher30.setDateNumber(dateNumber);
				}
				voucherService.update(voucher30);
			}
			
			if(voucher50 == null){
				voucher50  = new Voucher();
				voucher50.setName(voucherName);
				voucher50.setContent(content);
				voucher50.setFee(fee50);
				voucher50.setNumber(voucherNumber50);
				voucher50.setTypeTime(typeTime);
				if(typeTime == 1){
					voucher50.setFromTime(businessSubDate.getVoucherDate().getFromDate());
					voucher50.setToTime(businessSubDate.getVoucherDate().getToDate());
				}else{
					voucher50.setDateNumber(dateNumber);
				}
				voucher50.setId(0);
				voucher50.setBusinessSubId(businessSubId);
				voucher50.setCreateDate(now);
				voucher50.setUpdateDate(now);
				voucher50.setTypeVoucher((short) 1);
				voucher50.setUsed(0);
				voucher50.setCounselingTimeType(ApplicationDefine.Voucher_CounselingTimeType.Minutes50.getCode());
				voucherService.create(voucher50);
			}else{
				voucher50.setName(voucherName);
				voucher50.setContent(content);
				voucher50.setFee(fee50);
				voucher50.setNumber(voucherNumber50);
				voucher50.setTypeTime(typeTime);
				if(typeTime == 1){
					voucher50.setFromTime(businessSubDate.getVoucherDate().getFromDate());
					voucher50.setToTime(businessSubDate.getVoucherDate().getToDate());
				}else{
					voucher50.setDateNumber(dateNumber);
				}
				voucherService.update(voucher50);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/" + JSP_SUB_DETAIL + "/" + businessSubId;
	}

	@RequestMapping("/sub/member/{id}")
	public String sub_member_list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@PathVariable("id") Integer businessSubId, HttpServletRequest request, HttpSession session, Model model) {
		Page<UserBusinessEntity> list = userBusinessService.findByBusinessSubIdAndIsDeleted(businessSubId, false, page, PAGE_SIZE);
		new Pager<UserBusinessEntity>(list).setSetting(model, request);
		model.addAttribute("activePage", nav);
		model.addAttribute("businessSubId", businessSubId);
		
		return JSP_SUB_MEMBER_LIST;
	}

}
