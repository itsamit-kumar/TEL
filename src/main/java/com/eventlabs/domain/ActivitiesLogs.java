package com.eventlabs.domain;

import java.util.Date;

public class ActivitiesLogs {
	private Integer logId;
	private Integer loginId;
	private String activityType;
	private Date activityOn;
	private String serviceName;
	private String deviceName;
	
	
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public Date getActivityOn() {
		return activityOn;
	}
	public void setActivityOn(Date activityOn) {
		this.activityOn = activityOn;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public Integer getLoginId() {
		return loginId;
	}
	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}
	
	

}
