package org.trams.hello.rest.common;

public class ReturnObjectData<T> {

	public int code;
	public String message;
	public T data;
	public Integer countTotal;

	public ReturnObjectData(int code, String message, T data, Integer countTotal) {
		this.code = code;
		this.message = message;
		this.data = data;
		this.countTotal = countTotal;
	}

	public ReturnObjectData(int code, String message) {
		this.data = null;
		this.countTotal = 0;
		this.code = code;
		this.message = message;
	}

	public Integer getCountTotal() {
		return countTotal;
	}

	public void setCountTotal(Integer countTotal) {
		this.countTotal = countTotal;
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
