package com.virtusa.hrapp.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.Role;
import com.virtusa.hrapp.models.User;
import com.virtusa.hrapp.repositories.HrRepo;
import com.virtusa.hrapp.repositories.RoleRepository;
import com.virtusa.hrapp.repositories.UserRepo;

@Service
public class RoleServiceImpl {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private HrRepo hrRepo;

	public void findAll() {
		roleRepo.findAll();
	}

	public void findById(int id) {
		roleRepo.findById(id);
	}

	public void save(Role role) {
		roleRepo.save(role);
	}

	public void assignUserRole(Integer userId, Integer roleId) {
		User user = userRepo.findById(userId).orElse(null);
		Role role = roleRepo.findById(roleId).orElse(null);
		Set<Role> userRoles = user.getRoles();
		userRoles.add(role);
		user.setRoles(userRoles);
		userRepo.save(user);
	}
	
	public void assignHrRole(Integer hrId, Integer roleId) {
		Hrs hr = hrRepo.findById(hrId).orElse(null);
		Role role = roleRepo.findById(roleId).orElse(null);
		Set<Role> userRoles = hr.getRoles();
		userRoles.add(role);
		hr.setRoles(userRoles);
		hrRepo.save(hr);
	}
	
	public Set<Role> getUserRoles(User user){
	    return user.getRoles();
	}
	
	
	public Set<Role> getHrRoles(Hrs hr){
	    return hr.getRoles();
	}

}
