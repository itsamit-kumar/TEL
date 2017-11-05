package com.eventlabs.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="UserInfo")
@Scope("prototype")
public class UserInfo {
	
	private String userFName;
	private String userLName;
	private String userPassword;
	private String loginSource;
	private String userEmailId;
	private String phoneNumber;
	private String userId;
	private String userType;
	private String isUserExist;
	private String errorMessages;
	private String isUserLoggedIn;
	
	public String getUserFName() {
		return userFName;
	}
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getLoginSource() {
		return loginSource;
	}
	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}
	public String getIsUserExist() {
		return isUserExist;
	}
	public void setIsUserExist(String isUserExist) {
		this.isUserExist = isUserExist;
	}
	public String getErrorMessages() {
		return errorMessages;
	}
	public void setErrorMessages(String errorMessages) {
		this.errorMessages = errorMessages;
	}
	public String getIsUserLoggedIn() {
		return isUserLoggedIn;
	}
	public void setIsUserLoggedIn(String isUserLoggedIn) {
		this.isUserLoggedIn = isUserLoggedIn;
	}
	

}
