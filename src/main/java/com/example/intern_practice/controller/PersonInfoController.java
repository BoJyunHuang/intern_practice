package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.service.ifs.PersonInfoService;

@Controller
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;
	
	@Autowired
	private ExtendedProfileService extendedProfileService;
	
	public String showPersonInfoPage(Model model) {
		return "person_info";
	}
	
	@GetMapping("/person_info")
    public String hello() {
        return "person_info"; // 要導入的html
    } 
}
