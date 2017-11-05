package com.eventlabs.domain;

import java.io.Serializable;

public class LoginConfirmation implements Serializable {

	private static final long serialVersionUID = -4010977446449178460L;

	private Long id;
	private Login loginId;
	private String randomGeneratedKey;
	private Integer isConfirmLinkActivate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Login getLoginId() {
		return loginId;
	}
	public void setLoginId(Login loginId) {
		this.loginId = loginId;
	}
	public String getRandomGeneratedKey() {
		return randomGeneratedKey;
	}
	public void setRandomGeneratedKey(String randomGeneratedKey) {
		this.randomGeneratedKey = randomGeneratedKey;
	}
	public Integer getIsConfirmLinkActivate() {
		return isConfirmLinkActivate;
	}
	public void setIsConfirmLinkActivate(Integer isConfirmLinkActivate) {
		this.isConfirmLinkActivate = isConfirmLinkActivate;
	}

	
}
