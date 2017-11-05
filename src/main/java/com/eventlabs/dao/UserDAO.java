package com.eventlabs.dao;

import com.eventlabs.beans.UserInfo;
import com.eventlabs.domain.Login;

public interface UserDAO {
	public Login validateUser(String emailId);
	public String userRegistration(UserInfo userInfo);
	public void checkUserId(String userId);
	public void forgotPassword(String userId);

}
