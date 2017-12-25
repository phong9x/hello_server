package org.trams.hello.bean;

import org.springframework.web.multipart.MultipartFile;

public class ImageBanner {
	private Integer id;
	
	private String imageUrl;
	
	private String imageName;
	
	private Integer typeChange;
	
	private MultipartFile file;

	private Short orderNumber;
	
	

	public Short getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Short orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeChange() {
		return typeChange;
	}

	public void setTypeChange(Integer typeChange) {
		this.typeChange = typeChange;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
