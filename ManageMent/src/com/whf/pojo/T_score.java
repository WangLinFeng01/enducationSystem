package com.whf.pojo;

import java.util.Date;

public class T_score {
	private Integer id;//�γ̱�����
	private Date examDate;//����ʱ��
	private Integer score;//����
	private Integer paperId;//�Ծ�Id
	private Integer studentId;//ѧ��ID
	private Integer subjectId;//��ĿID
	
	public T_score() {
		super();
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	
	public T_score(Integer score, Integer studentId, Integer subjectId) {
		super();
		this.score = score;
		this.studentId = studentId;
		this.subjectId = subjectId;
	}

	public T_score(Integer id, Date examDate, Integer score, Integer paperId, Integer studentId, Integer subjectId) {
		super();
		this.id = id;
		this.examDate = examDate;
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

	

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
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

		
}
