package com.example.intern_practice.constants;

public enum HTML {

	HOME_PAGE("home"),
	NEWS_PAGE("news"),
	ADMIN_PAGE("admin"),
	RESPONSE_PAGE("response"),
	CATALOG_EDIT_PAGE("catalog-edit"),
	CATALOG_MANAGEMENT_PAGE("catalog-manage"),
	NEWS_EDIT_PAGE("news-edit"),
	NEWS_MANAGEMENT_PAGE("news-manage");
	
	private String page;

	private HTML(String page) {
		this.page = page;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
}
