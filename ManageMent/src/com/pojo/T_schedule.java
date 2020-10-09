package com.pojo;

public class T_schedule {

	private Integer id;
	private String date;
	private String info;
	private String status;
	
	public T_schedule() {
		super();
	}

	public T_schedule(Integer id, String date, String info, String status) {
		super();
		this.id = id;
		this.date = date;
		this.info = info;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
