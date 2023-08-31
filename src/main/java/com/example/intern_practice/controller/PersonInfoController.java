package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.intern_practice.entity.PersonInfo;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.AddPersonInfoResponse;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindExtendedProfileResponse;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;
import com.example.intern_practice.vo.ShowPersonInfoResponse;

@Controller
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private ExtendedProfileService extendedProfileService;

	@GetMapping("/person_list")
	public String init() {
		return "person_list";
	}

	@GetMapping("/show_all")
	public String showAll(Model model) {
		ShowPersonInfoResponse response = personInfoService.showPersonInfo();
		model.addAttribute("person_list", response.getPersonInfoList());
		return "person_list";
	}

	@PostMapping("/find_some")
	public String findSome(@ModelAttribute("request") FindPersonInfoRequest request, Model model) {
		ShowPersonInfoResponse response = personInfoService.findPersonInfo(request);
		model.addAttribute("person_list", response.getPersonInfoList());
		return "person_list";
	}

	@GetMapping("/show_person_info")
	public String showPersonInfo(@ModelAttribute PersonInfo personInfo, Model model) {
		model.addAttribute("person_info", personInfo);
		return "person_info";
	}
	
	@GetMapping("/add_person_info")
	public String showaddPage(@ModelAttribute AddPersonInfoRequest request, Model model) {
		model.addAttribute("person_info", request);
		return "person_info";
	}
	
	// TODO
	@PostMapping("/add_person_info")
	public String addPersonInfo(@ModelAttribute AddPersonInfoRequest request, Model model) {
		AddPersonInfoResponse res = personInfoService.addPersonInfo(request);
		model.addAttribute("message", res.getMessage());
		return "person_info";
	}
	
	// TODO
	@PostMapping("/revise_person_info")
	public String revisePersonInfo(@ModelAttribute RevisePersonInfoRequest request, Model model) {
		Response res = personInfoService.revisePersonInfo(request);
		model.addAttribute("errorMessage", res.getMessage());
		return "redirect:/show_person_info";
	}
	
	@PostMapping("/delete_person_info")
	public String deletePersonInfo(@ModelAttribute DeletePersonInfoRequest request, Model model) {
		model.addAttribute("errorMessage", personInfoService.deletePersonInfo(request).getMessage());
		return "redirect:/person_list";
	}
	
	@GetMapping("/find_extended_profile")
	public String findExtendedProfile(@PathVariable Integer employeeNumber, Model model) {
		ExtendedProfileRequest request = new ExtendedProfileRequest();
		request.setEmployeeNumber(employeeNumber);
		FindExtendedProfileResponse res = extendedProfileService.findExtendedProfile(request);
		model.addAttribute("extended_profile", res);
		return "extended_profile";
	}
	
	@GetMapping("/add_extended_profile")
	public String addExtendedProfile(@ModelAttribute ExtendedProfileRequest request, Model model) {
		 Object res = extendedProfileService.addExtendedProfile(request);
		model.addAttribute("extended_profile", res);
		return "extended_profile";
	}
	
	@GetMapping("/revise_extended_profile")
	public String reviseExtendedProfile(@ModelAttribute ExtendedProfileRequest request, Model model) {
		 Object res = extendedProfileService.reviseExtendedProfile(request);
		model.addAttribute("extended_profile", res);
		return "extended_profile";
	}
	
	@GetMapping("/delete_extended_profile")
	public String deleteExtendedProfile(@ModelAttribute ExtendedProfileRequest request, Model model) {
		 Object res = extendedProfileService.deleteExtendedProfile(request);
		model.addAttribute("extended_profile", res);
		return "extended_profile";
	}

}
