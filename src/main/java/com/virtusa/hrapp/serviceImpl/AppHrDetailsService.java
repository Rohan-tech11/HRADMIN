package com.virtusa.hrapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.models.AppHrPrinicipal;
import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.repositories.HrRepo;

@Service
public class AppHrDetailsService implements UserDetailsService {

	@Autowired
	private HrRepo hrRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Hrs hr= hrRepo.findByusername(username);
		
		if(hr == null)
		{
			throw new UsernameNotFoundException("User Not Found!!!");
		}
		
		return new AppHrPrinicipal(hr); 
		
	}

}
