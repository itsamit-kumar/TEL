package com.eventlabs.domain;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Login implements java.io.Serializable {

	private static final long serialVersionUID = -747152455504548902L;
	private Long loginId;
	private String loginSource;
	private String emailId;
	private String password;
	private String salt;
	private String createdBy;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedOn;
	private Integer isUserActivate;
	private Set<UserDetails> userDetailses = new HashSet<UserDetails>(0);
	
	
	
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getLoginId() {
		return loginId;
	}
	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}
	public String getLoginSource() {
		return loginSource;
	}
	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getIsUserActivate() {
		return isUserActivate;
	}
	public void setIsUserActivate(Integer isUserActivate) {
		this.isUserActivate = isUserActivate;
	}
	public Set<UserDetails> getUserDetailses() {
		return userDetailses;
	}
	public void setUserDetailses(Set<UserDetails> userDetailses) {
		this.userDetailses = userDetailses;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	
	
}
