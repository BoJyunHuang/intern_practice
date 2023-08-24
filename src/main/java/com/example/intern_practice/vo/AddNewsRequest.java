package com.example.intern_practice.vo;

public class AddNewsRequest {

	private String catalog;
	private String subcatalog;
	private String title;
	private String content;
	
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getSubcatalog() {
		return subcatalog;
	}
	public void setSubcatalog(String subcatalog) {
		this.subcatalog = subcatalog;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
