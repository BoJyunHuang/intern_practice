package com.example.intern_practice.vo;

public class CatalogRequest {

	private Integer catalogId;
	private String catalog;
	private String subcatalog;
	private int newsAmount;
	private boolean deleteFlag;
	
	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
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
	public int getNewsAmount() {
		return newsAmount;
	}
	public void setNewsAmount(int newsAmount) {
		this.newsAmount = newsAmount;
	}
	public boolean isDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
}