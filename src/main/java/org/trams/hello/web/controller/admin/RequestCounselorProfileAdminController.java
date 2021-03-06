/*
 * Created on 17 thg 1 2017 ( Time 13:50:37 )
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.CounselingCenter;
import org.trams.hello.bean.Counselor;
import org.trams.hello.bean.CounselorBiography;
import org.trams.hello.bean.CounselorField;
import org.trams.hello.bean.CounselorFieldTemp;
import org.trams.hello.bean.CounselorTemp;
//--- Entities
import org.trams.hello.bean.UserCertificate;
import org.trams.hello.bean.UserEducation;
import org.trams.hello.bean.jpa.CounselorBiographyTempEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.CounselorFieldTempEntity;
import org.trams.hello.bean.jpa.CounselorTempEntity;
import org.trams.hello.bean.jpa.UserCertificateTempEntity;
import org.trams.hello.bean.jpa.UserEducationTempEntity;
import org.trams.hello.bean.jpa.UserFileTempEntity;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.CounselingCenterService;
import org.trams.hello.business.service.CounselorBiographyService;
import org.trams.hello.business.service.CounselorBiographyTempService;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.CounselorFieldTempService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.CounselorTempService;
//--- Services 
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.UserCertificateTempService;
import org.trams.hello.business.service.UserEducationService;
import org.trams.hello.business.service.UserEducationTempService;
import org.trams.hello.business.service.UserFileService;
import org.trams.hello.business.service.UserFileTempService;
import org.trams.hello.business.service.mapping.CounselorFieldTempServiceMapper;
import org.trams.hello.business.service.mapping.CounselorServiceMapper;
import org.trams.hello.business.service.mapping.CounselorTempServiceMapper;
import org.trams.hello.common.mail.Mail;
import org.trams.hello.common.mail.Mail.EmailTemplate;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'RequestProfileChanging' management.
 */
