package com.eventlabs.domain;

import java.util.Date;

public class EventVideo {
	private Integer videoId;
	private String videoUrl;
	private EventDetails eventDetails;
	private String createdBy;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedOn;
	private Integer isActivate;
	
	public Integer getVideoId() {
		return videoId;
	}
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public EventDetails getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
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
	public Integer getIsActivate() {
		return isActivate;
	}
	public void setIsActivate(Integer isActivate) {
		this.isActivate = isActivate;
	}
	
	

}
