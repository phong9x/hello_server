/*
 * Created on 23 thg 12 2016 ( Time 14:27:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.rest.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.trams.hello.bean.Business;
import org.trams.hello.bean.jpa.BusinessEntity;
import org.trams.hello.business.service.BusinessService;
import org.trams.hello.rest.common.AbstractRestController;
import org.trams.hello.web.common.utils.DataUtils;
/**
 * Spring MVC controller for 'Business' management.
 */
@RequestMapping("/v1/business")
@Controller
public class BusinessRestController extends AbstractRestController{

	@Resource
	private BusinessService businessService;
	
		
	@RequestMapping( value="/all",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAll(
	) {
		
		try {
			List<Business> list = businessService.findAll();
			return SUCCESS(list);
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
	} 
	
	@RequestMapping( value="/paging",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAllPaging(
	@RequestBody String json,
	HttpSession session
	) throws ParseException {
		//UserItem userItem = Login.getMobileLogin(session);
		HashMap<String, Object> ret =new HashMap<String, Object>();
		try {
			JSONParser parser = new JSONParser();
			JSONObject jsonObj = (JSONObject) parser.parse(json);
			Integer page = DataUtils.parseInt(jsonObj.get("page"));
			Page<BusinessEntity> list = businessService.findAll(page);
			if(list.getContent()!=null){
				ret.put("list", list.getContent());
				ret.put("page", page);
				ret.put("totalPage", list.getTotalPages());
				return SUCCESS(ret);
			}else{
				return SUCCESS();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
		
	} 


	@RequestMapping( value="/item/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findOne(
	@PathVariable("id") Integer id,
	HttpSession session,
	@RequestHeader(value="token",required=false) String token
		) {
		//UserItem userItem = Login.getMobileLogin(session);
		try {
			Business item= businessService.findById(id);
			if(item!=null){
				return SUCCESS(item);
			}else{
				return SUCCESS();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
		
	}
	
	@RequestMapping( value="/create",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> create(
	HttpSession session,
	@RequestBody Business item
	) {
	try{
			item.setIsDelete(0);
			item.setCreateDate(new Date());
			item.setUpdateDate(new Date());
			Business create= businessService.create(item);
			return SUCCESS(create);
		}catch(Exception e){
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
	}

	@RequestMapping( value="/edit",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> edit(
	HttpSession session,
	@RequestBody Business item
	) {
	try{
			item.setUpdateDate(new Date());
			Business edit=businessService.update(item);
			return SUCCESS(edit);
		}catch(Exception e){
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
	}

	@RequestMapping( value="/delete/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> delete(
	@RequestHeader(value="token",required=false) String token,
	@PathVariable("id") Integer id) {
		try{
				businessService.delete(id);
				return SUCCESS();
		}catch(Exception e){
				e.printStackTrace();
				return FAIL("HAVE ERROR: "+e);
		}
	}
	
	@RequestMapping( value="/remove/{id}",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> update_delete(
	@RequestHeader(value="token",required=false) String token,
	@PathVariable("id") Integer id) {
		try{
			businessService.update_isDelete(id);
			return SUCCESS();
		}catch(Exception e){
			e.printStackTrace();
			return FAIL("HAVE ERROR: "+e);
		}
	}
}



