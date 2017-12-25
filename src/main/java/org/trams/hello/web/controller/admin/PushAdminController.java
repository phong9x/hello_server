/*
 * Created on 16 May 2017 ( Time 09:45:34 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.BusinessSub;
//--- Entities
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.PushNotification;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.PushNotificationEntity;
import org.trams.hello.bean.jpa.VersionAppEntity;
import org.trams.hello.business.service.BusinessSubService;
//--- Services 
import org.trams.hello.business.service.PushNotificationService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.business.service.VersionAppService;
import org.trams.hello.web.bean.search.SearchPushMember;
//--- Common classes
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.Pager;
import org.trams.hello.web.common.utils.DataUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Spring MVC controller for 'Notification' management.
 */
@Controller
@RequestMapping("/admin/operation/push")
public class PushAdminController extends AbstractController {

	//--- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "notification";
	private static final String MAIN_LIST_NAME = "list";

	private static final Integer PAGE_SIZE   = 30;

	private static String nav = "notification";

	//--- JSP pages names ( View name in the MVC model )
	private static final String JSP_SEND_MEMBER   = "admin/operation/push/send/member";
	private static final String JSP_SEND_DEVICE   = "admin/operation/push/send/device";
	private static final String JSP_LIST   = "admin/operation/push/list";
	private static final String JSP_EDIT   = "admin/operation/push/edit";
	private static final String JSP_DETAIL   = "admin/operation/push/detail";

	//--- Main entity service
	@Resource
    private PushNotificationService pushNotificatinService; // Injected by Spring
	//--- Other service(s)
	@Resource
    private UserService userService; // Injected by Spring
	@Resource
    private BusinessSubService businessSubService;
	@Resource
    private VersionAppService versionAppService;
	

