package com.virtusa.hrapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.virtusa.hrapp.models.AppUserPrinicipal;
import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.JobApplicants;
import com.virtusa.hrapp.models.Jobs;
import com.virtusa.hrapp.models.Role;
import com.virtusa.hrapp.models.User;
import com.virtusa.hrapp.repositories.JobApplicantsRepo;
import com.virtusa.hrapp.repositories.JobsRepo;
import com.virtusa.hrapp.repositories.RoleRepository;
import com.virtusa.hrapp.repositories.UserRepo;
import com.virtusa.hrapp.service.HrService;
import com.virtusa.hrapp.service.JobApplicantsService;
import com.virtusa.hrapp.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private HrService hrService;

	@Autowired
	private JobApplicantsService applicantsService;
	
	@Autowired
	private JobsRepo jobsRepo;

	@Autowired
	private JobApplicantsRepo applicantsRepo;

	@Autowired
	private UserRepo userRepo;
	
	
	
	
	@Autowired
	private RoleRepository  roleRepo;

	@PostMapping(value = "users/addNew")
	public RedirectView addNew(User user, RedirectAttributes redir) {

		User existingUserName = userService.findByUserName(user.getUsername());
		Hrs existingHrUserName = hrService.findByUserName(user.getUsername());

		if (existingUserName != null) {
			RedirectView redirectView = new RedirectView("/register", true);
			redir.addFlashAttribute("usermessage", "User Name is already taken!  please use another user name`");
			return redirectView;

		}
		if (existingHrUserName != null) {
			RedirectView redirectView = new RedirectView("/register", true);
			redir.addFlashAttribute("usermessage", "User Name is already taken!  please use another user name`");
			return redirectView;

		}

		User existingEmailId = userService.findByUserEmailId(user.getEmailid());

		if (existingEmailId != null) {

			RedirectView redirectView = new RedirectView("/register", true);
			redir.addFlashAttribute("message", "Email is already registered");
			return redirectView;

		}

		RedirectView redirectView = new RedirectView("/login", true);
		redir.addFlashAttribute("message", "You  are successfully registered! You can now login");
		
		
		
		Set<Role> role = new HashSet<>();
		role.add(roleRepo.findRoleById(2));
		user.setRoles(role);
		

		userService.saveUser(user);
		System.out.println(user + "useer with roles assigned");
		return redirectView;
	}

	@GetMapping("/applyforajob")
	public String getJobs(Model model) {

		List<Jobs> jobsList = hrService.getJobs();
		model.addAttribute("jobsList", jobsList);
		return "applyjob";
	}

	@RequestMapping(value = "/applyjobbyid", method = { RequestMethod.PUT, RequestMethod.GET })
	public String deleteJobById(@AuthenticationPrincipal AppUserPrinicipal userDetails, int id) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		String currentDate = formatter.format(date);

		Jobs job = jobsRepo.findJobById(id);
		JobApplicants jobApplicant = new JobApplicants();
		jobApplicant.setJob(job);
		jobApplicant.setAppliedDate(currentDate);
		jobApplicant.setHr(job.getHr());

		String userName = userDetails.getUsername();

		User user = userRepo.findByusername(userName);
		jobApplicant.setUser(user);

		applicantsRepo.save(jobApplicant);

		return "redirect:/applyforajob";
	}

	@GetMapping("/viewappliedjob")
	public String getAppliedJobs(@AuthenticationPrincipal AppUserPrinicipal userDetails, Model model)
			throws JsonProcessingException {

		String userName = userDetails.getUsername();

		User user = userRepo.findByusername(userName);

		int id = user.getId();

		List<JobApplicants> applicants = applicantsRepo.findApplicantsById(id);
		System.out.println(applicants);

		model.addAttribute("applicantsList", applicants);

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(applicants);
		System.out.println(json);
		return "viewappliedjobs";
	}
	
	
	
	
	/* method for withdrawing application */
	
	@RequestMapping(value="/withdrawApplicationbyid", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String withdrawApplicationById(int id)
	{
		applicantsService.withdrawApplicationById(id);
		return "redirect:/viewappliedjob";
	}
	
	
	
	
	
	

}
