package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindExtendedProfileResponse;
import com.example.intern_practice.vo.Response;

public interface ExtendedProfileService {

	/**
	 * 拡張プロフィール情報を検索します。
	 *
	 * @param request 拡張プロフィール情報の検索条件を含むリクエスト
	 * @return 検索結果を含むレスポンス
	 */
	public FindExtendedProfileResponse findExtendedProfile(ExtendedProfileRequest request);
	
	/**
	 * 拡張プロフィール情報を追加します。
	 *
	 * @param request 追加する拡張プロフィール情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public Response addExtendedProfile(ExtendedProfileRequest request);
	
	/**
	 * 拡張プロフィール情報を修正します。
	 *
	 * @param request 修正する拡張プロフィール情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public Response reviseExtendedProfile(ExtendedProfileRequest request);
	
	/**
	 * 拡張プロフィール情報を削除します。
	 *
	 * @param request 削除する拡張プロフィール情報を含むリクエスト
	 * @return 処理結果を示すレスポンス
	 */
	public Response deleteExtendedProfile(ExtendedProfileRequest request);
}
