package com.eventlabs.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component(value="ServiceResponse")
@Scope("prototype")
public class ServiceResponse {
	private String serviceName;
	private ResponseHeader header;
	private Object data;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ResponseHeader getHeader() {
		return header;
	}
	public void setHeader(ResponseHeader header) {
		this.header = header;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
