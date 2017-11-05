package com.eventlabs.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.eventlabs.beans.RequestHeader;
import com.eventlabs.domain.ActivitiesLogs;

public class CommonDAOImpl extends HibernateConnectionDAO implements CommonDAO{

	@Override
	public void storeLogs() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	@Override
	public Map saveLogs(RequestHeader aReqHeader, String aServiceName) {
		long responseId = new Date().getTime();
		Timestamp responseTime = new Timestamp(responseId);
		/*Object[] logArgs = new Object[] {aReqHeader.getDeviceName(),aReqHeader.getOsVersion(),aReqHeader.getOsName(),aReqHeader.getServiceVersion(),aReqHeader.getAppId(),
				aReqHeader.getRequestId(), responseId, aReqHeader.getNetworkType(), aReqHeader.getRequestedBy(), aReqHeader.getRequestTime(), responseTime ,
				aServiceName, aReqHeader.getRequestedBy(), aReqHeader.getRequestedBy()
        };*/
		ActivitiesLogs  activitiesLogs  =new ActivitiesLogs();
		activitiesLogs.setActivityOn(new Date());
		activitiesLogs.setServiceName(aServiceName);
		Serializable i = executeSave(activitiesLogs);
		
		if(i != null){
			Map<String, Object> logResponse = new HashMap<String, Object>();
			logResponse.put("responseId", responseId);
			logResponse.put("responseTime", responseTime);
			return logResponse;
		}else{
			return null;
		}
	}


}
