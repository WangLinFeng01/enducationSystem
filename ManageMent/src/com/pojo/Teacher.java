package com.pojo;

public class Teacher {
	private String teaName;
	private String password;
	

public Teacher() {
	super();

}

public Teacher(String teaName, String password) {
	super();
	this.teaName = teaName;
	this.password = password;
}

public String getUserName() {
	return teaName;
}
public void setUserName(String userName) {
	this.teaName = userName;
}
public String getPassWord() {
	return password;
}
public void setPassWord(String password) {
	this.password = password;
}	
	
	
	
}
