package com.whf.pojo;

import java.util.Date;

public class T_score {
	private Integer id;//课程表主键
	private Date examDate;//考试时间
	private Integer score;//分数
	private Integer paperId;//试卷Id
	private Integer studentId;//学生ID
	private Integer subjectId;//科目ID
	
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
