package com.example.intern_practice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.repository.PersonInfoDao;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;
import com.example.intern_practice.vo.ShowPersonInfoResponse;

public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public ShowPersonInfoResponse showPersonInfo() {
		return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(), personInfoDao.findAll());
	}

	@Override
	public Response addPersonInfo(AddPersonInfoRequest request) {
		if (request == null || StringUtils.hasText(request.getFullName()) || StringUtils.hasText(request.getKanaName())
				|| StringUtils.hasText(request.getRomanizedName()) || StringUtils.hasText(request.getNationality())
				|| StringUtils.hasText(request.getGender()) || request.getBirthDate() == null
				|| request.getJoinDate() == null) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}
		return personInfoDao.insertPersonInfo(request.getFullName(), request.getKanaName(), request.getRomanizedName(),
				request.getNationality(), request.getGender(), request.getBirthDate(), request.getJoinDate()) == 0
						? new Response(RtnCode.INCORRECT.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public ShowPersonInfoResponse findPersonInfo(FindPersonInfoRequest request) {
		if (request == null) {
			return new ShowPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		if (StringUtils.hasText(request.getName())) {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.searchPersonInfoByName(request.getName()));
		} else if (StringUtils.hasText(request.getNationality())) {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.findByNationality(request.getNationality()));
		} else if (StringUtils.hasText(request.getGender())) {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.findByGender(request.getGender()));
		} else if (request.getStart() != null && request.getEnd() != null && request.isDeparture() == true) {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.searchPersonInfoByDepartureDate(request.getStart(), request.getEnd()));
		} else {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.searchPersonInfoByJoinDate(request.getStart(), request.getEnd()));
		}
	}

	@Override
	public Response revisePersonInfo(RevisePersonInfoRequest request) {
		if (request == null || request.getEmployeeNumber() < 1 || StringUtils.hasText(request.getFullName())
				|| StringUtils.hasText(request.getKanaName()) || StringUtils.hasText(request.getRomanizedName())
				|| StringUtils.hasText(request.getNationality()) || StringUtils.hasText(request.getGender())
				|| request.getBirthDate() == null || request.getJoinDate() == null
				|| request.getDpartureDate() == null) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}
		return personInfoDao.updatePersonInfo(request.getEmployeeNumber(), request.getFullName(), request.getKanaName(),
				request.getRomanizedName(), request.getNationality(), request.getGender(), request.getBirthDate(),
				request.getJoinDate(), request.getDpartureDate()) == 0 ? new Response(RtnCode.INCORRECT.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public Response deletePersonInfo(DeletePersonInfoRequest request) {
		return personInfoDao.deletePersonInfo(
				request == null || request.getEmployeeNumber() < 1 ? 0 : request.getEmployeeNumber()) == 0
						? new Response(RtnCode.CANNOT_EMPTY.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

}
