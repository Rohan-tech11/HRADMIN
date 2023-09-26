package com.virtusa.hrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.hrapp.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query("SELECT u FROM Role u WHERE u.id = :id")
	Role findRoleById(@Param("id") Integer id );

}
