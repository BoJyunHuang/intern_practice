package com.example.intern_practice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.ExtendedProfile;
import com.example.intern_practice.repository.ExtendedProfileDao;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindExtendedProfileResponse;
import com.example.intern_practice.vo.Response;

@Service
public class ExtendedProfileServiceImpl implements ExtendedProfileService {

	@Autowired
	private ExtendedProfileDao extendedProfileDao;

	/**
	 * 指定されたマイナンバーに基づいて、拡張プロファイル情報を検索します。
	 *
	 * @param request 拡張プロファイル情報リクエスト
	 * @return 検索結果を表す {@link FindExtendedProfileResponse} オブジェクト
	 */
	@Override
	public FindExtendedProfileResponse findExtendedProfile(ExtendedProfileRequest request) {
		// マイナンバーに基づいて拡張プロファイル情報を検索します
		Optional<ExtendedProfile> res = extendedProfileDao.findById(
				// リクエストがnullであるか、マイナンバーが指定されていない場合、デフォルトで0として扱います
				request == null || request.getEmployeeNumber() == null ? 0 : request.getEmployeeNumber());
		// 検索結果が存在しない場合は NOT_FOUND レスポンスを、存在する場合は検索結果を含む SUCCESS レスポンスを返します
		return res.isEmpty() 
				? new FindExtendedProfileResponse(RtnCode.NOT_FOUND.getMessage())
				: new FindExtendedProfileResponse(RtnCode.SUCCESS.getMessage(), res.get());
	}

	/**
	 * 拡張プロファイル情報を追加します。
	 *
	 * @param request 拡張プロファイル情報リクエスト
	 * @return 追加操作の結果を表す {@link Response} オブジェクト
	 */
	@Override
	public Response addExtendedProfile(ExtendedProfileRequest request) {
		 // リクエストの必要な情報が妥当かどうかを検証し、不妥当であればエラーレスポンスを返します
		return validNullImport(request) 
				? new Response(RtnCode.CANNOT_EMPTY.getMessage())
				// 拡張プロファイル情報をデータベースに追加します
				: extendedProfileDao.insertExtendedProfile(
						request.getEmployeeNumber(), 
						request.getTelephone(),
						request.getMobilePhone(), 
						request.getCompanyEmail(), 
						request.getAlternateEmail(),
						request.getPostalCode(), 
						request.getAddress(), 
						request.getPassportNumber(),
						request.getPassportExpiryDate(), 
						request.getResidenceCardNumber(),
						request.getResidenceCardStartDate(), 
						request.getResidenceCardExpirationDate(),
						request.getResidenceCardStatus(), 
						request.getEmploymentInsuranceNumber(),
						request.getPensionNumber(), 
						request.getBankName(), 
						request.getStoreName(),
						request.getAccountNumber()) == 0 
								// 操作の結果に応じて適切なレスポンスを返します
								? new Response(RtnCode.INCORRECT.getMessage())
								: new Response(RtnCode.SUCCESS.getMessage());
	}

	/**
	 * 拡張プロファイル情報を修正します。
	 *
	 * @param request 拡張プロファイル情報リクエスト
	 * @return 修正操作の結果を表す {@link Response} オブジェクト
	 */
	@Override
	public Response reviseExtendedProfile(ExtendedProfileRequest request) {
		// リクエストの必要な情報が妥当かどうかを検証し、不妥当であればエラーレスポンスを返します
		return validNullImport(request) 
				? new Response(RtnCode.CANNOT_EMPTY.getMessage())
				// 拡張プロファイル情報をデータベースに修正します
				: extendedProfileDao.updateExtendedProfile(
						request.getEmployeeNumber(), 
						request.getTelephone(),
						request.getMobilePhone(), 
						request.getCompanyEmail(), 
						request.getAlternateEmail(),
						request.getPostalCode(), 
						request.getAddress(), 
						request.getPassportNumber(),
						request.getPassportExpiryDate(), 
						request.getResidenceCardNumber(),
						request.getResidenceCardStartDate(), 
						request.getResidenceCardExpirationDate(),
						request.getResidenceCardStatus(), 
						request.getEmploymentInsuranceNumber(),
						request.getPensionNumber(), 
						request.getBankName(), 
						request.getStoreName(),
						request.getAccountNumber()) == 0 
								// 操作の結果に応じて適切なレスポンスを返します
								? new Response(RtnCode.INCORRECT.getMessage())
								: new Response(RtnCode.SUCCESS.getMessage());
	}

	/**
	 * 拡張プロファイル情報を削除します。
	 *
	 * @param request 拡張プロファイル情報リクエスト
	 * @return 削除操作の結果を表す {@link Response} オブジェクト
	 */
	@Override
	public Response deleteExtendedProfile(ExtendedProfileRequest request) {
		// 拡張プロファイル情報をデータベースから削除します
		return extendedProfileDao.deleteExtendedProfile(
				// リクエストがnullまたはマイナンバーがnullの場合、削除操作は不可能なのでエラーレスポンスを返します
				request == null || request.getEmployeeNumber() == null ? 0 : request.getEmployeeNumber()) == 0
						// 操作の結果に応じて適切なレスポンスを返します
						? new Response(RtnCode.CANNOT_EMPTY.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	/**
	 * 拡張プロファイル情報の必須フィールドがnullまたは空文字列であるかを検証します。
	 *
	 * @param request 拡張プロファイル情報リクエスト
	 * @return 必須フィールドが不足している場合にtrueを返し、それ以外の場合にfalseを返します
	 */
	private boolean validNullImport(ExtendedProfileRequest request) {
		// リクエストがnullの場合や、必要なフィールドがnullまたは空文字列の場合はtrueを返します
		return request == null 
				|| request.getEmployeeNumber() == null 
				|| !StringUtils.hasText(request.getMobilePhone())
				|| !StringUtils.hasText(request.getCompanyEmail()) 
				|| !StringUtils.hasText(request.getAddress())
				|| !StringUtils.hasText(request.getPassportNumber()) 
				|| request.getPassportExpiryDate() == null
				|| !StringUtils.hasText(request.getResidenceCardNumber()) 
				|| request.getResidenceCardStartDate() == null
				|| request.getResidenceCardExpirationDate() == null
				|| !StringUtils.hasText(request.getResidenceCardStatus())
				|| !StringUtils.hasText(request.getEmploymentInsuranceNumber())
				|| !StringUtils.hasText(request.getPensionNumber()) 
				|| !StringUtils.hasText(request.getBankName())
				|| !StringUtils.hasText(request.getStoreName()) 
				|| !StringUtils.hasText(request.getAccountNumber())
						? true
						: false;
	}
}
