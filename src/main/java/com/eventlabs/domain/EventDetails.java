package com.eventlabs.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EventDetails {
	private Integer eventId;
	private Login login;
	private Category category;
	private City city;
	private EventImage eventImage;
	private EventVideo eventVideo;
	private String eventName;
	private String eventStartDate;
	private String eventEndDate;
	private String eventLat;
	private String eventLong;
	private String eventLocation;
	private Integer isActive;
	private String createdBy;
	private Date createdOn;
	private String modifiedBy;
	private Date modifiedOn;
	private Set<EventImage> eventImages = new HashSet<EventImage>(0);
	
	
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public EventImage getEventImage() {
		return eventImage;
	}
	public void setEventImage(EventImage eventImage) {
		this.eventImage = eventImage;
	}
	public EventVideo getEventVideo() {
		return eventVideo;
	}
	public void setEventVideo(EventVideo eventVideo) {
		this.eventVideo = eventVideo;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public String getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	public String getEventLat() {
		return eventLat;
	}
	public void setEventLat(String eventLat) {
		this.eventLat = eventLat;
	}
	public String getEventLong() {
		return eventLong;
	}
	public void setEventLong(String eventLong) {
		this.eventLong = eventLong;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Set<EventImage> getEventImages() {
		return eventImages;
	}
	public void setEventImages(Set<EventImage> eventImages) {
		this.eventImages = eventImages;
	}
	
	
}
