package com.example.intern_practice.vo;

import java.util.List;

public class CatalogRequest {

	private int catalogId;
	private String name;
	private String parent = "none";
	private int newsAmount;
	private boolean deleteFlag;
	private List<Integer> idList;

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
