package com.pojo;

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

	public T_score(Integer id, Date exameDate, Integer score, Integer paperId, Integer studentId, Integer subjectId) {
		super();
		this.id = id;
		this.examDate = exameDate;
		this.score = score;
		this.paperId = paperId;
		this.studentId = studentId;
		this.subjectId = subjectId;
	}
	
	
	
	public T_score(Integer score, Integer studentId, Integer subjectId) {
		super();
		this.score = score;
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
		return examDate;
	}

	public void setExameDate(Date exameDate) {
		this.examDate = exameDate;
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

	@Override
	public String toString() {
		return "T_score [id=" + id + ", exameDate=" + examDate + ", score=" + score + ", paperId=" + paperId
				+ ", studentId=" + studentId + ", subjectId=" + subjectId + "]";
	}
		
}
