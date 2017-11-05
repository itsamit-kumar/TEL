package com.eventlabs.controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.eventlabs.beans.EventInfo;
import com.eventlabs.beans.MasterData;
import com.eventlabs.beans.ResponseHeader;
import com.eventlabs.beans.SearchEvent;
import com.eventlabs.beans.ServiceRequest;
import com.eventlabs.beans.ServiceResponse;
import com.eventlabs.dao.CommonDAO;
import com.eventlabs.dao.EventDAO;
import com.eventlabs.utils.EventConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

@RestController
@RequestMapping("/eventServices")
public class EventController {
	
	@Autowired
	private WebApplicationContext mAppContext;
	

	@Autowired
	private CommonDAO commonDao;
	
	@Autowired
	private EventDAO eventDao;
	
	@RequestMapping("/getEventList")
	public List<EventInfo> getEventLists(){
	List<EventInfo> eventList=new ArrayList<EventInfo>();
	for(int i=0;i<5;i++){
		EventInfo eventInfo = new EventInfo();
		eventInfo.setEventType("Event Type"+i);
		eventInfo.setEventName("Event Name"+i);
		eventInfo.setEventLocation("Location "+i);
		eventInfo.seteStartDate("Start Date "+i);
		eventInfo.seteEndDate("End Date "+i);
		eventList.add(eventInfo);	
	}
	
	
	//eventList.add(eventInfo1);
		return eventList;
		
	}
	
	@RequestMapping("/createEvent")
	public String createEvent(@RequestParam("file") MultipartFile file,  @RequestParam("eventInfo") String aInputJSON){
		Date currentDate = new Date();
		String name = null;
		List<String> imagesName=new ArrayList<String>();
		if (!file.isEmpty()) {
    		name = currentDate.getTime()+file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				//File serverFile = new File(dir.getAbsolutePath()+ File.separator+name);
				File serverFile = new File("c:\\images"+ File.separator+name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				System.out.println(serverFile);
				stream.write(bytes);
				stream.close();
				System.out.println("You successfully uploaded file=" + name);
				imagesName.add(name);
			} catch (Exception e) {
				System.out.println("You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + name
					+ " because the file was empty.");
		}

		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
		
		Gson lGson = new Gson();
		ServiceRequest lServiceRequest = lGson.fromJson(aInputJSON,ServiceRequest.class);
		Object lParameter = lServiceRequest.getParameters();
		Map lpm = (Map) lParameter;
		JsonElement jsonElement = lGson.toJsonTree(lpm);
		EventInfo eventInfo = lGson.fromJson(jsonElement, EventInfo.class);
		eventInfo.setImageName(name);
		//userDao.userRegistration(userInfo);
		eventDao.saveEvent(eventInfo);
		Map logResponse =commonDao.saveLogs(lServiceRequest.getHeader(),lServiceRequest.getServiceName());

		ResponseHeader lResponseHeader = null;
		if(logResponse!=null){
			lResponseHeader = new ResponseHeader(lServiceRequest.getHeader().getRequestId(), lServiceRequest.getHeader().getRequestedBy(), Long.valueOf(String.valueOf(logResponse.get("responseId"))), (Timestamp)logResponse.get("responseTime"), EventConstants.RESPONSE_SUCCESS);
		}
		
		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setServiceName(lServiceRequest.getServiceName());
		lServiceResp.setData(eventInfo);
		Gson gson =  new GsonBuilder().serializeNulls().create();
		return gson.toJson(lServiceResp);
	}
	   
    @RequestMapping("/searchEvent")
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "rawtypes" })
	public String searchEvent(@RequestBody String aInputJSON) {
    	
        ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
		
		Gson lGson = new Gson();
		ServiceRequest lServiceRequest = lGson.fromJson(aInputJSON,ServiceRequest.class);
		Object lParameter = lServiceRequest.getParameters();
		
		Map lpm = (Map) lParameter;
		JsonElement jsonElement = lGson.toJsonTree(lpm);
		SearchEvent searchParm = lGson.fromJson(jsonElement, SearchEvent.class);
		List<EventInfo> eventInfos= eventDao.searchEvent(searchParm);
		Map logResponse =commonDao.saveLogs(lServiceRequest.getHeader(),lServiceRequest.getServiceName());

		ResponseHeader lResponseHeader = null;
		if(logResponse!=null){
			lResponseHeader = new ResponseHeader(lServiceRequest.getHeader().getRequestId(), lServiceRequest.getHeader().getRequestedBy(), Long.valueOf(String.valueOf(logResponse.get("responseId"))), (Timestamp)logResponse.get("responseTime"), EventConstants.RESPONSE_SUCCESS);
		}
		
		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setServiceName(lServiceRequest.getServiceName());
		lServiceResp.setData(eventInfos);
		Gson gson =  new GsonBuilder().serializeNulls().create();
		return gson.toJson(lServiceResp);

	}
    
    @RequestMapping("/getMasterData")
   	@POST
   	@Consumes("application/json")
   	@Produces("application/json")
   	@SuppressWarnings({ "rawtypes" })
   	public String getMasterData(@RequestBody String aInputJSON) {
       	System.out.println("inside master data");
        ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
   		
   		Gson lGson = new Gson();
   		ServiceRequest lServiceRequest = lGson.fromJson(aInputJSON,ServiceRequest.class);
   		//Object lParameter = lServiceRequest.getParameters();
   		//Map lpm = (Map) lParameter;
   		//JsonElement jsonElement = lGson.toJsonTree(lpm);
   		//SearchEvent searchParm = lGson.fromJson(jsonElement, SearchEvent.class);
   		
   		Map masterData= eventDao.getMasterData();
   		//MasterData masterData=createMasterData(masterList);
   		Map logResponse =commonDao.saveLogs(lServiceRequest.getHeader(),lServiceRequest.getServiceName());

   		ResponseHeader lResponseHeader = null;
   		if(logResponse!=null){
   			lResponseHeader = new ResponseHeader(lServiceRequest.getHeader().getRequestId(), lServiceRequest.getHeader().getRequestedBy(), Long.valueOf(String.valueOf(logResponse.get("responseId"))), (Timestamp)logResponse.get("responseTime"), EventConstants.RESPONSE_SUCCESS);
   		}
   		
   		lServiceResp.setHeader(lResponseHeader);
   		lServiceResp.setServiceName(lServiceRequest.getServiceName());
   		lServiceResp.setData(masterData);
   		Gson gson =  new GsonBuilder().serializeNulls().create();
   		return gson.toJson(lServiceResp);

   	}

	private MasterData createMasterData(List masterList) {
		List<Object> cityList = (List<Object>) masterList.get(0);
		/*for (int i = 0; i < cityList.size(); i++) {
			City city=(City) cityList.get(i);
			City city = new City();
			city.setId(id);
		}
		*/
		return null;
	}
}