	//--------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public PushAdminController() {
		super(PushAdminController.class, MAIN_ENTITY_NAME );
	}

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type",defaultValue="id") String type,
			@RequestParam(value="key",defaultValue="") String key,
			@RequestParam(value="startTime",defaultValue="") String startTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		Page<PushNotificationEntity> listPage= pushNotificatinService.filterAdmin(startTime, endTime, type, key, page, PAGE_SIZE);
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("type", type);
		model.addAttribute("key", key);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("activePage", nav);
		new Pager<PushNotificationEntity>(listPage).setSetting(model, request);		
		return JSP_LIST;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public String post(
			@RequestParam(value="pushId",defaultValue="") Integer[] pushId,
			HttpSession session,
			HttpServletRequest request,
			Model model) {
		pushNotificatinService.updateStatusByIds(ApplicationDefine.Push_Status.Cancel.getCode(), pushId);
		return "redirect:/"+JSP_LIST;
	}
	
	@RequestMapping(value = "/send/member", method = RequestMethod.GET ) 
	public String create(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		List<BusinessSub> list_business = businessSubService.findAll();
		List<VersionAppEntity> list_app_ios = versionAppService.listBy_Os((short)2, 1, Integer.MAX_VALUE);
		List<VersionAppEntity> list_app_android = versionAppService.listBy_Os((short)1, 1, Integer.MAX_VALUE);
		Calendar c = Calendar.getInstance();
		model.addAttribute("now", c.get(Calendar.YEAR));
		model.addAttribute("list_app_ios", list_app_ios);
		model.addAttribute("list_app_android", list_app_android);
		model.addAttribute("list_business", list_business);
		model.addAttribute("activePage", nav);
		return JSP_SEND_MEMBER;
	}

	@RequestMapping(value = "/send/member", method = RequestMethod.POST ) 
	public String create(
			HttpSession session,
			@ModelAttribute PushNotification notification,
			@RequestParam (value="date",defaultValue="") String date,
			@RequestParam (value="hour",defaultValue="") Integer hour,
			@RequestParam (value="minute",defaultValue="") Integer minute,
			Model model) {
		log("Action 'create'");
		try {
			UserItem admin = (UserItem)Login.getAdminLogin(session);
			SearchPushMember s  = (SearchPushMember)session.getAttribute("search_member_push");
			PageCustom<User> listPage = userService.filterPushMember(s, 1, Integer.MAX_VALUE);
			Calendar c = Calendar.getInstance();
			c.setTime(DataUtils.parseDate(date, "yyyy/MM/dd"));
			c.set(Calendar.HOUR_OF_DAY, hour );
			c.set(Calendar.MINUTE, minute);
			if(notification.getTypePush() == ApplicationDefine.Notification_Type.Home.getCode()){
				notification.setLandingPage("홈");
			}else if(notification.getTypePush() == ApplicationDefine.Notification_Type.NoticeOrEvent.getCode()){
				notification.setLandingPage("공지사항");
			}else if(notification.getTypePush() == ApplicationDefine.Notification_Type.CounselorDetail.getCode()){
				notification.setLandingPage("상담사 소개 (상세)");
			}
			if(s.getSendAll()){
				notification.setTypeSend(ApplicationDefine.Push_Type.SendAll.getCode());
			}else{
				notification.setTypeSend(ApplicationDefine.Push_Type.SendAnyUser.getCode());
				JSONArray jArray = new JSONArray();
				for (User i : listPage.getList()) {
					  JSONObject user = new JSONObject();
					  user.put("id", i.getId());
					  user.put("username", i.getUsername());
				      jArray.add(user);
				}
				notification.setUserReceiveId(jArray.toJSONString());
			}
			notification.setOpenApp(0);
			notification.setOsType((short)3);
			notification.setStartPushDate(c.getTime());
			notification.setStatus(ApplicationDefine.Push_Status.Waiting.getCode());
			notification.setTotalPush(listPage.getList().size());
			notification.setTotalPushSuccess(0);
			notification.setAdminId(admin.getId());
			notification.setCreateDate(new Date());
			notification.setUpdateDate( new Date() );
			pushNotificatinService.create(notification);
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			e.printStackTrace();
			return JSP_SEND_MEMBER;
		}
	}
	
	@RequestMapping(value = "/send/device", method = RequestMethod.GET ) 
	public String create_device(
			HttpSession session,
			Model model) {
		log("Action 'create'");
		List<VersionAppEntity> list_app_ios = versionAppService.listBy_Os((short)2, 1, Integer.MAX_VALUE);
		List<VersionAppEntity> list_app_android = versionAppService.listBy_Os((short)1, 1, Integer.MAX_VALUE);
		Calendar c = Calendar.getInstance();
		model.addAttribute("now", c.get(Calendar.YEAR));
		model.addAttribute("list_app_ios", list_app_ios);
		model.addAttribute("list_app_android", list_app_android);
		model.addAttribute("activePage", nav);
		return JSP_SEND_DEVICE;
	}

	@RequestMapping(value = "/send/device", method = RequestMethod.POST ) 
	public String create_device_post(
			HttpSession session,
			@ModelAttribute PushNotification notification,
			@RequestParam (value="date",defaultValue="") String date,
			@RequestParam (value="hour",defaultValue="") Integer hour,
			@RequestParam (value="minute",defaultValue="") Integer minute,
			Model model) {
		log("Action 'create'");
		try {
			UserItem admin = (UserItem)Login.getAdminLogin(session);
			SearchPushMember s  = (SearchPushMember)session.getAttribute("search_device_push");
			PageCustom<User> listPage = userService.filterPushMember(s, 1, Integer.MAX_VALUE);
			Calendar c = Calendar.getInstance();
			c.setTime(DataUtils.parseDate(date, "yyyy/MM/dd"));
			c.set(Calendar.HOUR_OF_DAY, hour );
			c.set(Calendar.MINUTE, minute);
			if(notification.getTypePush() == ApplicationDefine.Notification_Type.Home.getCode()){
				notification.setLandingPage("홈");
			}else if(notification.getTypePush() == ApplicationDefine.Notification_Type.NoticeOrEvent.getCode()){
				notification.setLandingPage("공지사항");
			}else if(notification.getTypePush() == ApplicationDefine.Notification_Type.CounselorDetail.getCode()){
				notification.setLandingPage("상담사 소개 (상세)");
			}
			if(s.getSendAll()){
				notification.setTypeSend(ApplicationDefine.Push_Type.SendAll.getCode());
			}else{
				notification.setTypeSend(ApplicationDefine.Push_Type.SendAnyUser.getCode());
				JSONArray jArray = new JSONArray();
				for (User i : listPage.getList()) {
					  JSONObject user = new JSONObject();
					  user.put("id", i.getId());
					  user.put("username", i.getUsername());
				      jArray.add(user);
				}
				notification.setUserReceiveId(jArray.toJSONString());
			}
			notification.setOpenApp(0);
			notification.setOsType((short)3);
			notification.setStartPushDate(c.getTime());
			notification.setStatus(ApplicationDefine.Push_Status.Waiting.getCode());
			notification.setTotalPush(listPage.getList().size());
			notification.setTotalPushSuccess(0);
			notification.setAdminId(admin.getId());
			notification.setCreateDate(new Date());
			notification.setUpdateDate( new Date() );
			pushNotificatinService.create(notification);
			return "redirect:/"+JSP_LIST;
		} catch(Exception e) {
			e.printStackTrace();
			return JSP_SEND_DEVICE;
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String update(
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		PushNotification notification = pushNotificatinService.findById(id);
		try {
			model.addAttribute("item", notification);
			model.addAttribute("activePage", nav);
			return JSP_EDIT;
		} catch(Exception e) {
			return JSP_LIST;
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(
	@RequestParam(value="edit",defaultValue="0") Integer edit,
	@ModelAttribute PushNotification item,
	HttpSession session,
	@PathVariable("id") Integer id,
	Model model  ) {
		PushNotification notification = pushNotificatinService.findById(id);
		try {
			notification.setTitle( item.getTitle() );
			notification.setContent( item.getContent() );
			notification.setLandingPage( item.getLandingPage() );
			notification.setTotalPush( item.getTotalPush() );
			notification.setOpenApp( item.getOpenApp() );
			notification.setTotalPushSuccess( item.getTotalPushSuccess() );
			notification.setTypeSend( item.getTypeSend());
			notification.setTypePush( item.getTypePush() );
			notification.setStartPushDate( item.getStartPushDate() );
			notification.setUpdateDate( new Date() );
			notification = pushNotificatinService.update(notification);
			model.addAttribute("item", notification);
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
	
			
	pushNotificatinService.delete(id);
	return "redirect:/"+JSP_LIST;
	}

}
