package com.example.intern_practice.constants;

public enum NewsAction {

	ADD("add"),
	FIND("find"),
	REVISE("revise"),
	PLUS("plus"),
	DELETE("delete"),
	SYSTEM("system");
	
	private String code;

	private NewsAction(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
