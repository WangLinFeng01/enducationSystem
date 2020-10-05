package com.whf.pojo;

public class CourseScore {
	private Integer id;
 	private String courseName;
 	private String score;
	public Integer getId() {
		return id;
	}
	public CourseScore(Integer id, String courseName, String score) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.score = score;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	
}
