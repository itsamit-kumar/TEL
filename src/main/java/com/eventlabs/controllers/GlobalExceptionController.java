package com.eventlabs.controllers;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.IllegalFormatConversionException;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.eventlabs.beans.ResponseHeader;
import com.eventlabs.beans.ServiceResponse;
import com.eventlabs.exception.CustomGenericException;
import com.eventlabs.utils.ErrorUtils;
import com.eventlabs.utils.EventConstants;
import com.google.gson.Gson;

@ControllerAdvice
public class GlobalExceptionController {

	@Autowired
	private WebApplicationContext mAppContext;

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionController.class);


	@ExceptionHandler(InvocationTargetException.class)
	public @ResponseBody String InvocationTargetExceptionResponse(InvocationTargetException ex,HttpServletRequest aRequest, HttpServletResponse response){
		LOGGER.info("InvocationTargetException thrown. " , ex);

		
		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later.");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);

	}


	@ExceptionHandler(CustomGenericException.class)
	public @ResponseBody String customErrorResponse(CustomGenericException ex,HttpServletRequest aRequest, HttpServletResponse response){
		LOGGER.info("Custom Error thrown. " , ex);

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later.");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);

	}

	@ExceptionHandler(ParseException.class)
	public @ResponseBody String jsonParseExceptionResponse(ParseException ex,HttpServletRequest aRequest, HttpServletResponse response){
		LOGGER.info("JsonParseException. " , ex);

		

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later.");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(java.text.ParseException.class)
	public @ResponseBody String textParseExceptionResponse(java.text.ParseException ex,HttpServletRequest aRequest, HttpServletResponse response){
		LOGGER.info("TextParseException. " ,ex);

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later.");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);

	}



	@ExceptionHandler(AuthenticationException.class)
	public @ResponseBody String handleAuthenticationException(HttpServletRequest aRequest,  AuthenticationException ex) {
		LOGGER.info("AuthenticationException. " +ex.getMessage());

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
	
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Invalid Username/Password.");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(NamingException.class)
	public @ResponseBody String handleNamingException(NamingException ex,HttpServletRequest aRequest) {
		LOGGER.info("NamingException. " +ex.getMessage());

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
	
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Invalid Username/Password");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(IllegalFormatConversionException.class)
	public  @ResponseBody String handleIllegalFormatConversionException(IllegalFormatConversionException ex,HttpServletRequest aRequest) {
		LOGGER.info("IllegalFormatConversionException. " +ex.getMessage());

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);

		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(SQLException.class)
	public  @ResponseBody String handleSQLException(SQLException ex,HttpServletRequest aRequest) {
		LOGGER.info("SQLException. " ,ex);

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(org.json.JSONException.class)
	public @ResponseBody String handleJSONException(org.json.JSONException ex,HttpServletRequest aRequest) {
		LOGGER.info("org.json.JSONException. " +ex.getMessage());

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Invalid Username/Password");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}

	@ExceptionHandler(RuntimeException.class)
	public  @ResponseBody String handleRuntimeException(RuntimeException ex,HttpServletRequest aRequest) {
		LOGGER.info("RuntimeException. " ,ex);

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);
	}



	@ExceptionHandler(Exception.class)
	public  @ResponseBody String handleRuntimeException(Exception ex,HttpServletRequest aRequest) {
		LOGGER.info("Exception. " ,ex);

		ErrorUtils lErrorUtils = new ErrorUtils();
		lErrorUtils.sendFormattedError(aRequest, ex);
		
		ResponseHeader lResponseHeader = new ResponseHeader();
		lResponseHeader.setStatusMessage("Error Occured. Please try again later");
		lResponseHeader.setOverallStatus(EventConstants.RESPONSE_FAILURE);
		ServiceResponse lServiceResp = (ServiceResponse) mAppContext.getBean("ServiceResponse");

		lServiceResp.setHeader(lResponseHeader);
		lServiceResp.setData(null);
		lServiceResp.setServiceName("");
		Gson gson = new Gson();
		return gson.toJson(lServiceResp);  

	}

}