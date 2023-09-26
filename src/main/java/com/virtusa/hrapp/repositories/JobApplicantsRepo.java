package com.virtusa.hrapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.hrapp.models.JobApplicants;

public interface JobApplicantsRepo extends JpaRepository<JobApplicants, Integer> {
	
	
	@Query("SELECT u FROM JobApplicants u WHERE u.user.id = :id")
	List<JobApplicants> findApplicantsById(@Param("id") Integer id );

	@Query("SELECT u FROM JobApplicants u WHERE u.hr.hrId = :id")
	List<JobApplicants> findAllJobApplicationsByHrId(@Param("id") Integer id);

}
