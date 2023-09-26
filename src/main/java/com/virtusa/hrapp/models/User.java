package com.virtusa.hrapp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
		
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="user_id")
	private int id;
    
    @Column(name="first_name")
    private String firstname;
    
    @Column(name="last_name")
    private String lastname;
    
    @Column(name="user_name")
    private String username;
    
    @Column(name="phone_number")
    private String phonenumber;
    
    @Column(name="email_id")
    private String emailid;
    
    @Column(name="user_password")
	private String password;
    
	
	@Column(name="user_skills")
	private String skills;
	
	@Column(name="user_exp")
	private String experience;
	
	@Column(name="user_last_job")
	private String lastJob;
    
	/*
	 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinTable( name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
	 * inverseJoinColumns = @JoinColumn(name = "role_id") ) private Set<Role> roles
	 * = new HashSet<>();
	 */
	
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "job_id") private List<Jobs> jobs;
	 */
	
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
Set<Role> roles = new HashSet<>();
	
}

