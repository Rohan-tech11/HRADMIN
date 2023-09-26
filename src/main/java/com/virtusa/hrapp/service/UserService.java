package com.virtusa.hrapp.service;

import com.virtusa.hrapp.models.User;

public interface UserService {
	
	void saveUser(User user);
	
	User findByUserName(String userName);
	
	User findByUserEmailId(String emailId);

}
