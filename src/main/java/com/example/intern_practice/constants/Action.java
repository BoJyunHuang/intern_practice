package com.example.intern_practice.constants;

public enum Action {
	ADD("add"),
	GET("get"),
    REVISE("revise"),
    VIEW("VIEW"),
    DELETE("delete"),
    FIND("find"),	
    TYPE_NEWS("news"),
	TYPE_CATALOG("catalog"),
	TYPE_VIEW("view"),
	DATA_TYPE_CATALOG("カタログ"),
	DATA_TYPE_TAGS("タグ"),
	ADNIN("管理者");

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
