package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.PersonInfo;
import com.example.intern_practice.repository.ExtendedProfileDao;
import com.example.intern_practice.repository.PersonInfoDao;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.AddPersonInfoResponse;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.ExtendedProfileRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.RevisePersonInfoRequest;

@Controller
public class PersonInfoController {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Autowired
	private ExtendedProfileDao extendedProfileDao;

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private ExtendedProfileService extendedProfileService;

	/**
	 * 検索フォームと個人情報リストを初期化して表示します。
	 *
	 * @param model Springのモデルオブジェクト
	 * @return 検索画面のビュー名
	 */
	@GetMapping("/person_list")
	public String init(Model model) {
		// このメソッドは、検索フォームと個人情報リストを初期化して表示します。
	    // 検索フォームには「FindPersonInfoRequest」の新しいインスタンスを追加します。
	    // 個人情報リストは「personInfoService」から取得し、モデルに追加します。
		model.addAttribute("search", new FindPersonInfoRequest());
		model.addAttribute("person_list", personInfoService.showPersonInfo().getPersonInfoList());
		return "search";
	}

	/**
	 * 指定された検索条件に基づいて個人情報を検索し、検索結果を表示します。
	 *
	 * @param request 検索条件を含むリクエストオブジェクト
	 * @param model Springのモデルオブジェクト
	 * @return 検索画面のビュー名
	 */
	@PostMapping("/find_person")
	public String findPerson(@ModelAttribute("search") FindPersonInfoRequest request, Model model) {
		// このメソッドは、指定された検索条件に基づいて個人情報を検索し、
	    // 検索結果をモデルに追加して検索画面を表示します。
		model.addAttribute("person_list", personInfoService.findPersonInfo(request).getPersonInfoList());
		return "search";
	}

	/**
	 * 指定された個人IDに基づいて個人情報と拡張プロフィール情報を表示します。
	 *
	 * @param personId 個人情報を識別するためのマイナンバー
	 * @param model Springのモデルオブジェクト
	 * @return 個人情報ページのビュー名
	 */
	@GetMapping("/show_person_info")
	public String showPersonInfo(@RequestParam Integer personId, Model model) {
		// このメソッドは、指定されたマイナンバーに基づいて個人情報と拡張プロフィール情報を取得し、
	    // モデルに追加して個人情報ページを表示します。
		model.addAttribute("personInfo", personInfoDao.findById(personId).get());
		model.addAttribute("extendedProfile", extendedProfileDao.findById(personId).get());
		return "info_page";
	}

	/**
	 * 新しい個人情報を追加するためのフォームを表示します。
	 *
	 * @param model Springのモデルオブジェクト
	 * @return 個人情報フォームを表示するための共通メソッド
	 */
	@GetMapping("/add_person_info")
	public String showaddPage(Model model) {
		// このメソッドは、新しい個人情報を追加するためのフォームを表示します。
		return showPersonInfoForm(model, new PersonInfo(), true);
	}

	/**
	 * 指定された個人IDに基づいて個人情報を編集するためのフォームを表示します。
	 *
	 * @param personId 編集対象の個人情報のID
	 * @param model Springのモデルオブジェクト
	 * @return 個人情報フォームを表示するための共通メソッド
	 */
	@GetMapping("/revise_person_info/{personId}")
	public String showrevisePage(@PathVariable Integer personId, Model model) {
		// このメソッドは、指定されたマイナンバーに基づいて個人情報を編集するためのフォームを表示します。
		return showPersonInfoForm(model, personInfoDao.findById(personId).get(), false);
	}

	/**
	 * 個人情報を追加し、その結果に応じて拡張プロフィールフォームまたはエラーページを表示します。
	 *
	 * @param request 個人情報の追加要求
	 * @param model Springのモデルオブジェクト
	 * @return 拡張プロフィールフォームまたはエラーページのビュー名
	 */
	@PostMapping("/add_person_info")
	public String addPersonInfo(@ModelAttribute("personInfo") AddPersonInfoRequest request, Model model) {
		// 個人情報の追加要求を処理し、その結果を受け取ります。
		AddPersonInfoResponse res = personInfoService.addPersonInfo(request);
		// エラーメッセージとマイナンバーをモデルに追加します。
		return showExtendedProfileForm(model, res.getMessage(), res.getPersonInfo().getEmployeeNumber());
	}

	/**
	 * 個人情報を編集し、その結果に応じて拡張プロフィールフォームまたはエラーページを表示します。
	 *
	 * @param request 個人情報の編集要求
	 * @param model Springのモデルオブジェクト
	 * @return 拡張プロフィールフォームまたはエラーページのビュー名
	 */
	@PostMapping("/revise_person_info")
	public String revisePersonInfo(@ModelAttribute("personInfo") RevisePersonInfoRequest request, Model model) {
		// 個人情報の編集要求を処理し、その結果を受け取ります。
		Response res = personInfoService.revisePersonInfo(request);
		// エラーメッセージとマイナンバーをモデルに追加します。
		return showExtendedProfileForm(model, res.getMessage(), request.getEmployeeNumber());
	}

