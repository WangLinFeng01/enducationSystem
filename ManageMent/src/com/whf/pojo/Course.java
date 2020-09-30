package com.whf.pojo;

public class Course {
	private Integer id;
	private String week;
	private String morning;
	private String afternoon;
	private String evening;
	public Course(Integer id, String week, String morning, String afternoon, String evening) {
		super();
		this.id = id;
		this.week = week;
		this.morning = morning;
		this.afternoon = afternoon;
		this.evening = evening;
	}
	public String getEvening() {
		return evening;
	}
	public void setEvening(String evening) {
		this.evening = evening;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}
	
	public Course() {
		super();
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", week=" + week + ", morning=" + morning + ", afternoon=" + afternoon
				+ ", evening=" + evening + "]";
	}
	
	

}
