package com.eventlabs.beans;

import java.util.ArrayList;
import java.util.List;

public class SearchEvent {
	private String industry;
	private String country;
	private String city;
	private List<EventInfo> eventInfos =new ArrayList<EventInfo>();
	
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<EventInfo> getEventInfos() {
		return eventInfos;
	}
	public void setEventInfos(List<EventInfo> eventInfos) {
		this.eventInfos = eventInfos;
	}

}
