package com.pojo;

public class TScore {
	private Integer studentId;
	private String userName;
	private	Integer chinese;
	private	Integer mathematics;
	private	Integer english;
	private	Integer total;
	private Integer score;
	
	public TScore() {
		super();
	}
	
	

	public TScore(Integer studentId, String userName, Integer score) {
		super();
		this.studentId = studentId;
		this.userName = userName;
		this.score = score;
	}



	public TScore(Integer studentId, String userName, Integer chinese, Integer mathematics, Integer english,
			Integer total) {
		super();
		this.studentId = studentId;
		this.userName = userName;
		this.chinese = chinese;
		this.mathematics = mathematics;
		this.english = english;
		this.total = total;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getChinese() {
		return chinese;
	}

	public void setChinese(Integer chinese) {
		this.chinese = chinese;
	}

	public Integer getMathematics() {
		return mathematics;
	}

	public void setMathematics(Integer mathematics) {
		this.mathematics = mathematics;
	}

	public Integer getEnglish() {
		return english;
	}

	public void setEnglish(Integer english) {
		this.english = english;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}



	@Override
	public String toString() {
		return "TScore [studentId=" + studentId + ", userName=" + userName + ", chinese=" + chinese + ", mathematics="
				+ mathematics + ", english=" + english + ", total=" + total + ", score=" + score + "]";
	}


	
	
	
}
