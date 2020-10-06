package com.whf.pojo;

import java.util.Date;

public class Question {
	
	 private Integer id;
	 //答案
	 private String answer;
	 private  Date joinTime;
	 //A选项的信息
	 private  String optionA;
	 //B选项的信息
	 private  String optionB;
	 //C选项的信息
	 private  String optionC;
	 //D选项的信息
	 private  String optionD;
	 //D选项的信息
	 private  String optionE;
	 //题目
	 private  String subText;
	 private  String type;
	 private  Integer subjectId;
	 private  Integer paperId;
	public Question() {
		super();
	}
	
	
	public Question(String answer, String optionA, String optionB, String optionC, String optionD,String optionE,
			String subText) {
		super();
		this.answer = answer;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.optionE = optionE;
		this.subText = subText;
	}


	@Override
	public String toString() {
		return "Question [answer=" + answer + ", joinTime=" + joinTime + ", optionA=" + optionA + ", optionB=" + optionB
				+ ", optionC=" + optionC + ", optionD=" + optionD + ", optionE=" + optionE + ", subText=" + subText
				+ ", type=" + type + ", subjectId=" + subjectId + ", paperId=" + paperId + "]";
	}


	public Question(String answer, Date joinTime, String optionA, String optionB, String optionC, String optionD,
			String optionE, String subText, String type, Integer subjectId, Integer paperId) {
		super();
		this.answer = answer;
		this.joinTime = joinTime;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		this.optionE = optionE;
		this.subText = subText;
		this.type = type;
		this.subjectId = subjectId;
		this.paperId = paperId;
	}


	public Question(String answer, String subText) {
		super();
		this.answer = answer;
		this.subText = subText;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Date getJoinTime() {
		return joinTime;
	}


	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	
	
	public String getOptionE() {
		return optionE;
	}


	public void setOptionE(String optionE) {
		this.optionE = optionE;
	}


	public String getOptionA() {
		return optionA;
	}
	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}
	public String getOptionB() {
		return optionB;
	}
	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}
	public String getOptionC() {
		return optionC;
	}
	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}
	public String getOptionD() {
		return optionD;
	}
	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}
	public String getSubText() {
		return subText;
	}
	public void setSubText(String subText) {
		this.subText = subText;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
}
