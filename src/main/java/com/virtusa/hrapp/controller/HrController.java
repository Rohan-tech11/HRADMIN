package com.virtusa.hrapp.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import com.virtusa.hrapp.models.AppHrPrinicipal;
import com.virtusa.hrapp.models.Hrs;
import com.virtusa.hrapp.models.JobApplicants;
import com.virtusa.hrapp.models.Jobs;
import com.virtusa.hrapp.models.Role;
import com.virtusa.hrapp.models.User;
import com.virtusa.hrapp.repositories.HrRepo;
import com.virtusa.hrapp.repositories.JobApplicantsRepo;
import com.virtusa.hrapp.repositories.JobsRepo;
import com.virtusa.hrapp.repositories.RoleRepository;
import com.virtusa.hrapp.service.HrService;
import com.virtusa.hrapp.service.UserService;

@Controller
public class HrController {

	@Autowired
	private HrService hrService;
	
	@Autowired
	private HrRepo hrRepo;

	@Autowired
	private JobsRepo jobsRepo;
	
	
	@Autowired
	private JobApplicantsRepo applicantsRepo;
	
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	
	@PostMapping(value = "hr/addNew")
	public RedirectView addNew(Hrs hr, RedirectAttributes redir) {
		System.out.println("hi");

		Hrs existingUserName = hrService.findByUserName(hr.getUsername());
		User existingUserNameinUserDb=userService.findByUserName(hr.getUsername());
		System.out.println("hii 1");
		if (existingUserName != null) {
			System.out.println("hii..22");
			RedirectView redirectView = new RedirectView("/registerAsHr", true);
			redir.addFlashAttribute("usermessage", " HR User Name is already taken!  please use another user name`");
			return redirectView;

		}
		if (existingUserNameinUserDb != null) {
			System.out.println("hii..22");
			RedirectView redirectView = new RedirectView("/registerAsHr", true);
			redir.addFlashAttribute("usermessage", " HR User Name is already taken!  please use another user name`");
			return redirectView;

		}

	 Hrs existingEmailId = hrService.findByUserEmailId(hr.getEmail());
		System.out.println("hii..332");


		if (existingEmailId != null) {
			System.out.println("hii..442");

			RedirectView redirectView = new RedirectView("/registerAsHr", true);
			redir.addFlashAttribute("message", "Hr Email is already registered");
			return redirectView;

		}
		
		
		
		Set<Role> role = new HashSet<>();
		role.add(roleRepo.findRoleById(3));
		
		
        hr.setRoles(role);
		
		
		hrService.saveHr(hr);
		RedirectView redirectView = new RedirectView("/login", true);
		redir.addFlashAttribute("message", "You  are successfully registered! You can now login");

		
		return redirectView;
	}
	
	
    @GetMapping("/postajob")
    public String getJobs(@AuthenticationPrincipal AppHrPrinicipal hrDetails ,Model model)
    {
		Hrs hr = hrRepo.findByusername(hrDetails.getUsername());
    	List<Jobs> jobsList=hrService.getAllJobsByHrId(hr.getHrId());
    	System.out.println(jobsList);
    	model.addAttribute("jobsList", jobsList);
    	return "postajob" ;     
    }
	
	

	@GetMapping("/getdeleteajob")
	public String deleteJob(@AuthenticationPrincipal AppHrPrinicipal hrDetails,Model model) {
		Hrs hr = hrRepo.findByusername(hrDetails.getUsername());
    	List<Jobs> jobsList=hrService.getAllJobsByHrId(hr.getHrId());
    	model.addAttribute("jobsList", jobsList);
		return "deletejob";
	}
	
	//@{/deletejobbyid/(id=${job.jobId})}
	

	@RequestMapping(value="/deletejobbyid", method= {RequestMethod.DELETE,RequestMethod.GET})
	public String deleteJobById(int id)
	{
		hrService.deleteJobById(id);
		return "redirect:/getdeleteajob";
	}
    
    
	//save new job in db
	@PostMapping("/addJob")
	public String addJob(@AuthenticationPrincipal AppHrPrinicipal hrDetails ,Jobs job ,Model model) {
	
		Hrs hr = hrRepo.findByusername(hrDetails.getUsername());;
		job.setHr(hr);
		
		
		hrService.postJob(job);
		List<Jobs> jobsList=hrService.getJobs();
    	System.out.println(jobsList);
    	model.addAttribute("jobsList", jobsList);

		return "redirect:/postajob";
	}
	
	@GetMapping("/viewapplicants")
	public String viewApplicants(@AuthenticationPrincipal AppHrPrinicipal hrDetails ,Model model) throws JsonProcessingException {
		Hrs hr = hrRepo.findByusername(hrDetails.getUsername());
        List<JobApplicants> jobApplications = applicantsRepo.findAllJobApplicationsByHrId(hr.getHrId());
    	model.addAttribute("jobApplications", jobApplications);


		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(jobApplications);
		System.out.println(json);
		return  "applicants";
	}

}
