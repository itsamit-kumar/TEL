package com.eventlabs.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:properties/emailId.properties")

public class ErrorUtils {

	@Autowired
	Environment mPropEnv;
	private static final Logger LOGGER = Logger.getLogger(ErrorUtils.class);

	public void  sendFormattedError(HttpServletRequest aRequest,Throwable aError){

		try {
			String lUser;
			String lDate = new Date().toString();
			String lEnv = "Test";//System.getProperty("event.life", "local");
			String lMailSubject = "EventLabs ERROR ("+lEnv+")";
			String lMailToAddress = "amitkumarceb@gmail.com";

			if(lEnv !=null && ( (!"local".equals(lEnv)) && (!"dev".equals(lEnv))) ){

				if(!StringUtils.isEmpty(aRequest.getHeader("AUTH_USER"))){
					lUser = aRequest.getHeader("AUTH_USER");
				} else if(!StringUtils.isEmpty(String.valueOf(aRequest.getSession().getAttribute("mobileUserId")))){
					lUser=  String.valueOf(aRequest.getSession().getAttribute("mobileUserId"));
				} else {	
					lUser = aRequest.getRemoteUser();
				}

				String lMessage = "<table width='100%' border='0' cellspacing='0' cellpadding='0' height='30px'> " +
						"  <tr> " +
						"    <td align='center' style='color:#009;font-size:22px;'><strong>Send IT Back Error Report</strong></td> " +
						"  </tr> " +
						"</table> " +
						"<table> " +
						"<tr>&nbsp;</tr> " +
						"</table> " +
						"<table width='30%' border='1' cellspacing='0' cellpadding='0'> " +
						"  <tr> " +
						"    <td width='29%'><strong>Environment</strong></td> " +
						"    <td width='71%'>"+lEnv+"</td> " +
						"  </tr> " +
						"  <tr> " +
						"    <td><strong>User</strong></td> " +
						"    <td>"+lUser+"</td> " +
						"  </tr> " +
						"  <tr> " +
						"    <td><strong>Date</strong></td> " +
						"    <td>"+lDate+"</td> " +
						"  </tr> " +
						"</table> " +
						" " +
						"<h3><span style='color:red;'>Error Message</span>: <span style='color:#00C;'>"+getErrorMessage(aError)+"</span></h3> " +
						"<table width='100%' border='1' cellspacing='0' cellpadding='0'> " +
						"  <tr style='background-color:#999;color:#fff;'> " +
						"    <td width='8%'><strong>Line Number</strong></td> " +
						"    <td width='20%'><strong>File Name</strong></td> " +
						"    <td width='50%'><strong>Class Name</strong></td> " +
						"    <td width='22%'><strong>Method</strong></td> " +
						"  </tr> ";

				StackTraceElement[] aStackTraceElement = aError.getStackTrace();
				
				StringBuilder buf = new StringBuilder();
				
				if(aStackTraceElement != null){

					for(StackTraceElement lTempError : aStackTraceElement){

						buf.append("  <tr> " +
								"    <td   align='center'>"+ lTempError.getLineNumber() +"</td> " +
								"    <td>"+ lTempError.getFileName() +"</td> " +
								"    <td>"+ lTempError.getClassName() +"</td> " +
								"    <td>"+ lTempError.getMethodName() +"</td> " +
								"  </tr> ");
					}					
				}
				lMessage =buf.toString();
				lMessage+="</table> "; 

				//sendErrorMail(lMailToAddress, lMailSubject, lMessage);
			}

		} catch(Exception e){

			LOGGER.info("Error in formatting Error Mail................",e);
		}
	}

	public  void sendErrorMail(String aToAddress, String aSubject,String aMessage) {

		String to = aToAddress;
		String from = aToAddress;
		String host = "smtp.gmail.com";//"javamail.cisco.com";
		String lSubject = aSubject;

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(lSubject);
			message.setContent(aMessage,"text/html; charset=utf-8");

			Transport.send(message);

		} catch (Exception mex) {
			LOGGER.info("Failed to Send ErrorMail................",mex);
		}
	}


	private String getErrorMessage(Throwable aError){

		StringWriter errors = new StringWriter();
		aError.printStackTrace(new PrintWriter(errors));
		String s1 =  errors.toString();		
		String s2[] = s1.split("at");

		if(s2 != null && s2.length>0){
			if(!StringUtils.isEmpty(aError.getMessage())) {
				return s2[0] + ":" + aError.getMessage();
			} else {
				return s2[0];
			}
		}

		return "";
	}


}
