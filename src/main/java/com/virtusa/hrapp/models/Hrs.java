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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Hr")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hrs {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hr_id")
	private Integer hrId;
	
	@Column(name="com_name")	
	private String comName;
	
	@Column(name="hr_name")
	private String hrName;
	
	
	 @Column(name="user_name")
	 private String username;
	 
	 
	@Column(name="com_phone")
	private String phone;
	
	@Column(name="com_address")
	private String address;
	
	@Column(name="hr_email")
	private String email;
	
	@Column(name="com_desc")
	private String companyDesc;
	
	
	 @Column(name="hr_password")
	private String password;
	
	 
	 @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
     @JoinTable(
             name = "hr_role",
             joinColumns = {@JoinColumn(name = "hr_id")},
             inverseJoinColumns = {@JoinColumn(name = "role_id")}
     )
Set<Role> roles = new HashSet<>();
	

	
	
}