	/**
	 * 拡張プロフィールを追加し、その結果に応じてリダイレクトまたはエラーページを表示します。
	 *
	 * @param request 拡張プロフィールの追加要求
	 * @param model Springのモデルオブジェクト
	 * @return リダイレクト先またはエラーページのビュー名
	 */
	@PostMapping("/add_extended_profile")
	public String addExtendedProfile(@ModelAttribute("extendedProfile") ExtendedProfileRequest request, Model model) {
		// 拡張プロフィールの追加要求を処理し、その結果を受け取ります。
		Response res = extendedProfileService.addExtendedProfile(request);
		// エラーメッセージをモデルに追加します。
		model.addAttribute("errorMessage", res.getMessage());
		// 結果に応じてリダイレクトまたはエラーページのビュー名を返します。
		return res.getMessage().equals(RtnCode.SUCCESS.getMessage()) ? "redirect:/person_list" : "extended_profile";
	}

	/**
	 * 拡張プロフィールを編集し、その結果に応じてリダイレクトまたはエラーページを表示します。
	 *
	 * @param request 拡張プロフィールの編集要求
	 * @param model Springのモデルオブジェクト
	 * @return リダイレクト先またはエラーページのビュー名
	 */
	@PostMapping("/revise_extended_profile")
	public String reviseExtendedProfile(@ModelAttribute("extendedProfile") ExtendedProfileRequest request,
			Model model) {
		// 拡張プロフィールの編集要求を処理し、その結果を受け取ります。
		Response res = extendedProfileService.reviseExtendedProfile(request);
		// エラーメッセージをモデルに追加します。
		model.addAttribute("errorMessage", res.getMessage());
		// 結果に応じてリダイレクトまたはエラーページのビュー名を返します。
		return res.getMessage().equals(RtnCode.SUCCESS.getMessage()) ? "redirect:/person_list" : "extended_profile";
	}

	/**
	 * 個人情報と拡張プロフィールを削除し、削除結果に応じてリダイレクトします。
	 *
	 * @param personId 削除対象の個人情報と拡張プロフィールのマイナンバー
	 * @param model Springのモデルオブジェクト
	 * @return リダイレクト先またはエラーページのビュー名
	 */
	@PostMapping("/delete_person_info")
	public String deletePersonInfo(@RequestParam Integer personId, Model model) {
		// 削除対象の個人情報と拡張プロフィールを削除するためのリクエストを作成します。
		DeletePersonInfoRequest pRequest = new DeletePersonInfoRequest();
		ExtendedProfileRequest eRequest = new ExtendedProfileRequest();
		pRequest.setEmployeeNumber(personId);
		eRequest.setEmployeeNumber(personId);
		// 個人情報と拡張プロフィールの削除結果を確認し、成功または失敗を判定します。
		Object error = personInfoService.deletePersonInfo(pRequest).getMessage().equals(RtnCode.SUCCESS.getMessage())
				&& extendedProfileService.deleteExtendedProfile(eRequest).getMessage()
						.equals(RtnCode.SUCCESS.getMessage()) ? "success" : "failed";
		// エラーメッセージをモデルに追加してリダイレクト先を指定します
		model.addAttribute("errorMessage", error);
		return "redirect:/person_list";
	}

	/**
	 * 個人情報フォームを表示するための共通メソッドです。
	 *
	 * @param model Springのモデルオブジェクト
	 * @param personInfo 個人情報オブジェクト
	 * @param isNew 新しい個人情報の場合は true、編集の場合は false
	 * @return 個人情報フォームのビュー名
	 */
	private String showPersonInfoForm(Model model, Object personInfo, boolean isNew) {
		// このメソッドは、個人情報フォームを表示するための共通ロジックを提供します。
		model.addAttribute("personInfo", personInfo);
		model.addAttribute("isNew", isNew);
		return "person_info";
	}

	/**
	 * 拡張プロフィールフォームを表示するための共通メソッドです。
	 *
	 * @param model Springのモデルオブジェクト
	 * @param message 成功またはエラーメッセージ
	 * @param employeeNumber マイナンバー
	 * @return 拡張プロフィールフォームまたはエラーページのビュー名
	 */
	private String showExtendedProfileForm(Model model, String message, Integer employeeNumber) {
		// このメソッドは、拡張プロフィールフォームを表示するための共通ロジックを提供します。
		if (message.equals(RtnCode.SUCCESS.getMessage())) {
			ExtendedProfileRequest extendedProfile = new ExtendedProfileRequest();
			extendedProfile.setEmployeeNumber(employeeNumber);
			model.addAttribute("extendedProfile", extendedProfile);
			return "extended_profile";
		}
		model.addAttribute("errorMessage", message);
		return "person_info";
	}
}
