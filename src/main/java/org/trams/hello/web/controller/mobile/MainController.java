/*
 * Created on 22 thg 8 2016 ( Time 15:01:35 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.controller.mobile;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.trams.hello.business.service.BankForPaymentService;
import org.trams.hello.business.service.PaymentHistoryService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.web.common.AbstractController;
import org.trams.hello.web.common.utils.ConstantUtils;

@Controller
@RequestMapping("/mobile")
public class MainController extends AbstractController {

	// --- Variables names ( to be used in JSP with Expression Language )
	private static final String MAIN_ENTITY_NAME = "main";

	// --- JSP pages names ( View name in the MVC model )

	private static final String JSP_SHARED = "mobile/share";

	// Resource
	@Resource
	private PaymentHistoryService paymentHistoryService;

	@Resource
	private UserService userService;
	
	@Resource
	private BankForPaymentService bankForPaymentService;

	// --------------------------------------------------------------------------------------
	/**
	 * Default constructor
	 */
	public MainController() {
		super(MainController.class, MAIN_ENTITY_NAME);
		log("UserController created.");
	}

	@RequestMapping(value = "/share", method = RequestMethod.GET)
	public String share(
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) throws Exception {
		try {
			response.addHeader("Access-Control-Allow-Origin", "*");
			String  browserDetails  =   request.getHeader("User-Agent");
			System.out.println(browserDetails);
			String  userAgent       =   browserDetails;
			if (userAgent.toLowerCase().indexOf("android") >= 0 )
	         {
				 model.addAttribute("os", "android");
	         } else if(userAgent.toLowerCase().indexOf("iphone") >= 0 )
	         {
	        	 model.addAttribute("os", "iphone");
	        	 
	         }
			String url_image = ConstantUtils.getConfig("domain")+"/images/shared_logo.png";
			model.addAttribute("url_image", url_image);
			model.addAttribute("title", "Hello");
			model.addAttribute("site_name", "hello.co.kr");
			model.addAttribute("description", "hello");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSP_SHARED;
	}

	
}
