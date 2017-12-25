package org.trams.hello.web.controller.admin;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trams.hello.bean.PageCustom;
import org.trams.hello.bean.web.admin.MessageCounselor;
import org.trams.hello.business.service.MessageService;
import org.trams.hello.business.service.UserReceiveMessageService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.Pager;

@Controller
@RequestMapping("/admin/cs/message")
public class MessageAdminController extends AbstractController {
	private static final String MAIN_ENTITY_NAME = "message";
	private static final String MAIN_LIST_NAME = "list";
	private static final Integer PAGE_SIZE   = 30;
	private static String nav = "message";

	private static final String JSP_SENDING   = "admin/cs/message/sending";
	private static final String JSP_LIST   = "admin/cs/message/list";
	private static final String JSP_DETAIL   = "admin/cs/message/detail";

	@Resource
    private MessageService messageService;
	@Resource
    private UserReceiveMessageService userReceiveMessageService;
	@Resource
    private UserService userService;
	
	public MessageAdminController() {
		super(MessageAdminController.class, MAIN_ENTITY_NAME );
		log("MessageAdminController created.");
	}

	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="type_search",defaultValue="adminNameSearch") String type_search,
			@RequestParam(value="start_search",defaultValue="") String start_search,
			@RequestParam(value="end_search",defaultValue="") String end_search,
			@RequestParam(value="keyword",defaultValue="") String key,
			@RequestParam(value="order_list",defaultValue="createDateOrder") String orderBy,
			HttpSession session,
			HttpServletRequest request,
			Model model) throws ParseException {
		if(key == null){
			key="";
		}
		
		PageCustom<MessageCounselor> listPage = messageService.getListFromAdmin(start_search, end_search, key, type_search, orderBy, page, PAGE_SIZE);
		model.addAttribute(MAIN_LIST_NAME, listPage);	
		model.addAttribute("type_search", type_search);
		model.addAttribute("list", listPage.getList());
		model.addAttribute("keyword", key);
		model.addAttribute("page", page);
		model.addAttribute("start_search", start_search);
		model.addAttribute("end_search", end_search);
		model.addAttribute("activePage", nav);
		model.addAttribute("order_list", orderBy);
		model.addAttribute("totalCount", listPage.getTotalCount());
		model.addAttribute("pagination_navigator", "/admin/cs/message/list");
		new Pager<MessageCounselor>(listPage).setManualSetting(model, request);
		return JSP_LIST;
	}
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detailMessage(HttpSession session, @PathVariable("id") Integer id, Model model) {
		try {
			MessageCounselor m = messageService.getByMessIdAdmin(id);
			model.addAttribute("message", m);
			return JSP_DETAIL;
		} catch (Exception e) {
			return JSP_LIST;
		}
	}
	
	@RequestMapping(value = "/sending", method = RequestMethod.GET)
	public String sendingMessage(HttpSession session, HttpServletRequest request, Model model) {
		try {
			return JSP_SENDING;
		} catch (Exception e) {
			return JSP_LIST;
		}
	}

}
