/*
 * Created on 23 thg 12 2016 ( Time 14:27:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.rest.controller;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.Policy;
import org.trams.hello.business.service.PolicyService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.rest.common.AbstractRestController;
/**
 * Spring MVC controller for 'Question' management.
 */
@RequestMapping("/v1/policy")
@Controller
public class PolicyController extends AbstractRestController{

	@Resource
	private PolicyService policyService;
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/termsOfServices", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> gettermsOfServices(HttpServletRequest httpRequest) {
		try {
			HashMap<String, Object> ret = new HashMap<String, Object>();
			Policy p = policyService.getPoli((short) 1, (short) 1);
			ret.put("termsOfServices", p);
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}

	@RequestMapping(value = "/privacyPolicys", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getPrivacyPolicys(HttpServletRequest httpRequest) {
		try {
			HashMap<String, Object> ret = new HashMap<String, Object>();
			Policy p = policyService.getPoli((short) 2, (short) 1);
			ret.put("privacyPolicys", p);
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}

	@RequestMapping(value = "/paymentPolicys", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getPaymentPolicys(HttpServletRequest httpRequest) {
		try {
			HashMap<String, Object> ret = new HashMap<String, Object>();
			Policy p = policyService.getPoli((short) 3, (short) 1);
			ret.put("paymentPolicys", p);
			return SUCCESS(ret);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: " + e);
		}
	}
	
}


