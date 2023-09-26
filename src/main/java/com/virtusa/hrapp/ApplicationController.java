package com.virtusa.hrapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

	@GetMapping("/")
	public String getHome() {

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/getErrorPage")
	public String getErrorPage() {
		return "DisplayErrorPage";
	}

	@GetMapping("/deletejob")
	public String deleteJob() {
		return "deletejob";
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/getDashboard")
	public String getDashboard() {
		return "dashboard";
	}

	@GetMapping("/registerAsHr")
	public String getHrRegistration() {
		return "HrRegister";
	}

}
