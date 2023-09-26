package com.virtusa.hrapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.models.AppUserPrinicipal;
import com.virtusa.hrapp.models.User;
import com.virtusa.hrapp.repositories.UserRepo;

@Service
public class AppUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userRepo.findByusername(username);
		
		if(user == null)
		{
			throw new UsernameNotFoundException("User Not Found!!!");
		}
		
		return new AppUserPrinicipal(user); 
	}
}
		


