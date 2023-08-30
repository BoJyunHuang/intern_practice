package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.AddPersonInfoResponse;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;
import com.example.intern_practice.vo.ShowPersonInfoResponse;

public interface PersonInfoService {

	/**
	 * 個人情報を表示します。
	 *
	 * @return 個人情報を含むレスポンス
	 */
	public ShowPersonInfoResponse showPersonInfo();
	
	/**
	 * 個人情報を追加します。
	 *
	 * @param request 追加する個人情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public AddPersonInfoResponse addPersonInfo(AddPersonInfoRequest request);
	
	/**
	 * 個人情報を検索します。
	 *
	 * @param request 検索条件を含むリクエスト
	 * @return 検索結果を含むレスポンス
	 */
	public ShowPersonInfoResponse findPersonInfo(FindPersonInfoRequest request);
	
	/**
	 * 個人情報を修正します。
	 *
	 * @param request 修正する個人情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public Response revisePersonInfo(RevisePersonInfoRequest request);
	
	/**
	 * 個人情報を削除します。
	 *
	 * @param request 削除する個人情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public Response deletePersonInfo(DeletePersonInfoRequest request);
	
}
