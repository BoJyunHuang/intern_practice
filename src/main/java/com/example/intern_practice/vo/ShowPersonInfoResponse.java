package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.entity.PersonInfo;

public class ShowPersonInfoResponse {

	private String message;
	private List<PersonInfo> personInfoList;
	
	public ShowPersonInfoResponse() {
		super();
	}
	
	public ShowPersonInfoResponse(String message) {
		super();
		this.message = message;
	}
	
	public ShowPersonInfoResponse(String message, List<PersonInfo> personInfoList) {
		super();
		this.message = message;
		this.personInfoList = personInfoList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PersonInfo> getPersonInfoList() {
		return personInfoList;
	}

	public void setPersonInfoList(List<PersonInfo> personInfoList) {
		this.personInfoList = personInfoList;
	}
	
}
