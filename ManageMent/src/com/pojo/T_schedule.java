package com.pojo;

public class T_schedule {

	private Integer id;
	private String subject;
	private Integer hour;
	private Integer speed;
	
	public T_schedule() {
		super();
	}

	public T_schedule(String subject, Integer hour, Integer speed) {
		super();
		this.subject = subject;
		this.hour = hour;
		this.speed = speed;
	}


	public T_schedule(Integer id, String subject, Integer hour, Integer speed) {
		super();
		this.id = id;
		this.subject = subject;
		this.hour = hour;
		this.speed = speed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
}
