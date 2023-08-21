package com.example.intern_practice.vo;

public class ReviseNewsRequest {

	private Integer serialNumber;
	private String catalog;
	private String subCatalog;
	private String title;
	private String content;
	
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getSubCatalog() {
		return subCatalog;
	}
	public void setSubCatalog(String subCatalog) {
		this.subCatalog = subCatalog;
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
