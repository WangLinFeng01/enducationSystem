package com.whf.pojo;

import java.util.Date;

public class T_score {
	private Integer id;//�γ̱�����
	private Date exameDate;//����ʱ��
	private String score;//����
	private Integer paperId;//�Ծ�Id
	private Integer studentId;//ѧ��ID
	private Integer subjectId;//��ĿID
	
	public T_score() {
		super();
	}

	public T_score(Integer id, Date exameDate, String score, Integer paperId, Integer studentId, Integer subjectId) {
		super();
		this.id = id;
		this.exameDate = exameDate;
		this.score = score;
		this.paperId = paperId;
		this.studentId = studentId;
		this.subjectId = subjectId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getExameDate() {
		return exameDate;
	}

	public void setExameDate(Date exameDate) {
		this.exameDate = exameDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "T_score [id=" + id + ", exameDate=" + exameDate + ", score=" + score + ", paperId=" + paperId
				+ ", studentId=" + studentId + ", subjectId=" + subjectId + "]";
	}
		
}
