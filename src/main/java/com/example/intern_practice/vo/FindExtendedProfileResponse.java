package com.example.intern_practice.vo;

import com.example.intern_practice.entity.ExtendedProfile;

public class FindExtendedProfileResponse {

	private String message;
	private ExtendedProfile extendedProfile;
	
	public FindExtendedProfileResponse() {
		super();
	}
	public FindExtendedProfileResponse(String message) {
		super();
		this.message = message;
	}
	public FindExtendedProfileResponse(String message, ExtendedProfile extendedProfile) {
		super();
		this.message = message;
		this.extendedProfile = extendedProfile;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExtendedProfile getExtendedProfile() {
		return extendedProfile;
	}
	public void setExtendedProfile(ExtendedProfile extendedProfile) {
		this.extendedProfile = extendedProfile;
	}
	
}
