package com.virtusa.hrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtusa.hrapp.models.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	User findByusername(String username);
	
	User findByemailid(String emailId);

}

