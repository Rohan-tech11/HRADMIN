package com.virtusa.hrapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResumeController {
	
	@GetMapping("/resumebuilder")
	public String getResumeBuilder()
	{
		return "resumebuilder";
	}

}
