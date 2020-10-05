package com.whf.pojo;

import java.util.Date;

public class Paper {
	private Integer id;
	private Date joinDate;
	private String paperName;
	private String subjectId;
	public Paper(Integer id, Date joinDate, String paperName, String subjectId) {
		super();
		this.id = id;
		this.joinDate = joinDate;
		this.paperName = paperName;
		this.subjectId = subjectId;
	}
	public Paper(Date joinDate, String paperName, String subjectId) {
		super();
		this.joinDate = joinDate;
		this.paperName = paperName;
		this.subjectId = subjectId;
	}
	public Paper() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
}
