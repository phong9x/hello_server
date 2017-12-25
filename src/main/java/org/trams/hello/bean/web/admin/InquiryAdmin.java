package org.trams.hello.bean.web.admin;

import java.util.Date;

public class InquiryAdmin {
	private Integer id;
	private String question;
	private String answer;
	private Date createDate;
	private Integer status;
	private Integer categoryId;
	private String askEmail;
	private String categoryName;
	private String answerName;
	private String couselorName;
	private Integer adminId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getAskEmail() {
		return askEmail;
	}

	public void setAskEmail(String askEmail) {
		this.askEmail = askEmail;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getAnswerName() {
		return answerName;
	}

	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}

	public String getCouselorName() {
		return couselorName;
	}

	public void setCouselorName(String couselorName) {
		this.couselorName = couselorName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		return "InquiryAdmin [id=" + id + ", question=" + question + ", answer=" + answer + ", createDate=" + createDate
				+ ", status=" + status + ", categoryId=" + categoryId + ", askEmail=" + askEmail + ", categoryName="
				+ categoryName + ", answerName=" + answerName + ", couselorName=" + couselorName + ", adminId="
				+ adminId + "]";
	}

}
