package com.example.intern_practice.service.impl;

import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.repository.ExtendedProfileDao;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindExtendedProfileResponse;
import com.example.intern_practice.vo.Response;

public class ExtendedProfileServiceImpl implements ExtendedProfileService {

	private ExtendedProfileDao extendedProfileDao;

	@Override
	public FindExtendedProfileResponse findExtendedProfile(ExtendedProfileRequest request) {
		return request == null ? new FindExtendedProfileResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: new FindExtendedProfileResponse(RtnCode.SUCCESS.getMessage(), extendedProfileDao
						.findById(request.getEmployeeNumber() < 1 ? 0 : request.getEmployeeNumber()).get());
	}

	@Override
	public Response addExtendedProfile(ExtendedProfileRequest request) {
		if (request == null || request.getEmployeeNumber() < 1 || StringUtils.hasText(request.getTelephone())
				|| StringUtils.hasText(request.getMobilePhone()) || StringUtils.hasText(request.getCompanyEmail())
				|| StringUtils.hasText(request.getAlternateEmail()) || StringUtils.hasText(request.getPostalCode())
				|| StringUtils.hasText(request.getAddress()) || StringUtils.hasText(request.getPassportNumber())
				|| request.getPassportExpiryDate() == null || StringUtils.hasText(request.getResidenceCardNumber())
				|| request.getResidenceCardStartDate() == null || request.getResidenceCardExpirationDate() == null
				|| StringUtils.hasText(request.getResidenceCardStatus())
				|| StringUtils.hasText(request.getEmploymentInsuranceNumber())
				|| StringUtils.hasText(request.getPensionNumber()) || StringUtils.hasText(request.getBankName())
				|| StringUtils.hasText(request.getStoreName()) || StringUtils.hasText(request.getAccountNumber())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		return extendedProfileDao.insertExtendedProfile(request.getEmployeeNumber(), request.getTelephone(),
				request.getMobilePhone(), request.getCompanyEmail(), request.getAlternateEmail(),
				request.getPostalCode(), request.getAddress(), request.getPassportNumber(),
				request.getPassportExpiryDate(), request.getResidenceCardNumber(), request.getResidenceCardStartDate(),
				request.getResidenceCardExpirationDate(), request.getResidenceCardStatus(),
				request.getEmploymentInsuranceNumber(), request.getPensionNumber(), request.getBankName(),
				request.getStoreName(), request.getAccountNumber()) == 0 ? new Response(RtnCode.INCORRECT.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public Response reviseExtendedProfile(ExtendedProfileRequest request) {
		if (request == null || request.getEmployeeNumber() < 1 || StringUtils.hasText(request.getTelephone())
				|| StringUtils.hasText(request.getMobilePhone()) || StringUtils.hasText(request.getCompanyEmail())
				|| StringUtils.hasText(request.getAlternateEmail()) || StringUtils.hasText(request.getPostalCode())
				|| StringUtils.hasText(request.getAddress()) || StringUtils.hasText(request.getPassportNumber())
				|| request.getPassportExpiryDate() == null || StringUtils.hasText(request.getResidenceCardNumber())
				|| request.getResidenceCardStartDate() == null || request.getResidenceCardExpirationDate() == null
				|| StringUtils.hasText(request.getResidenceCardStatus())
				|| StringUtils.hasText(request.getEmploymentInsuranceNumber())
				|| StringUtils.hasText(request.getPensionNumber()) || StringUtils.hasText(request.getBankName())
				|| StringUtils.hasText(request.getStoreName()) || StringUtils.hasText(request.getAccountNumber())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}
		return extendedProfileDao.updateExtendedProfile(request.getEmployeeNumber(), request.getTelephone(),
				request.getMobilePhone(), request.getCompanyEmail(), request.getAlternateEmail(),
				request.getPostalCode(), request.getAddress(), request.getPassportNumber(),
				request.getPassportExpiryDate(), request.getResidenceCardNumber(), request.getResidenceCardStartDate(),
				request.getResidenceCardExpirationDate(), request.getResidenceCardStatus(),
				request.getEmploymentInsuranceNumber(), request.getPensionNumber(), request.getBankName(),
				request.getStoreName(), request.getAccountNumber()) == 0 ? new Response(RtnCode.INCORRECT.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public Response deleteExtendedProfile(ExtendedProfileRequest request) {
		return extendedProfileDao.deleteExtendedProfile(
				request == null || request.getEmployeeNumber() < 1 ? 0 : request.getEmployeeNumber()) == 0
						? new Response(RtnCode.CANNOT_EMPTY.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

}
