package com.eventlabs.beans;

import java.sql.Timestamp;

public class RequestHeader {

	private String requestId;
	private String requestedBy;
	private String serviceVersion;
	private String deviceName;
	private String osVersion;
	private String osName;
	private String appId;
	private String networkType;
	private Timestamp requestTime;
	private String deviceOSName;
	private String deviceOSVersion;
	private String deviceNetworkType;
	private String version;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getServiceVersion() {
		return serviceVersion;
	}
	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public Timestamp getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Timestamp requestTime) {
		this.requestTime = requestTime;
	}
	public String getDeviceOSName() {
		return deviceOSName;
	}
	public void setDeviceOSName(String deviceOSName) {
		this.deviceOSName = deviceOSName;
	}
	public String getDeviceOSVersion() {
		return deviceOSVersion;
	}
	public void setDeviceOSVersion(String deviceOSVersion) {
		this.deviceOSVersion = deviceOSVersion;
	}
	public String getDeviceNetworkType() {
		return deviceNetworkType;
	}
	public void setDeviceNetworkType(String deviceNetworkType) {
		this.deviceNetworkType = deviceNetworkType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	

}
