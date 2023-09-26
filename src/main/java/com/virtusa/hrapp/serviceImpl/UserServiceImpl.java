package com.virtusa.hrapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.models.User;
import com.virtusa.hrapp.repositories.UserRepo;
import com.virtusa.hrapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public void saveUser(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);

	}

	@Override
	public User findByUserName(String userName) {
		User user = userRepo.findByusername(userName);
		return user;
	}

	@Override
	public User findByUserEmailId(String emailId) {

		User user = userRepo.findByemailid(emailId);
		return user;
	}

}
