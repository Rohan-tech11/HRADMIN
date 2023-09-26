package com.virtusa.hrapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.hrapp.models.JobApplicants;
import com.virtusa.hrapp.models.Jobs;

@Repository
public interface JobsRepo extends JpaRepository<Jobs, Integer> {
	
	
	/*
	 * @Query("SELECT j FROM Jobs j WHERE j.id = :id") Jobs
	 * findJobsByHrId(@Param("id") Integer id );
	 */
	
	@Query("SELECT j FROM Jobs j WHERE j.id = :id")
    Jobs findJobById(@Param("id") Integer id );
	
	
	
	@Query("SELECT u FROM Jobs u WHERE u.hr.id = :id")
	List<Jobs> findJobsByHrId(@Param("id") Integer id );


}
