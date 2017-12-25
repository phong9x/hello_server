package org.trams.hello.bean;

import java.util.List;

public class MindCheckStatistics {
	private Integer id;
	
	private String question;
	
	private List<AnswerTestStatistics> list;
	
	private Integer totalAnswer;

	public Integer getTotalAnswer() {
		return totalAnswer;
	}

	public void setTotalAnswer(Integer totalAnswer) {
		this.totalAnswer = totalAnswer;
	}

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

	public List<AnswerTestStatistics> getList() {
		return list;
	}

	public void setList(List<AnswerTestStatistics> list) {
		this.list = list;
	}
	
	
	
}
