package org.trams.hello.bean;

import java.util.ArrayList;
import java.util.List;

public class BannerForm {
	public Integer size;
	
	public List<ImageBanner> list = new ArrayList<>();

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<ImageBanner> getList() {
		return list;
	}

	public void setList(List<ImageBanner> list) {
		this.list = list;
	}
	
	
}
