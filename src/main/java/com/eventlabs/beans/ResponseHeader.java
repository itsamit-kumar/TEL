package com.eventlabs.beans;

import java.sql.Timestamp;


public class ResponseHeader {
	private String requestId;
	private String requestedBy;
	private long responseID;
	private Timestamp responseTime;
	private String overallStatus;
	private String statusMessage;
	
	public ResponseHeader(){
		
	}
	
	public ResponseHeader(String requestId, String requestedBy, long responseID, Timestamp responseTime, String overallStatus) {
		super();
		this.requestId = requestId;
		this.requestedBy = requestedBy;
		this.responseID = responseID;
		this.overallStatus = overallStatus;
		this.responseTime = responseTime;
	}
	
	public Timestamp getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

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
	public long getResponseID() {
		return responseID;
	}
	public void setResponseID(long responseID) {
		this.responseID = responseID;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	
}
