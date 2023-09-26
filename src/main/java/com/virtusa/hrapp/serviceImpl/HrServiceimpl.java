package com.virtusa.hrapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.Jobs;
import com.virtusa.hrapp.repositories.HrRepo;
import com.virtusa.hrapp.repositories.JobsRepo;
import com.virtusa.hrapp.service.HrService;

@Service
public class HrServiceimpl implements HrService {
	
	@Autowired
	private JobsRepo jobsRepo;
	
	
	@Autowired
	private HrRepo hrRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void postJob(Jobs job)
	{
		
		jobsRepo.save(job);
		
	}


	@Override
	public List<Jobs> getJobs() {
		
		return jobsRepo.findAll();	
		}


	@Override
	public void deleteJobById(int id) {
		 jobsRepo.deleteById(id);
		
	}


	@Override
	public Hrs findByUserName(String hrUserName) {
		Hrs hr = hrRepo.findByusername(hrUserName);
		return hr;

	}


	@Override
	public Hrs findByUserEmailId(String email) {
		Hrs hr = hrRepo.findByusername(email);
		return hr;
	}


	@Override
	public void saveHr(Hrs hr) {
		hr.setPassword(encoder.encode(hr.getPassword()));

		hrRepo.save(hr);		
	}


	@Override
	public List<Jobs> getAllJobsByHrId(Integer hrId) {
		// TODO Auto-generated method stub
		
		return jobsRepo.findJobsByHrId(hrId);	

	}


	
	
	
}
