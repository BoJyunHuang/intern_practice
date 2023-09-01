package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.PersonInfo;
import com.example.intern_practice.repository.ExtendedProfileDao;
import com.example.intern_practice.repository.PersonInfoDao;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.AddPersonInfoResponse;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;

@Controller
public class PersonInfoController {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Autowired
	private ExtendedProfileDao extendedProfileDao;

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private ExtendedProfileService extendedProfileService;

	@GetMapping("/person_list")
	public String init(Model model) {
		model.addAttribute("search", new FindPersonInfoRequest());
		model.addAttribute("person_list", personInfoService.showPersonInfo().getPersonInfoList());
		return "search";
	}

	@PostMapping("/find_person")
	public String findPerson(@ModelAttribute("search") FindPersonInfoRequest request, Model model) {
		model.addAttribute("person_list", personInfoService.findPersonInfo(request).getPersonInfoList());
		return "search";
	}

	@GetMapping("/show_person_info")
	public String showPersonInfo(@RequestParam Integer personId, Model model) {
		model.addAttribute("personInfo", personInfoDao.findById(personId).get());
		model.addAttribute("extendedProfile", extendedProfileDao.findById(personId).get());
		return "info_page";
	}

	@GetMapping("/add_person_info")
	public String showaddPage(Model model) {
		model.addAttribute("personInfo", new PersonInfo());
		return "person_info";
	}

	@PostMapping("/add_person_info")
	public String addPersonInfo(@ModelAttribute("personInfo") AddPersonInfoRequest request, Model model) {
		AddPersonInfoResponse res = personInfoService.addPersonInfo(request);
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
			ExtendedProfileRequest extendedProfile = new ExtendedProfileRequest();
			extendedProfile.setEmployeeNumber(res.getPersonInfo().getEmployeeNumber());
			model.addAttribute("extendedProfile", extendedProfile);
			return "extended_profile";			
		}
		model.addAttribute("errorMessage", res.getMessage());
		return "person_info";
	}

	@PostMapping("/add_extended_profile")
	public String addExtendedProfile(@ModelAttribute("extendedProfile") ExtendedProfileRequest request, Model model) {
		Response res = extendedProfileService.addExtendedProfile(request);
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
			model.addAttribute("errorMessage", res.getMessage());
			return "redirect:/person_list";
		}
		model.addAttribute("errorMessage", res.getMessage());
		return "extended_profile";
	}

	// TODO
	@PostMapping("/revise_person_info")
	public String revisePersonInfo(@ModelAttribute RevisePersonInfoRequest pRequest,
			@ModelAttribute ExtendedProfileRequest eRequest, Model model) {
//		Response res1 = personInfoService.revisePersonInfo(pRequest);
//		Response res2 = extendedProfileService.reviseExtendedProfile(eRequest);
//		model.addAttribute("errorMessage", res.getMessage());
		return "redirect:/show_person_info";
	}

	@PostMapping("/delete_person_info")
	public String deletePersonInfo(@RequestParam Integer personId, Model model) {
		DeletePersonInfoRequest pRequest = new DeletePersonInfoRequest();
		pRequest.setEmployeeNumber(personId);
		Response pres = personInfoService.deletePersonInfo(pRequest);
		ExtendedProfileRequest eRequest = new ExtendedProfileRequest();
		eRequest.setEmployeeNumber(personId);
		Response eres = extendedProfileService.deleteExtendedProfile(eRequest);
		Object error = pres.getMessage().equals(RtnCode.SUCCESS.getMessage())
				&& eres.getMessage().equals(RtnCode.SUCCESS.getMessage()) ? "success" : "failed";
		model.addAttribute("errorMessage", error);
		return "redirect:/person_list";
	}

}
