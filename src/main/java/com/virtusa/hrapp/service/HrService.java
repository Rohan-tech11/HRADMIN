package com.virtusa.hrapp.service;

import java.util.List;

import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.Jobs;

public interface HrService {
	
	
	List<Jobs> getJobs();
	
	void postJob(Jobs job);
	
	
	void deleteJobById(int id);

	Hrs findByUserName(String hrUserName);

	Hrs findByUserEmailId(String email);

	void saveHr(Hrs hr);

	List<Jobs> getAllJobsByHrId(Integer hrId);
	
	

}
