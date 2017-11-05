package com.eventlabs.beans;

import java.util.List;

public class MasterData {
	private List<City> city;
	private List<Country> country;
	private List<IndustryType> industryType;
	private List<EventType> eventType;
	
	public List<City> getCity() {
		return city;
	}
	public void setCity(List<City> city) {
		this.city = city;
	}
	public List<Country> getCountry() {
		return country;
	}
	public void setCountry(List<Country> country) {
		this.country = country;
	}
	public List<IndustryType> getIndustryType() {
		return industryType;
	}
	public void setIndustryType(List<IndustryType> industryType) {
		this.industryType = industryType;
	}
	public List<EventType> getEventType() {
		return eventType;
	}
	public void setEventType(List<EventType> eventType) {
		this.eventType = eventType;
	}

	
}
