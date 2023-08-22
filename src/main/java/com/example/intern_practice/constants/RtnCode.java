package com.example.intern_practice.constants;

public enum RtnCode {
	
	SUCCESS("200", "Success!"), 
	CANNOT_EMPTY("400", "Input is empty!"), 
	INCORRECT("401", " Incorrect requests!"),
	NOT_FOUND("404", "Not found!"),
	TEST1_ERROR("409","Test1 error"),
	TEST2_ERROR("409","Test2 error"),
	TEST3_ERROR("409","Test3 error"),
	TEST4_ERROR("409","Test4 error"),
	TEST5_ERROR("409","Test5 error"),
	ALREADY_EXISTED("409", "Has already existed!"), 
	PATTERNISNOTMATCH("422", "Pattern is not match!");

	private String code;
	private String message;

	private RtnCode(String code, String message) {
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
