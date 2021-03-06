/*
 * Created on 17 thg 1 2017 ( Time 13:50:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.ArrayList;
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
import org.trams.hello.bean.CounselingCenter;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.UserCertificateEntity;
import org.trams.hello.bean.jpa.UserEducationEntity;
import org.trams.hello.bean.jpa.UserFileEntity;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.CounselingCenterService;
import org.trams.hello.business.service.CounselorBiographyService;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.CounselorService;
//--- Services 
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.UserEducationService;
import org.trams.hello.business.service.UserFileService;
import org.trams.hello.business.service.mapping.CounselorServiceMapper;
import org.trams.hello.common.mail.Mail;
import org.trams.hello.common.mail.Mail.EmailTemplate;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Pager;
import org.trams.hello.web.common.utils.ConstantUtils;
import org.trams.hello.web.common.utils.DataUtils;

/**
 * Spring MVC controller for 'RequestProfileChanging' management.
 */
@Controller
@RequestMapping("/admin/request/requestCounselor")
public class RequestCounselorAdminController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "requestCounselor";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE = 30;

	private static String nav = "requestCounselor";

	// --- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE = "admin/request/requestCounselor/create";
	private static final String JSP_LIST = "admin/request/requestCounselor/list";
	private static final String JSP_EDIT = "admin/request/requestCounselor/edit";
	private static final String JSP_DETAIL = "admin/request/requestCounselor/detail";

	// --- Main entity service

	// --- Other service(s)
	@Resource
	private CounselorService counselorService; // Injected by Spring

	@Resource
	private CounselorServiceMapper counselorServiceMapper; // Injected by Spring

	@Resource
	private CounselorFieldService counselorFieldService;

	@Resource
	private CounselingCenterService counselingCenterService; // Injected by
																// Spring

	@Resource
	private CategoryService categoryService;

	@Resource
	private UserCertificateService userCertificateService;

	@Resource
	private UserEducationService userEducationService;

	@Resource
	private UserFileService userFileService;

	@Resource
	private CounselorBiographyService counselorBiographyService;

	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */

	public RequestCounselorAdminController() {
		super(RequestCounselorAdminController.class, MAIN_ENTITY_NAME);
		log("RequestProfileChangingAdminController created.");
	}

	@RequestMapping("/list")
	public String list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "role", defaultValue = "") Integer[] role,
			@RequestParam(value = "centerId", defaultValue = "") Integer centerId,
			@RequestParam(value = "status", defaultValue = "") Short[] status,
			@RequestParam(value = "search", defaultValue = "0") Integer search,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		if (key == null) {
			key = "";
		}
		Page<CounselorEntity> listPage = null;
		if (search > 0) {
			listPage = counselorService.filter(role, centerId, status, key, type, page, PAGE_SIZE);
			model.addAttribute("status", status);
		} else {
			Short[] statusPro = new Short[1];
			statusPro[0] = 0;
			listPage = counselorService.filter(role, centerId, statusPro, key, type, page, PAGE_SIZE);
			model.addAttribute("status", statusPro);
		}
		Calendar c = Calendar.getInstance();
		List<CounselingCenter> list_center = counselingCenterService.findAll();
		model.addAttribute(MAIN_LIST_NAME, listPage);
		model.addAttribute("list_center", list_center);
		model.addAttribute("yearNow", c.get(Calendar.YEAR));
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("role", role);
		model.addAttribute("centerId", centerId);
		model.addAttribute("activePage", nav);
		model.addAttribute("pagination_navigator", "/admin/requestCounselor/list");
		String param = "";
		for (Integer r : role) {
			param += "&amp;role=" + r;
		}
		for (Short s : status) {
			param += "&amp;status=" + s;
		}
		new Pager<CounselorEntity>(listPage).setSetting(model,request);
		return JSP_LIST;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(HttpSession session, @PathVariable("id") Integer id, Model model) {
		CounselorEntity counselingCenter = counselorService.findByOne(id);
		try {
			List<Object> list_field = counselorFieldService.getCategoryNameBy_CouselorId(id);
			String field = "";
			for (int i = 0; i < list_field.size(); i++) {
				if (i == 0) {
					field = list_field.get(i).toString();
				} else {
					field = field + "," + list_field.get(i).toString();
				}
			}
			List<CategoryEntity> list_fee = new ArrayList<>();
			list_fee = categoryService.findByType((short) 2);
			List<CounselorBiographyEntity> list_biograpy = counselorBiographyService.listPagingByCounselorId(id);
			List<UserEducationEntity> list_education = userEducationService.listPagingByUserId(id);
			List<UserCertificateEntity> list_cert = userCertificateService.findByUserId(id);
			List<UserFileEntity> list_file = userFileService.listPagingByTypeAndUserId((short)1, id);
			model.addAttribute("list_education", list_education);
			model.addAttribute("list_fee", list_fee);
			model.addAttribute("list_cert", list_cert);
			model.addAttribute("list_biograpy", list_biograpy);
			model.addAttribute("list_file", list_file);
			model.addAttribute("field", field);
			model.addAttribute("item", counselingCenter);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			return JSP_LIST;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@RequestParam(value = "active", defaultValue = "1") Short active,
			@ModelAttribute Counselor item, HttpSession session, @PathVariable("id") Integer id, Model model) {
		try {
			CounselorEntity counselor = counselorService.findByOne(id);
			Counselor c = counselorServiceMapper.mapCounselorEntityToCounselor(counselor);
			if (item.getStatus() == 1) {
				c.setActived(active);
				c.setLevel(item.getLevel());
				c.setFeeCounselor(item.getFeeCounselor());
				c.setFeeCounselor50Minutes(item.getFeeCounselor50Minutes());
				c.setAcceptCounselorDate(new Date());
				c.setUpdateDate(new Date());
				c.setStatus(ApplicationDefine.Counselor_Status.NormalDisplay.getCode());
			} else {
				c.setStatus((short) ApplicationDefine.Counselor_Status.Reserve.getCode());
				c.setPauseReason(item.getPauseReason());
				c.setReserveDate(new Date());
				c.setUpdateDate(new Date());
			}

			Counselor edit = counselorService.update(c);
			if (edit.getStatus() == 1 || edit.getStatus() == 2) {
				HashMap<String, Object> params = new HashMap<>();
				params.put("counselorHome", ConstantUtils.getConfig("counselor.domain"));
				params.put("createDate", DataUtils.parseStringFromDate(new Date(), "yyyy MM월 dd일  HH:mm:ss"));
				Mail.sendEmailTemplate(counselor.getUser().getEmail(), params, EmailTemplate.ACCEPT_COUNSELOR, ApplicationDefine.EmailSubject.ACCEPT_COUNSELOR.getCode());
			} else if (edit.getStatus() == 3) {
				HashMap<String, Object> params = new HashMap<>();
				params.put("createDate", DataUtils.parseStringFromDate(new Date(), "yyyy MM월 dd일  HH:mm:ss"));
				params.put("reason", c.getPauseReason());
				Mail.sendEmailTemplate(counselor.getUser().getEmail(), params, EmailTemplate.REFUSE_COUNSELOR, ApplicationDefine.EmailSubject.REFUSE_COUNSELOR.getCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/" + JSP_LIST;
	}

}
