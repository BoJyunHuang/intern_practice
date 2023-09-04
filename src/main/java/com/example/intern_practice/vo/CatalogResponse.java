package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.entity.Catalog;

public class CatalogResponse {

	private String message;
	private Catalog catalog;
	private List<Catalog> catalogList;
	
	public CatalogResponse() {
		super();
	}
	
	public CatalogResponse(String message) {
		super();
		this.message = message;
	}
	
	public CatalogResponse(String message, Catalog catalog) {
		super();
		this.message = message;
		this.catalog = catalog;
	}
	
	public CatalogResponse(String message, List<Catalog> catalogList) {
		super();
		this.message = message;
		this.catalogList = catalogList;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
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
