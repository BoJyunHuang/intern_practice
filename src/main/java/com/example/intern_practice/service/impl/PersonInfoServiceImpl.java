package com.example.intern_practice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.PersonInfo;
import com.example.intern_practice.repository.PersonInfoDao;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.AddPersonInfoResponse;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;
import com.example.intern_practice.vo.ShowPersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	/**
	 * 全ての個人情報を表示します。
	 *
	 * @return 個人情報の一覧を含むレスポンス
	 */
	@Override
	public ShowPersonInfoResponse showPersonInfo() {
		return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(), personInfoDao.findAll());
	}

	/**
	 * 個人情報を追加します。
	 *
	 * @param request 個人情報の追加要求
	 * @return 追加された個人情報と成功メッセージを含むレスポンス
	 */
	@Override
	public AddPersonInfoResponse addPersonInfo(AddPersonInfoRequest request) {
		// 要求がnullであるか、必要な情報が不足している場合はエラーレスポンスを返す
		if (request == null 
				|| !StringUtils.hasText(request.getFullName())
				|| !StringUtils.hasText(request.getNationality()) 
				|| !StringUtils.hasText(request.getGender())
				|| request.getBirthDate() == null 
				|| request.getJoinDate() == null) {
			return new AddPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}

		// 成功レスポンスを作成し、新しい個人情報をデータベースに保存して返す
		return new AddPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
		    personInfoDao.save(new PersonInfo(
		        request.getFullName(),          // 名前
		        request.getKanaName(),          // 名前（カタカナ）
		        request.getRomanizedName(),     // 名前（ローマ字）
		        request.getNationality(),       // 国籍
		        request.getGender(),            // 性別
		        request.getBirthDate(),         // 生年月日
		        request.getJoinDate(),          // 入社日
		        null                            // 退職日（null で初期化）
		    )));
	}

	/**
	 * 個人情報を検索します。
	 *
	 * @param request 個人情報の検索要求
	 * @return 検索結果と成功メッセージを含むレスポンス
	 */
	@Override
	public ShowPersonInfoResponse findPersonInfo(FindPersonInfoRequest request) {
		// 要求がnullの場合はエラーレスポンスを返す
		if (request == null) {
			return new ShowPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 検索条件に基づいて個人情報を検索し、結果をレスポンスとして返す
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
		} else if (request.getStart() != null && request.getEnd() != null && request.isDeparture() == false) {
			return new ShowPersonInfoResponse(RtnCode.SUCCESS.getMessage(),
					personInfoDao.searchPersonInfoByJoinDate(request.getStart(), request.getEnd()));
		} else {
			return new ShowPersonInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}
	}

	/**
	 * 個人情報を修正します。
	 *
	 * @param request 個人情報の修正要求
	 * @return 修正結果に応じたレスポンス
	 */
	@Override
	public Response revisePersonInfo(RevisePersonInfoRequest request) {
		// 要求がnullであるか、必要な情報が不足している場合はエラーレスポンスを返す
		if (request == null 
				|| request.getEmployeeNumber() == null 
				|| !StringUtils.hasText(request.getFullName())
				|| !StringUtils.hasText(request.getNationality()) 
				|| !StringUtils.hasText(request.getGender())
				|| request.getBirthDate() == null 
				|| request.getJoinDate() == null) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}
		// 個人情報をデータベースで修正し、結果に応じて適切なレスポンスを返す
		return personInfoDao.updatePersonInfo(
				request.getEmployeeNumber(), 
				request.getFullName(), 
				request.getKanaName(),
				request.getRomanizedName(), 
				request.getNationality(), 
				request.getGender(), 
				request.getBirthDate(),
				request.getJoinDate(), 
				request.getDpartureDate()) == 0 
						? new Response(RtnCode.INCORRECT.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

	/**
	 * 個人情報を削除します（削除フラグを立てます）。
	 *
	 * @param request 削除要求
	 * @return 削除結果に応じたレスポンス
	 */
	@Override
	public Response deletePersonInfo(DeletePersonInfoRequest request) {
		// 要求がnullであるか、マイナンバーが不足している場合はエラーレスポンスを返す
		return request == null || request.getEmployeeNumber() == null 
				? new Response(RtnCode.CANNOT_EMPTY.getMessage())
				: personInfoDao.deletePersonInfo(request.getEmployeeNumber()) == 0
						? new Response(RtnCode.NOT_FOUND.getMessage())
						: new Response(RtnCode.SUCCESS.getMessage());
	}

}
