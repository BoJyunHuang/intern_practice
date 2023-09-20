package com.example.intern_practice.vo;

import java.util.List;

public class CatalogRequest {

	// カタログID
	private int catalogId;
	// 名前
	private String name;
	// 親カタログ
	private String parent = "none";
	// ニュース数
	private int newsAmount;
	// 削除フラグ
	private boolean deleteFlag;
	// IDリスト
	private List<Integer> idList;

	// コンストラクタ
	public CatalogRequest() {
		super();
	}

	public CatalogRequest(int catalogId) {
		super();
		this.catalogId = catalogId;
	}

	public CatalogRequest(List<Integer> idList) {
		super();
		this.idList = idList;
	}

	// ゲッターとセッター
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
