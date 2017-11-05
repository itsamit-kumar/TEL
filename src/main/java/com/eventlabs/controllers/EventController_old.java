package com.eventlabs.controllers;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.eventlabs.beans.EventInfo;
import com.eventlabs.beans.ResponseHeader;
import com.eventlabs.beans.ServiceRequest;
import com.eventlabs.beans.ServiceResponse;
import com.eventlabs.beans.UserInfo;
import com.eventlabs.dao.CommonDAO;
import com.eventlabs.utils.EventConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class EventController_old {
	
	@Autowired
	private WebApplicationContext mAppContext;
	

	@Autowired
	private CommonDAO commonDao;
	
/*
	@Autowired
	private CommonDAO commonDao;*/
	
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
	public String createEvent(@RequestParam("file") MultipartFile file,  @RequestParam("username") String aInputJSON /*ServiceRequest aInputJSON*/){
		//System.out.println("inside controller :: :: "+aInputJSON.getServiceName());
		//System.out.println("inside controller :: :: "+aInputJSON.getHeader());
		//System.out.println("inside controller :: :: "+aInputJSON.getParameters());
		System.out.println("form Data "+aInputJSON);
		
		/*Gson lGson = new Gson();
		ServiceRequest lServiceRequest = lGson.fromJson(userData,ServiceRequest.class);
		Object lParameter = lServiceRequest.getParameters();*/
		
		Date currentDate = new Date();
		String name = null;
		if (!file.isEmpty()) {
    		name =file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator+ currentDate.getTime()+name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				System.out.println(serverFile);
				stream.write(bytes);
				stream.close();

				System.out.println("You successfully uploaded file=" + name);
			} catch (Exception e) {
				System.out.println("You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + name
					+ " because the file was empty.");
		}
		
		//return "createEvent";
		
		
		/*String rootPath = System.getProperty("user.dir");
	    File dir = new File(rootPath + File.separator + "webapp"+File.separator+"res"+File.separator+"img");
	    if (!dir.exists())
	        dir.mkdirs();
	    String fileName = file.getOriginalFilename();
	    File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

	    try {
	        inputStream = file.getInputStream();

	        if (!newFile.exists()) {
	            newFile.createNewFile();
	        }
	        outputStream = new FileOutputStream(newFile);
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        while ((read = inputStream.read(bytes)) != -1) {
	            outputStream.write(bytes, 0, read);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }*/
		
        ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");
		
		Gson lGson = new Gson();
		ServiceRequest lServiceRequest = lGson.fromJson(aInputJSON,ServiceRequest.class);
		Object lParameter = lServiceRequest.getParameters();
		
		Map lpm = (Map) lParameter;
		JsonElement jsonElement = lGson.toJsonTree(lpm);
		UserInfo userInfo = lGson.fromJson(jsonElement, UserInfo.class);
		//userDao.userRegistration(userInfo);
		Map logResponse =commonDao.saveLogs(lServiceRequest.getHeader(),lServiceRequest.getServiceName());

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
	
    @RequestMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {
System.out.println(" inside controller");
        byte[] bytes;

        if (!file.isEmpty()) {
             bytes = file.getBytes();
            //store file in storage
        }

        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), username));
    }
    
    @RequestMapping(value = "/uploadEvent")
    public void uploadEvent(@RequestParam("file") MultipartFile file, @RequestParam("username") String username ) throws IOException {
    	String name = null;
		if (!file.isEmpty()) {
    		name =file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				System.out.println(serverFile);
				stream.write(bytes);
				stream.close();

				System.out.println("You successfully uploaded file=" + name);
			} catch (Exception e) {
				System.out.println("You failed to upload " + name + " => " + e.getMessage());
			}
		} else {
			System.out.println("You failed to upload " + name
					+ " because the file was empty.");
		}

    }
}
