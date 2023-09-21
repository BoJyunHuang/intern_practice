package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.entity.Catalog;

public class CatalogResponse {

	// メッセージ
	private MSG msg;
	// カタログ
	private Catalog catalog;
	// カタログリスト
	private List<Catalog> catalogList;
	
	// コンストラクタ
	public CatalogResponse() {
		super();
	}
		
	public CatalogResponse(MSG msg) {
		super();
		this.msg = msg;
	}

	public CatalogResponse(MSG msg, Catalog catalog) {
		super();
		this.msg = msg;
		this.catalog = catalog;
	}

	public CatalogResponse(MSG msg, List<Catalog> catalogList) {
		super();
		this.msg = msg;
		this.catalogList = catalogList;
	}

	// ゲッターとセッター
	public MSG getMsg() {
		return msg;
	}
	
	public void setMsg(MSG msg) {
		this.msg = msg;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public List<Catalog> getCatalogList() {
		return catalogList;
	}
	
	public void setCatalogList(List<Catalog> catalogList) {
		this.catalogList = catalogList;
	}
	
}
