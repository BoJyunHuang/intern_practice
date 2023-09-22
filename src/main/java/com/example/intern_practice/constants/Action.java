package com.example.intern_practice.constants;

public enum Action {
	ADD("add"),
	GET("get"),
    REVISE("revise"),
    PLUS("plus"),
    MINUS("minus"),
    DELETE("delete"),
    FIND("find"),	
    TYPE_NEWS("news"),
	TYPE_CATALOG("catalog"),
	TYPE_VIEW("view");

	private String type;

	private Action(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
