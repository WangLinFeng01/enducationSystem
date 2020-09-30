package com.whf.model;

public class Student {

private String userName;
private String password;

public Student() {
	super();

}

public Student(String userName, String password) {
	super();
	this.userName = userName;
	this.password = password;
}

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassWord() {
	return password;
}
public void setPassWord(String password) {
	this.password = password;
}	
	
}
