package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;
import com.example.intern_practice.vo.ShowPersonInfoResponse;

public interface PersonInfoService {

	public ShowPersonInfoResponse showPersonInfo();
	
	public Response addPersonInfo(AddPersonInfoRequest request);
	
	public ShowPersonInfoResponse findPersonInfo(FindPersonInfoRequest request);
	
	public Response revisePersonInfo(RevisePersonInfoRequest request);
	
	public Response deletePersonInfo(DeletePersonInfoRequest request);
	
}
