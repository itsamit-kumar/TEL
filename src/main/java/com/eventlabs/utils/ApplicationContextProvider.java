package com.eventlabs.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

	public class ApplicationContextProvider implements ApplicationContextAware{

		 private static WebApplicationContext context;

		   public static WebApplicationContext getApplicationContext() {
		        return context;
		    }

			@Override
			public void setApplicationContext(ApplicationContext applicationContext)
					throws BeansException {
	
				setAppContext(applicationContext);
			}
			
			public static void setAppContext(ApplicationContext applicationContext){
				context = (WebApplicationContext) applicationContext;
			}
}

