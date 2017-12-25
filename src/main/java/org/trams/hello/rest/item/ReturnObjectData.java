package org.trams.hello.rest.item;

public class ReturnObjectData<T>{
	
	public int code;
	public int page_flag;
	public String message;
	public T data;
	
	public ReturnObjectData(int code,String message,T data, int page_flag){
		this.data = data;
		this.code = code;
		this.page_flag = code;
		this.message = message;
	} 



	public int getPage_flag() {
		return page_flag;
	}
	public void setPage_flag(int page_flag) {
		this.page_flag = page_flag;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
 
}
