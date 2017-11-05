package com.eventlabs.dao;

import java.util.Map;

import com.eventlabs.beans.RequestHeader;

public interface CommonDAO {
	
	public void storeLogs();

	public Map saveLogs(RequestHeader requestHeader, String serviceName);

}