@Controller
@RequestMapping("/admin/request/requestCounselorProfile")
public class RequestCounselorProfileAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "requestCounselor";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "requestCounselorProfile";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "admin/request/requestCounselorProfile/create";
	private static final String JSP_LIST   = "admin/request/requestCounselorProfile/list";
	private static final String JSP_EDIT   = "admin/request/requestCounselorProfile/edit";
	private static final String JSP_DETAIL   = "admin/request/requestCounselorProfile/detail";

	//--- Main entity service
	
	//--- Other service(s)
	@Resource
    private CounselorService counselorService; // Injected by Spring
	
	
	
	@Resource
    private CounselorFieldTempService counselorFieldTempService; // Injected by Spring
	
	@Resource
    private CounselorFieldTempServiceMapper counselorFieldTempServiceMapper; // Injected by Spring
	
	@Resource
    private CounselorServiceMapper counselorServiceMapper; // Injected by Spring
	
	@Resource
    private CounselorTempService counselorTempService; // Injected by Spring
	
	
    
	@Resource
    private CounselorTempServiceMapper counselorTempServiceMapper; // Injected by Spring
	
	@Resource
    private CounselorFieldService counselorFieldService;
	
	@Resource
    private CounselorBiographyService counselorBiographyService;
	
	@Resource
    private CounselingCenterService counselingCenterService; // Injected by Spring
	
	@Resource
	private CategoryService categoryService;
	
	@Resource
	private UserCertificateService userCertificateService;
	
	@Resource
	private UserEducationService userEducationService;
	
	@Resource
	private UserEducationTempService userEducationTempService;
	
	@Resource
	private UserCertificateTempService userCertificateTempService;
	
	@Resource
	private UserFileService userFileService;
	
	@Resource
	private UserFileTempService userFileTempService;
	
	@Resource
	private CounselorBiographyTempService counselorBiographyTempService;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */

	public RequestCounselorProfileAdminController() {
		super(RequestCounselorProfileAdminController.class, MAIN_ENTITY_NAME );
		log("RequestProfileChangingAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="status",defaultValue="") Short[] status,
			@RequestParam(value="search",defaultValue="0") Integer search,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		if(key==null){
			key="";
		}
		Page<CounselorTempEntity> listPage = null;
		if(search >0){
			listPage=counselorTempService.filter(status, type, key, page, PAGE_SIZE);
			model.addAttribute("status", status);
		}else{
			Short[] statusPro= new Short[1];
			statusPro[0] = 0;
			listPage=counselorTempService.filter(statusPro, type, key, page, PAGE_SIZE);
			model.addAttribute("status", statusPro);
		}
		Calendar c = Calendar.getInstance();
		List<CounselingCenter> list_center = counselingCenterService.findAll();
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("list_center", list_center);
		model.addAttribute("yearNow", c.get(Calendar.YEAR));
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("activePage", nav);
		
		new Pager<CounselorTempEntity>(listPage).setSetting(model,request);		
		return JSP_LIST;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		CounselorTempEntity counselorTemp = counselorTempService.findOne(id);
		try {
			List<Object> list_field = counselorFieldTempService.getCategoryNameBy_CouselorId(id);
			String field ="";
			for (int i = 0; i < list_field.size(); i++) {
				if(i == 0){
					field =list_field.get(i).toString();
				}else{
					field= field+"," + list_field.get(i).toString();
				}
			}
			Page<CounselorBiographyTempEntity> list_biograpy = counselorBiographyTempService.listPagingByUserId(id, 1, Integer.MAX_VALUE);
			Page<UserEducationTempEntity> list_education = userEducationTempService.listPagingByUserId(1, Integer.MAX_VALUE, id);
			Page<UserCertificateTempEntity> list_cert= userCertificateTempService.listPagingByUserId(1, Integer.MAX_VALUE, id);
			Page<UserFileTempEntity> list_file = userFileTempService.listPagingByUserId(1, Integer.MAX_VALUE, id);
			if(list_education.getContent() != null){
				model.addAttribute("list_education", list_education.getContent());
			}else{
				model.addAttribute("list_education", null);
			}
			if(list_cert.getContent() != null){
				model.addAttribute("list_cert", list_cert.getContent());
			}else{
				model.addAttribute("list_cert", null);
			}
			if(list_biograpy.getContent() != null){
				model.addAttribute("list_biograpy", list_biograpy.getContent());
			}else{
				model.addAttribute("list_biograpy", null);
			}
			if(list_file.getContent() != null){
				model.addAttribute("list_file", list_file.getContent());
			}else{
				model.addAttribute("list_file", null);
			}
			model.addAttribute("field", field);
			model.addAttribute("item", counselorTemp);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch(Exception e) {
			e.printStackTrace();
			return JSP_LIST;
		}
	}
	@Transactional
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
	HttpSession session,
	@RequestParam(value="status",defaultValue="1") Integer status,
	@RequestParam(value="reserveReason",defaultValue="") String reserveReason,
	@PathVariable("id") Integer id,
	Model model  ) {
		CounselorTemp ct = counselorTempService.findById(id);
		CounselorEntity counselorEntity = counselorService.findByOne(ct.getCounselorId());
		Counselor c = counselorServiceMapper.mapCounselorEntityToCounselor(counselorEntity);
		
		try {
			if(status == 1){
				counselorFieldService.deleteByCounselorId(ct.getCounselorId());
				List<CounselorFieldTempEntity> list_field = counselorFieldTempService.listPagingByCounselorTempId(id);
				for (CounselorFieldTempEntity i : list_field) {
					CounselorFieldTemp cft = counselorFieldTempServiceMapper.mapCounselorFieldTempEntityToCounselorFieldTemp(i);
					CounselorField cf =  new CounselorField();
					cf.setCounselorFieldId(cft.getCounselorFieldId());
					cf.setCounselorId(ct.getCounselorId());
					cf.setCreateDate(new Date());
					cf.setUpdateDate(new Date());
					counselorFieldService.create(cf);
				}
				counselorFieldTempService.deleteBycounselorTempId(id);
				if(c != null){
//					c.setCounselorNumber( ct.getCounselorNumber() );
//					c.setAccountBankNumber( ct.getAccountBankNumber() );
//					c.setAccountBankHolder( ct.getAccountBankHolder() );
//					c.setAimmedRecommend( ct.getAimmedRecommend() );
//					c.setFeeCounselor( ct.getFeeCounselor() );
//					c.setCounselOnline( ct.getCounselOnline() );
//					c.setPoints( ct.getPoints() );
//					c.setLikes( ct.getLikes() );
//					c.setGuidanceUsername( ct.getGuidanceUsername() );
//					c.setGuidancePassword( ct.getGuidancePassword() );
//					c.setPauseReason( ct.getPauseReason() );
//					c.setProfitPercent( ct.getProfitPercent() );
//					c.setIsDelete( ct.getIsDelete() );
//					c.setAcceptCounselorDate( ct.getAcceptCounselorDate() );
//					c.setStatusPaymentCounselingUseVoucher( ct.getStatusPaymentCounselingUseVoucher() );
//					c.setReserveDate( ct.getReserveDate() );
//					c.setCounselingRestTime( ct.getCounselingRestTime() );
					
					c.setLevel( ct.getLevel() );
					c.setLevelVip( ct.getLevelVip() );
					c.setCounselNumber( ct.getCounselNumber() );
					c.setMbtiCertificate( ct.getMbtiCertificate() );
					c.setIntroduce( ct.getIntroduce() );
					c.setThumbnailUrl( ct.getThumbnailUrl() );
					c.setThumbnailName( ct.getThumbnailName() );
					c.setIsFromAssesta( ct.getIsFromAssesta() );
					c.setUpdateDate( new Date() );
					c = counselorService.update(c);
					counselorTempService.delete(id);
				}
				//List<CounselorFieldTempEntity> list_field= counselorFieldTempService.listPagingByCounselorTempId(id);
				List<CounselorBiographyTempEntity> list_biograpy = counselorBiographyTempService.listByUserId(id);
				List<UserEducationTempEntity> list_education = userEducationTempService.listByUserId(id);
				List<UserCertificateTempEntity> list_cert= userCertificateTempService.listByUserId(id);
				
				if(!list_biograpy.isEmpty()){
					counselorBiographyService.deleteCounselorBiography(c.getId());
					for (CounselorBiographyTempEntity i : list_biograpy) {
						CounselorBiography cb = new CounselorBiography();
						cb.setContent(i.getContent());
						cb.setCounselorId(c.getId());
						cb.setFileName(i.getFileName());
						cb.setFileUrl(i.getFileUrl());
						cb.setCreateDate(i.getCreateDate());
						cb.setUpdateDate(new Date());
						counselorBiographyService.create(cb);
					}
					counselorBiographyTempService.deleteByUserId(ct.getId());
				}
				if(!list_cert.isEmpty()){
					userCertificateService.deleteByUserId(c.getId());
					for (UserCertificateTempEntity i : list_cert) {
						UserCertificate uc = new UserCertificate();
						uc.setCreateDate(new Date());
						uc.setFileUrl(i.getFileUrl());
						uc.setFileName(i.getFileName());
						uc.setName(i.getName());
						uc.setUpdateDate(new Date());
						uc.setCreateDate(i.getCreateDate());
						uc.setUserId(c.getId());
						uc.setType(i.getType());
						uc.setAuthor(i.getAuthor());
						uc.setIssuedDate(i.getIssuedDate());
						userCertificateService.create(uc);
					}
					userCertificateTempService.deleteByCounselorTempId(ct.getId());
				}
				
				if(!list_education.isEmpty()){
					userEducationService.deleteByUserId(id);
					for (UserEducationTempEntity i : list_education) {
						UserEducation ue = new UserEducation();
						ue.setCreateDate(i.getCreateDate());
						ue.setFaculty(i.getFaculty());
						ue.setUniversity(i.getUniversity());
						ue.setFileName(i.getFileName());
						ue.setFileUrl(i.getFileUrl());
						ue.setUniversity(i.getUniversity());
						ue.setUpdateDate(new Date());
						ue.setUserId(id);
						ue.setType(i.getType());
						userEducationService.create(ue);
					}
					userEducationTempService.deleteByCounselorTempId(ct.getId());
				}
			}else{
				ct.setStatus((short) 2);
				ct.setReserveReason(reserveReason);
				ct.setReserveDate(new Date());
				counselorTempService.update(ct);
				HashMap<String, Object> params = new HashMap<>();
				params.put("reason", reserveReason);
				Mail.sendEmailTemplate(counselorEntity.getUser().getEmail(), params, EmailTemplate.REFUSE_COUNSELOR_CHANGE_PROFILE, ApplicationDefine.EmailSubject.REFUSE_COUNSELOR_CHANGE_PROFILE.getCode());
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "redirect:/"+JSP_LIST;
	}
	
	

	
}
