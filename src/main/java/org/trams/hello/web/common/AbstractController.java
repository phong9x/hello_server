/*
 * Created on 23 thg 11 2016 ( Time 13:29:33 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.web.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.trams.hello.bean.jpa.MenuAdminLevel1Entity;
import org.trams.hello.business.service.MenuAdminLevel2Service;

public abstract class AbstractController {
	
	protected static final String MODE        = "mode";
	protected static final String MODE_CREATE = "create";
	protected static final String MODE_UPDATE = "update";
	
//	protected static final String CREATE_ACTION = "createAction" ;
//	protected static final String UPDATE_ACTION = "updateAction" ;
	protected static final String SAVE_ACTION = "saveAction" ;
	
	protected static final String DATE_FORMAT_PATTERN = "date_format_pattern";
	protected static final Integer PAGE_SIZE   = 10;

	protected final String entityName ;
	protected final Logger logger ;

//	@Resource
//	protected ControllerHelper controllerHelper;
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private MenuAdminLevel2Service menuAdminLevel2Service;
	
	@Resource
	protected HttpServletRequest httpRequest;
	
	private Map<Locale, CustomDateEditor> customDateEditorByLocales = new HashMap<Locale, CustomDateEditor>();
	
	public AbstractController(Class<? extends AbstractController> controllerClass, String entityName) {
		super();
		//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "DEBUG");		
		//System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");	
		this.entityName = entityName ;
		this.logger = LoggerFactory.getLogger(controllerClass) ;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		Locale locale = RequestContextUtils.getLocale(request);
		binder.registerCustomEditor(Date.class, getCustomDateEditor(locale));
	}
	
	
	@ModelAttribute(value = "list_menu")
	public List<MenuAdminLevel1Entity> getMenu() {
		return menuAdminLevel2Service.getAdminMenu();
	}
	
	@ModelAttribute(value = "init")
	public Map<String, Object> init() {
		Map<String, Object> params = new HashMap<>();
		params.put("originRequest", httpRequest.getRequestURI());
		params.put("queryString", httpRequest.getQueryString());
		return params;
	}
	
	private CustomDateEditor getCustomDateEditor(Locale locale) {
		CustomDateEditor customDateEditor = customDateEditorByLocales.get(locale);
		if(customDateEditor == null) {
			String dateFormatPattern = messageSource.getMessage(DATE_FORMAT_PATTERN, null, locale);
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			customDateEditor = new CustomDateEditor(dateFormat, true);
			customDateEditorByLocales.put(locale, customDateEditor);
		}
		return customDateEditor;
	}

	protected void log(String msg) {
		logger.info(msg);
	}

	protected void logContent(Model model) {
		Map<String,Object> map = model.asMap();
		logger.info("Model content (size = " + map.size() + ") : ");
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			logger.info(" . '" + entry.getKey() + "' : " + entry.getValue() );
		}
	}

	
	@SuppressWarnings("unchecked")
	protected void logSessionContent(HttpSession session) {
		Enumeration<String> enumNames = session.getAttributeNames();
		List<String> names = Collections.list(enumNames);
		logger.info("Session content (size = " + names.size() + ") : ");
		for ( String name : names ) {
			logger.info(" . '" + name + "' : " + session.getAttribute(name) );
		}
	}	
	private static final String URI_SEPARATOR = "/";
	
	protected String encodeUrlPathSegments(HttpServletRequest httpServletRequest, Object... pathSegments) {
		//--- get character encoding
		String characterEncoding = httpServletRequest.getCharacterEncoding();
		if (characterEncoding == null) {
			characterEncoding = WebUtils.DEFAULT_CHARACTER_ENCODING;
		}

		//--- encode N segments
		StringBuffer sb = new StringBuffer();
		int n = 0 ;
		for (Object segment : pathSegments) {
			n++ ;
			if (n > 1) { 
				sb.append(URI_SEPARATOR);
			}
			//--- encode 1 segment
			try {
				String encodedSegment = UriUtils.encodePathSegment(segment.toString(), characterEncoding);
				sb.append(encodedSegment);
			} catch (UnsupportedEncodingException uee) {
				throw new RuntimeException("encodePathSegment error", uee);
			}

			//sb.append(this.encodeUrlPathSegment(httpServletRequest, segment.toString()));
		}
		return sb.toString();
	}
	
	/**
	 * Returns "redirect:/entityName" 
	 * @return
	 */
	protected String redirectToList() {
		return "redirect:/" + this.entityName ;
	}

	/**
	 * Returns "redirect:/entityName/form/id1/id2/..." 
	 * @param httpServletRequest
	 * @param idParts
	 * @return
	 */
	protected String redirectToForm(HttpServletRequest httpServletRequest, Object... idParts) {
		return "redirect:" + getFormURL(httpServletRequest, idParts);
	}

	/**
	 * Returns "/entityName/form/id1/id2/..." 
	 * @param httpServletRequest
	 * @param idParts
	 * @return
	 */
	protected String getFormURL(HttpServletRequest httpServletRequest, Object... idParts) {
		return "/" + this.entityName + "/form/" + encodeUrlPathSegments(httpServletRequest, idParts );
	}


}