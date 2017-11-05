package com.eventlabs.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.eventlabs.beans.EventInfo;
import com.eventlabs.beans.ResponseHeader;
import com.eventlabs.beans.ServiceRequest;
import com.eventlabs.beans.ServiceResponse;
import com.eventlabs.beans.UserInfo;
import com.eventlabs.dao.CommonDAO;
import com.eventlabs.dao.EventDAO;
import com.eventlabs.dao.UserDAO;
import com.eventlabs.domain.Login;
import com.eventlabs.utils.EventConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


@RestController
@RequestMapping("/userServices")
public class UserController {
	
	@Autowired
	private WebApplicationContext mAppContext;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private CommonDAO commonDao;
	
	@RequestMapping("/registration ")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "rawtypes" })
	public String registrarNewUser(@RequestBody String aInputJSON) {
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
		
		Gson lGson = new Gson();
		ServiceRequest lServiceRequest = lGson.fromJson(aInputJSON,ServiceRequest.class);
		Object lParameter = lServiceRequest.getParameters();
		
		Map lpm = (Map) lParameter;
		JsonElement jsonElement = lGson.toJsonTree(lpm);
		UserInfo userInfo = lGson.fromJson(jsonElement, UserInfo.class);
		String isUserCreated = userDao.userRegistration(userInfo);
		Map logResponse =commonDao.saveLogs(lServiceRequest.getHeader(),lServiceRequest.getServiceName());
		if(isUserCreated=="Y"){
			userInfo.setUserId(userInfo.getUserEmailId());
			userInfo.setIsUserLoggedIn("Y");
		}else{
			userInfo.setIsUserLoggedIn("N");
			userInfo.setErrorMessages("This EmailId  Already Exist");
		}
		
		ResponseHeader lResponseHeader = null;
		if(logResponse!=null){
			lResponseHeader = new ResponseHeader(lServiceRequest.getHeader().getRequestId(), lServiceRequest.getHeader().getRequestedBy(), Long.valueOf(String.valueOf(logResponse.get("responseId"))), (Timestamp)logResponse.get("responseTime"), EventConstants.RESPONSE_SUCCESS);
		}
		
		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setServiceName(lServiceRequest.getServiceName());
		lServiceResp.setData(userInfo);
		Gson gson =  new GsonBuilder().serializeNulls().create();
		return gson.toJson(lServiceResp);

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("/validateUser")
	@POST
	@Consumes("application/json")
	public String validateUser(@RequestBody String aInputJSON,HttpServletRequest aRequest) throws JsonSyntaxException, JsonIOException, IOException {
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
   
		Gson lGson = new Gson();
		ServiceRequest lSerReq = lGson.fromJson(aInputJSON,ServiceRequest.class);
		Object lParameter = lSerReq.getParameters();
		
		Map lpm = (Map) lParameter;
		JsonElement jsonElement = lGson.toJsonTree(lpm);
		UserInfo userInfo = lGson.fromJson(jsonElement, UserInfo.class);
		
		Login loginInfo = userDao.validateUser(userInfo.getUserId());
		if(loginInfo!=null && loginInfo.getPassword().equals(userInfo.getUserPassword())){
			//get Event List
			List<EventInfo>  eventInfos=new ArrayList<EventInfo>();
			userInfo.setIsUserLoggedIn("Y");
		}else if(loginInfo==null){
			userInfo.setErrorMessages("This email id not exist");
		}else{
			userInfo.setErrorMessages("UserId or Password not matched");
		}
				
		Map logResponse =commonDao.saveLogs(lSerReq.getHeader(),lSerReq.getServiceName());
		ResponseHeader lResponseHeader = null;
		if(logResponse!=null){
			lResponseHeader = new ResponseHeader(lSerReq.getHeader().getRequestId(), lSerReq.getHeader().getRequestedBy(), Long.valueOf(String.valueOf(logResponse.get("responseId"))), (Timestamp)logResponse.get("responseTime"), EventConstants.RESPONSE_SUCCESS);
		}			
		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setServiceName(lSerReq.getServiceName());
		lServiceResp.setData(userInfo);
		Gson gson =  new GsonBuilder().serializeNulls().create();
		return gson.toJson(lServiceResp);

	}

	@RequestMapping("/forgotPassword")
	@POST
	@Consumes("application/json")
	public String forgotPassword() {
		UserInfo userInfo =new UserInfo();
		
		return "createEvent";

	}
}
