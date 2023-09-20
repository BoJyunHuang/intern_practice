package com.example.intern_practice.constants;

public enum MSG {
	
	SUCCESS("200", "成功！"), 
	CANNOT_EMPTY("400", "入力が空です！"), 
	INCORRECT("401", "誤ったリクエスト！"),
	NOT_FOUND("404", "見つかりません！"),
	TEST1_ERROR("409","テスト1エラー"),
	TEST2_ERROR("409","テスト2エラー"),
	TEST3_ERROR("409","テスト3エラー"),
	TEST4_ERROR("409","テスト4エラー"),
	TEST5_ERROR("409","テスト5エラー"),
	ALREADY_EXISTED("409", "既に存在します！"), 
	PATTERNISNOTMATCH("422", "パターンが一致しません！");

	private String code;
	private String message;

	private MSG(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
