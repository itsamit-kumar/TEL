package com.eventlabs.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.eventlabs.beans.UserInfo;
import com.eventlabs.domain.Login;
import com.eventlabs.domain.UserDetails;

public class UserDAOImpl extends HibernateConnectionDAO implements UserDAO{
		
	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);


	@Override
	public Login validateUser(String emailId) {
		String sql="select * from Login";
		//jdbcTemplate.queryForInt(sql);
		List<?> lst = executeNamedQuery("getLoginDetailsbyEmailId", "emailId", emailId);
		Login loginDB = null;
		if(null != lst && !lst.isEmpty()){
			loginDB = (Login)lst.get(0);
		}
		return loginDB;
		
	}
    
    @Transactional
	@Override
	public String userRegistration(UserInfo userInfo) {
    	String isUserCreated;
    	LOGGER.info("inside user creation method");
    	Login checkUser = validateUser(userInfo.getUserEmailId());
    	if(checkUser==null && userInfo.getUserEmailId()!=null){
    		Login login = new Login();
    		login.setCreatedBy(userInfo.getUserFName());
    		login.setCreatedOn(new Date());
    		login.setModifiedBy(userInfo.getUserFName());
    		login.setModifiedOn(new Date());
    		login.setEmailId(userInfo.getUserEmailId());
    		login.setPassword(userInfo.getUserPassword());
    		login.setLoginSource(userInfo.getLoginSource());
    		login.setIsUserActivate(1);
    		
    		UserDetails userDetails = new UserDetails();
    		userDetails.setFullName(userInfo.getUserFName()+userInfo.getUserLName());
    		userDetails.setImagepath("images Path");
    		//userDetails.setDob(new Date());
    		//userDetails.setGender(userInfo.);
    		userDetails.setLogin(login);
    		userDetails.setMobile(userInfo.getPhoneNumber());
    		userDetails.setCreatedBy(userInfo.getUserFName());
    		userDetails.setCreatedOn(new Date());
    		userDetails.setModifiedBy(userInfo.getUserFName());
    		userDetails.setModifiedOn(new Date());
    		
    		Set<UserDetails> userDetailses = new HashSet<UserDetails>();
    		userDetailses.add(userDetails);
    		login.setUserDetailses(userDetailses);
    		
    	    executeSave(login);
    	    isUserCreated="Y";
    	    
    	}else{
    		isUserCreated="N";
    	}
    	
    	return isUserCreated;
		
	}

	@Override
	public void checkUserId(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forgotPassword(String userId) {
		// TODO Auto-generated method stub
		
	}

}
