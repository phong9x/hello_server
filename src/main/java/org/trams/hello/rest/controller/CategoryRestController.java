/*
 * Created on 23 thg 12 2016 ( Time 14:27:41 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.rest.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import org.trams.hello.bean.Category;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.mapping.CategoryServiceMapper;
import org.trams.hello.rest.common.AbstractRestController;
import org.trams.hello.web.common.utils.DataUtils;

/**
 * Spring MVC controller for 'Category' management.
 */
@RequestMapping("/v1/public/category")
@Controller
public class CategoryRestController extends AbstractRestController{

	@Resource
	private CategoryService categoryService;
	@Resource
	private CategoryServiceMapper categoryServiceMapper;
	
		
	@RequestMapping( value="/all",
			method = {RequestMethod.POST, RequestMethod.GET},
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public HashMap<String, Object> findAll(
	) {
		
		try {
			HashMap<String, Object> ret = new HashMap<>();
			HashMap<String, Object> cha = new HashMap<>();
			HashMap<String, Object> con = new HashMap<>();
			List<CategoryEntity> list = categoryService.findByType((short)1);
			
			Map<Integer, Map<String, Object>> chas = new HashMap<>();
			Map<Integer, List<Map<String, Object>>> cons = new HashMap<>();
			
			for (CategoryEntity c : list) {
				if(c.getParentId() == 0){
					
					Map<String, Object> map = new HashMap<>();
					map.put("id", c.getId());
					map.put("parentId", c.getParentId());
					map.put("categoryName", c.getCategoryName());
					map.put("decription", c.getDecription());
					map.put("comment", c.getComment());
					map.put("type", c.getType());
					map.put("isShow", c.getIsShow());
					map.put("relateCategoryId", c.getRelateCategoryId());
					map.put("createDate", c.getCreateDate());
					map.put("updateDate", c.getUpdateDate());
					map.put("imageCheckUrl", c.getImageCheckUrl());
					map.put("imageUncheckUrl", c.getImageUncheckUrl());
					chas.put(c.getId(), map);
					
				}else{
					if(cons.containsKey(c.getParentId())){
						Map<String, Object> map = new HashMap<>();
						map.put("id", c.getId());
						map.put("parentId", c.getParentId());
						map.put("categoryName", c.getCategoryName());
						map.put("decription", c.getDecription());
						map.put("comment", c.getComment());
						map.put("type", c.getType());
						map.put("isShow", c.getIsShow());
						map.put("relateCategoryId", c.getRelateCategoryId());
						map.put("createDate", c.getCreateDate());
						map.put("updateDate", c.getUpdateDate());
						map.put("imageCheckUrl", c.getImageCheckUrl());
						map.put("imageUncheckUrl", c.getImageUncheckUrl());
						cons.get(c.getParentId()).add(map);
					} else{
						List<Map<String, Object>> list_con = new ArrayList<>();
						Map<String, Object> map = new HashMap<>();
						map.put("id", c.getId());
						map.put("parentId", c.getParentId());
						map.put("categoryName", c.getCategoryName());
						map.put("decription", c.getDecription());
						map.put("comment", c.getComment());
						map.put("type", c.getType());
						map.put("isShow", c.getIsShow());
						map.put("relateCategoryId", c.getRelateCategoryId());
						map.put("createDate", c.getCreateDate());
						map.put("updateDate", c.getUpdateDate());
						map.put("imageCheckUrl", c.getImageCheckUrl());
						map.put("imageUncheckUrl", c.getImageUncheckUrl());
						list_con.add(map);
						cons.put(c.getParentId(), list_con);
					}
				}
				
			}
			
			for (Integer i : chas.keySet()) {
				List<Map<String, Object>> listConCuaCha = cons.get(i);
				chas.get(i).put("listConCuaCha", listConCuaCha);
			}
			Map<Integer, Map<String, Object>> treeMap = new TreeMap<Integer, Map<String, Object>>(chas);
			
			return SUCCESS(treeMap.values());
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
			Page<CategoryEntity> list = categoryService.findAll(page);
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
			Category item= categoryService.findById(id);
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
	@RequestBody Category item
	) {
	try{
			item.setCreateDate(new Date());
			item.setUpdateDate(new Date());
			Category create= categoryService.create(item);
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
	@RequestBody Category item
	) {
	try{
			item.setUpdateDate(new Date());
			Category edit=categoryService.update(item);
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
				categoryService.delete(id);
				return SUCCESS();
		}catch(Exception e){
				e.printStackTrace();
				return FAIL("HAVE ERROR: "+e);
		}
	}
	
}



