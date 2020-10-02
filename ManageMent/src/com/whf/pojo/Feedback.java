package com.whf.pojo;

public class Feedback {
 	private Integer id;
 	private String fname;
 	private String feedback;
	public Feedback(Integer id, String fname, String feedback) {
		super();
		this.id = id;
		this.fname = fname;
		this.feedback = feedback;
	}
	public Feedback() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
}
