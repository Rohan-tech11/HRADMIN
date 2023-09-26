package com.virtusa.hrapp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.repositories.JobApplicantsRepo;
import com.virtusa.hrapp.service.JobApplicantsService;

@Service
public class JobApplicantsServiceImpl implements JobApplicantsService {

	
	@Autowired
	private JobApplicantsRepo applicantsRepo;
	
	@Override
	public void withdrawApplicationById(int id) {
		
		applicantsRepo.deleteById(id);

	}

}
