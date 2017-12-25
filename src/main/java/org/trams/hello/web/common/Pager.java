package org.trams.hello.web.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.trams.hello.bean.PageCustom;


public class Pager<T> {

	private Page<T> m_page;
	private PageCustom<T> c_list;
	
	private List<T> m_list;
	
	public Pager(Page<T> list)
	{
		m_page = list;
	}
	
	public Pager(PageCustom<T> list)
	{
		c_list = list;
	}
	
	public Pager(List<T> list)
	{
		m_list = list;
	}
	
	public Pager(){}
	
	public void setSetting(Model model, HttpServletRequest request)
	{
		int current = m_page.getNumber() + 1;
		int begin = Math.max(1, current - 4);
		int end = Math.min(begin + 9, m_page.getTotalPages());		
		model.addAttribute("list", m_page.getContent());
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalCount", m_page.getTotalElements());
	    model.addAttribute("size", m_page.getSize());
	    model.addAttribute("totalPages", m_page.getTotalPages());
	    String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("page.*?(?=&|\\?|$)[&]|page.*?(?=&|\\?|$)", "");
		}
		model.addAttribute("param_url", param_url);
		model.addAttribute("pagination_navigator", request.getRequestURI());
		
	    if (m_page.getTotalElements()==0)
	    	model.addAttribute("page", null);
	    else
	    	model.addAttribute("page", m_page);
	}
	
	public void setSettingSecondList(Model model, String url)
	{
		int current = m_page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		
		int end = Math.min(begin + 10, m_page.getTotalPages());
		
		model.addAttribute("list2", m_page.getContent());
	    model.addAttribute("beginIndex2", begin);
	    model.addAttribute("endIndex2", end);
	    model.addAttribute("currentIndex2", current);
	    model.addAttribute("totalCount2", m_page.getTotalElements());
	    model.addAttribute("param_url2", url);
	    model.addAttribute("size2", m_page.getSize());
	    model.addAttribute("totalPages2", m_page.getTotalPages());
	    
	    if (m_page.getTotalElements()==0)
	    	model.addAttribute("page2", null);
	    else
	    	model.addAttribute("page2", m_page);
	}
	
	public void setSettingThirdList(Model model, String url)
	{
		int current = m_page.getNumber() + 1;
		int begin = Math.max(1, current - 4);
		
		int end = Math.min(begin + 9, m_page.getTotalPages());
		
		model.addAttribute("list3", m_page.getContent());
	    model.addAttribute("beginIndex3", begin);
	    model.addAttribute("endIndex3", end);
	    model.addAttribute("currentIndex3", current);
	    model.addAttribute("totalCount3", m_page.getTotalElements());
	    model.addAttribute("param_url3", url);
	    model.addAttribute("size3", m_page.getSize());
	    model.addAttribute("totalPages3", m_page.getTotalPages());
	    
	    if (m_page.getTotalElements()==0)
	    	model.addAttribute("page_3", null);
	    else
	    	model.addAttribute("page_3", m_page);
	}
	
	public void setListSetting(Model model, String url, Long iTotalCount, int iPageNumber, int pageSize)
	{
		int current = iPageNumber;
		int begin = Math.max(1, current - 4);
		int totalPages = Math.toIntExact(iTotalCount / pageSize) + (iTotalCount % pageSize == 0 ? 0 : 1);
		int end = Math.min(begin + 9, totalPages);
		
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalCount", iTotalCount);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("list_url", url);
		model.addAttribute("size", pageSize);
		model.addAttribute("list", m_list);
	}
	
	public void setManualSetting(Model model, HttpServletRequest request)
	{
		String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("page.*?(?=&|\\?|$)[&]|page.*?(?=&|\\?|$)", "");
		}
		int begin = Math.max(1, c_list.getCurrent() - 4);
		int end = Math.min(begin + 9, c_list.getTotalPages());
		
		model.addAttribute("param_url", param_url);
		model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex",end);
		model.addAttribute("list", c_list.getList());
		model.addAttribute("currentIndex", c_list.getCurrent());
		model.addAttribute("totalCount",c_list.getTotalCount());
		model.addAttribute("size",c_list.getSize());
		model.addAttribute("totalPages",c_list.getTotalPages());
	}

}
