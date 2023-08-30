package com.example.intern_practice.vo;

import com.example.intern_practice.entity.PersonInfo;

public class AddPersonInfoResponse {

	private String message;
	private PersonInfo personInfo;
	
	public AddPersonInfoResponse() {
		super();
	}
	public AddPersonInfoResponse(String message) {
		super();
		this.message = message;
	}
	public AddPersonInfoResponse(String message, PersonInfo personInfo) {
		super();
		this.message = message;
		this.personInfo = personInfo;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
