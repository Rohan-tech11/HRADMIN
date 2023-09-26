package com.virtusa.hrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.User;

public interface HrRepo extends JpaRepository<Hrs, Integer> {
	
	
	@Query("SELECT u FROM Hrs u WHERE u.id = :id")
	Hrs findHrById(@Param("id") Integer id );
	 
	
	Hrs findByusername(String username);
	
	
	Hrs findByEmail(String emailId);

}
