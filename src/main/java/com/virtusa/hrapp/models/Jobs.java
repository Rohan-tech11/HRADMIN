package com.virtusa.hrapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Jobs")
@NoArgsConstructor
@AllArgsConstructor
public class Jobs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private Integer jobId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "job_position")
	private String jobPosition;

	@Column(name = "job_vacancy")
	private Integer jobVacancy;

	
	  @ManyToOne(fetch = FetchType.EAGER, optional = false)
	  
	  @JoinColumn(name = "hr_id", nullable = false)
	  
	  @OnDelete(action = OnDeleteAction.CASCADE) 
	  private Hrs hr;
	

	@Column(name = "job_post_date")
	private String jobPostDate;

	@Column(name = "job_type")
	private String jobType;

	@Column(name = "job_description")
	private String jobDesc;

	

	
}
