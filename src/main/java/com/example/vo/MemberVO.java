package com.example.vo;

import java.util.Arrays;

import org.springframework.stereotype.Component;

@Component
public class MemberVO {
	private String userId=null;
	private String userPw=null;
	private String userName=null;
	private String userPhone =null;
	private int userAge =0;
	private String userDate =null;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userPhone="
				+ userPhone + ", userAge=" + userAge + ", userDate=" + userDate + "]";
	}

	
	
	
	
	
	
	
	
	
}
