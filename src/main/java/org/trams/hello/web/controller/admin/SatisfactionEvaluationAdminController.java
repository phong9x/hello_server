/*
 * Created on 7 Mar 2017 ( Time 17:04:24 )
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//--- Entities
import org.trams.hello.bean.UserQuestionaire;
import org.trams.hello.bean.jpa.TestResultEntity;
import org.trams.hello.bean.jpa.UserQuestionaireEntity;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.QuestionnaireService;
import org.trams.hello.business.service.TestResultService;
//--- Services 
import org.trams.hello.business.service.UserQuestionaireService;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'UserQuestionaire' management.
 */
@Controller
@RequestMapping("/admin/operation/satisfaction")
public class SatisfactionEvaluationAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "userQuestionaire";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "satisfaction";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_CREATE   = "admin/operation/satisfaction/create";
	private static final String JSP_LIST   = "admin/operation/satisfaction/list";
	private static final String JSP_EDIT   = "admin/operation/satisfaction/edit";
	private static final String JSP_DETAIL   = "admin/operation/satisfaction/detail";

	//--- Main entity service
	@Resource
    private UserQuestionaireService userQuestionaireService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private CounselorService counselorService; // Injected by Spring
	@Resource
    private QuestionnaireService questionnaireService; // Injected by Spring
	
	@Resource
    private TestResultService testResultService;
	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public SatisfactionEvaluationAdminController() {
		super(SatisfactionEvaluationAdminController.class, MAIN_ENTITY_NAME );
		log("UserQuestionaireAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="startTime",defaultValue="") String startTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="type",defaultValue="userName") String type,
			@RequestParam(value="orderBy",defaultValue="createDate") String orderBy,
			@RequestParam(value="delete",defaultValue="") Integer[] delete,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		if(key==null){
			key="";
		}
		
		if(delete.length > 0){
			for (int i = 0; i < delete.length; i++) {
				userQuestionaireService.delete(delete[i]);
				testResultService.deleteBy_UserQuestionId(delete[i]);
			}
		}
		
		Page<UserQuestionaireEntity> listPage = null;
		listPage = userQuestionaireService.filterAdmin((short)1, startTime, endTime, type, key, page, PAGE_SIZE, orderBy);
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("orderBy", orderBy);
		model.addAttribute("activePage", nav);
		new Pager<UserQuestionaireEntity>(listPage).setSetting(model, request);		
		return JSP_LIST;
	}

	
	@RequestMapping("/detail/{id}")
	public String detail(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id,
	@RequestParam(value="delete",defaultValue="0") Integer delete
			) {
		if(delete > 0){
			userQuestionaireService.delete(id);
			testResultService.deleteBy_UserQuestionId(id);
			return "redirect:/"+ JSP_LIST;
		}
		UserQuestionaireEntity userQuestionaire = userQuestionaireService.findOne(id);
		List<TestResultEntity> testResult = testResultService.listResultBy_UserQuestionId(id);
		model.addAttribute("item", userQuestionaire);
		model.addAttribute("list", testResult);
		return JSP_DETAIL;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		
		model.addAttribute("activePage", nav);
		return JSP_CREATE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute UserQuestionaire userQuestionaire,
			Model model) {
		log("Action 'create'");
		
			
		try {
			userQuestionaire.setUpdateDate( new Date() );
			userQuestionaire.setCreateDate( new Date() );
			userQuestionaireService.create(userQuestionaire);
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			e.printStackTrace();
			return JSP_CREATE;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		UserQuestionaire userQuestionaire = userQuestionaireService.findById(id);
		try {
			model.addAttribute("item", userQuestionaire);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
	@RequestParam(value="edit",defaultValue="0") Integer edit,
	@ModelAttribute UserQuestionaire item,
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		UserQuestionaire userQuestionaire = userQuestionaireService.findById(id);
		try {
			userQuestionaire.setUserId( item.getUserId() );
			userQuestionaire.setPoint( item.getPoint() );
			userQuestionaire.setComment( item.getComment() );
			userQuestionaire.setUpdateDate( new Date() );
			userQuestionaire = userQuestionaireService.update(userQuestionaire);
			model.addAttribute("item", userQuestionaire);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/"+JSP_EDIT+"/"+id;
	}
	
	

	@RequestMapping(value = "/delete/{id}")
	public String delete(
	Model model, 
	HttpSession session,
	@PathVariable("id") Integer id ) {
	
			
	userQuestionaireService.delete(id);
	return "redirect:/"+JSP_LIST;
	}

}
