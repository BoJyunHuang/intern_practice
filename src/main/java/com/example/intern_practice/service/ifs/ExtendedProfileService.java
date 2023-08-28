package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindExtendedProfileResponse;
import com.example.intern_practice.vo.Response;

public interface ExtendedProfileService {

	public FindExtendedProfileResponse findExtendedProfile(ExtendedProfileRequest request);
	
	public Response addExtendedProfile(ExtendedProfileRequest request);
	
	public Response reviseExtendedProfile(ExtendedProfileRequest request);
	
	public Response deleteExtendedProfile(ExtendedProfileRequest request);
}
