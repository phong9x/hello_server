package org.trams.hello.web.bean.search;

public class SearchCommon {
	
	Integer page;
	
	String type;
	
	String keyWord;
	
	
	public SearchCommon(){
		page = 1;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
