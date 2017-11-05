package com.eventlabs.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="ServiceRequest")
@Scope("prototype")
public class ServiceRequest {
	private String serviceName;
	private RequestHeader header;
	private Object parameters;
	
	private EventActivity eventActivity;
	private EventSession eventSession;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public RequestHeader getHeader() {
		return header;
	}
	public void setHeader(RequestHeader header) {
		this.header = header;
	}
	public Object getParameters() {
		return parameters;
	}
	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}
	public EventActivity getEventActivity() {
		return eventActivity;
	}
	public void setEventActivity(EventActivity eventActivity) {
		this.eventActivity = eventActivity;
	}
	public EventSession getEventSession() {
		return eventSession;
	}
	public void setEventSession(EventSession eventSession) {
		this.eventSession = eventSession;
	}
	
	

}
