/*
 * Created on 12 Apr 2017 ( Time 10:26:03 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PsychologicalTestSetting;
import org.trams.hello.bean.RequestUserPsychological;
import org.trams.hello.bean.api.PsychologicalTest;
import org.trams.hello.bean.jpa.PsychologicalTestSettingEntity;
import org.trams.hello.bean.web.admin.PsychologicalTestForm;
import org.trams.hello.business.service.PsychologicalTestSettingService;
import org.trams.hello.business.service.RequestUserPsychologicalService;
//--- Services 
import org.trams.hello.business.service.UserPsychologicalService;
import org.trams.hello.web.bean.search.SearchPsychological;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Pager;

/**
 * Spring MVC controller for 'UserPsychological' management.
 */
@Controller
@RequestMapping("/admin/content/psychological")
public class PsychologicalAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "psychological";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "userPsychological";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_LIST   = "admin/content/psychological/list";
	private static final String JSP_SETTING  = "admin/content/psychological/setting";

	//--- Main entity service
	@Resource
    private UserPsychologicalService userPsychologicalService; // Injected by Spring
	@Resource
    private RequestUserPsychologicalService requestUserPsychologicalService;
	//--- Other service(s)
	@Resource
    private PsychologicalTestSettingService psychologicalTestSettingService; // Injected by Spring

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public PsychologicalAdminController() {
		super(PsychologicalAdminController.class, MAIN_ENTITY_NAME );
		log("UserPsychologicalAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="cancelPayment",defaultValue="0") Integer cancelPayment,
			@ModelAttribute SearchPsychological searchPsychological,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		if(cancelPayment > 0){
			RequestUserPsychological r = requestUserPsychologicalService.findById(cancelPayment);
			r.setStatus(ApplicationDefine.RequestPsychological_Status.CancelPayment.getCode());
			requestUserPsychologicalService.update(r);
		}
		PageCustom<PsychologicalTest> listPage = requestUserPsychologicalService.filterAdmin(searchPsychological, PAGE_SIZE);
		List<PsychologicalTestSetting> listTest = psychologicalTestSettingService.findAll();
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("s", searchPsychological);
		model.addAttribute("listTest", listTest);
		model.addAttribute("activePage", nav);
		new Pager<PsychologicalTest>(listPage).setManualSetting(model, request);	
		return JSP_LIST;
	}
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		Page<PsychologicalTestSettingEntity> list = psychologicalTestSettingService.listPaging(1, Integer.MAX_VALUE);
		if(!list.getContent().isEmpty()){
			model.addAttribute("list", list.getContent());
		}
		List<String> listCompany = new ArrayList<>();
		listCompany.add("가이던스");
		listCompany.add("어세스타");
		model.addAttribute("listCompany", listCompany);
		return JSP_SETTING;
	}

	@Transactional
	@RequestMapping(value = "/setting", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute PsychologicalTestForm psy,
			Model model) {
		try {
			for (PsychologicalTestSetting i : psy.getI()) {
				PsychologicalTestSetting p = psychologicalTestSettingService.findById(i.getId());
				p.setCompanyName(i.getCompanyName());
				p.setUrl(i.getUrl());
				p.setTestName(i.getTestName());
				p.setFee(i.getFee());
				p.setIsShow(i.getIsShow());
				p.setUpdateDate(new Date());
				psychologicalTestSettingService.update(p);
			}
			return "redirect:/"+JSP_SETTING;
		} catch(Exception e) {
			e.printStackTrace();
			return JSP_SETTING;
		}
	}

	

}
