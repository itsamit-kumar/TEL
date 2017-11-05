package com.eventlabs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.eventlabs.beans.EventInfo;
import com.eventlabs.beans.MasterData;
import com.eventlabs.beans.SearchEvent;
import com.eventlabs.domain.Category;
import com.eventlabs.domain.City;
import com.eventlabs.domain.Country;
import com.eventlabs.domain.EventDetails;
import com.eventlabs.domain.EventImage;
import com.eventlabs.domain.EventType;
import com.eventlabs.domain.EventVideo;
import com.eventlabs.domain.IndustryType;
import com.eventlabs.domain.Login;

public class EventDAOImpl extends HibernateConnectionDAO implements EventDAO{
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void saveEvent(EventInfo eventInfo) {
		
		    System.out.println("kk >> "+eventInfo.getUserId());
		    
		   Login userInfo = getUserInfo(eventInfo.getUserId());
	
			EventDetails eventDetails= new EventDetails();
			
			eventDetails.setEventName(eventInfo.getEventName());
			
			Category category = new Category();
			category.setCategoryId(Long.valueOf(eventInfo.getEventType()));
			eventDetails.setCategory(category);
			
			City city = new City();
			city.setCityId(Long.valueOf(eventInfo.getCity()));
			eventDetails.setCity(city);
			
			eventDetails.setCreatedBy("username");
			eventDetails.setCreatedOn(new Date());
			eventDetails.setEventEndDate(eventInfo.geteEndDate());
			//EventImage eventImage = new EventImage();
			//eventImage.setImageUrl(eventInfo.getEventName());
			//eventDetails.setEventImage(eventImage);
			
			eventDetails.setEventLat("lat");
			eventDetails.setEventLong("long");
			eventDetails.setEventLocation("formated location");
			eventDetails.setEventStartDate(eventInfo.geteStartDate());
			
			//EventVideo eventVideo = null;
			//eventDetails.setEventVideo(eventVideo);
			eventDetails.setIsActive(1);
			
			Login login = new Login();
			login.setLoginId(userInfo.getLoginId());
			eventDetails.setLogin(login);
			
			eventDetails.setModifiedBy("user nate"); 
			eventDetails.setModifiedOn(new Date());
			
			
			EventImage eventImage = new EventImage();
			eventImage.setImageUrl(eventInfo.getImageName());
			eventImage.setEventDetails(eventDetails);
			eventImage.setModifiedBy("Amit Kumar");
			eventImage.setCreatedOn(new Date());
			eventImage.setCreatedBy("Amit Kumar");
			eventImage.setModifiedOn(new Date());
			Set<EventImage> eventImages = new HashSet<EventImage>();
			eventImages.add(eventImage);
			eventDetails.setEventImages(eventImages);
			
			executeSave(eventDetails);
			//executeSave(eventImage);
			
		
		
		System.out.println("inside DAO IMPL "+eventInfo.getEventName());
		
	}

	private Login getUserInfo(String emailId) {
		List<?> lst = executeNamedQuery("getLoginDetailsbyEmailId", "emailId", emailId);
		Login loginDB = null;
		if(null != lst && !lst.isEmpty()){
			loginDB = (Login)lst.get(0);
		}
		return loginDB;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventInfo> searchEvent(SearchEvent searchEvent) {
		
		//String hql =" from PropertyPost property,MhdPropertySubscription  mhdprosubscription "+where;
		//List<PropertyPost> postPropertyList = session.createQuery(hql).setProperties(search).list();
		Session session = null;
		List<EventDetails> eventDetailList;
		List<EventInfo> listOfEvents;
		try {
			session = hibernateTemplate.getSessionFactory().openSession();
			//String hql =" select eventName from EventDetails eventDetails where eventDetails.eventId=40";
			String hql ="from EventDetails eventDetails";
			eventDetailList = session.createQuery(hql).list();
			listOfEvents=convertDataToBean(eventDetailList);
		} finally{
				session.close();
			}
		
		return listOfEvents;
	}

	private List<EventInfo> convertDataToBean(List<EventDetails> eventDetailList) {
		List<EventInfo> listOfEvent =new ArrayList<EventInfo>();
		for (int i = 0; i < eventDetailList.size(); i++) {
			EventDetails eventDetail = eventDetailList.get(i);
			EventInfo eventInfo= new EventInfo();
			eventInfo.setEventId(eventDetail.getEventId().toString());
			eventInfo.setEventName(eventDetail.getEventName());
			for(EventImage imgInfo: eventDetail.getEventImages()) {
			    eventInfo.setImageName( imgInfo.getImageUrl());
			    break;
			}
			listOfEvent.add(eventInfo);
		}
		return listOfEvent;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getMasterData() {
		System.out.println("inside dao");
		
		Map masterData = new HashMap<String, List>();
		Session session = null;
		try {
			session = hibernateTemplate.getSessionFactory().openSession();
			String hqlforcity ="from City city";
			List<City> cityList = session.createQuery(hqlforcity).list();
			
			String hqlforcountry ="from Country country";
			List<Country> countryList = session.createQuery(hqlforcountry).list();
			
			String hqlforindustry ="from IndustryType industry";
			List<IndustryType> industryList = session.createQuery(hqlforindustry).list();
			
			String hqlforeventType ="from EventType eventType";
			List<EventType> eventTypeList = session.createQuery(hqlforeventType).list();
			
			masterData.put("industryList", industryList);
			masterData.put("countryList", countryList);
			masterData.put("cityList", cityList);
			masterData.put("eventTypeList", eventTypeList);
		} catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		finally {
			session.close();
		}
	
		
		return masterData;
	}

}
