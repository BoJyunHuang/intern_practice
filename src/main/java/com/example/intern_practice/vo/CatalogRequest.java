package com.example.intern_practice.vo;

import java.util.List;

public class CatalogRequest {

	private int catalogId;
	private String catalog;
	private String subcatalog;
	private int newsAmount;
	private boolean deleteFlag;
	private List<Integer> idList;
	
	public int getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(int catalogId) {
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
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	
}
