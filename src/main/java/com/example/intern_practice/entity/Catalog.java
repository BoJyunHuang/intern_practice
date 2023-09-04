package com.example.intern_practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catalog")
public class Catalog {

	// カタログID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "catalog_id")
	private Integer catalogId;
	
	// カタログ
	@Column(name = "catalog")
	private String catalog;
	
	// サブカタログ
	@Column(name = "subcatalog")
	private String subcatalog;
	
	// ニュース数
	@Column(name = "news_amount")
	private int newsAmount;

	// コンストラクタ
	public Catalog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Catalog(Integer catalogId, String catalog, String subcatalog, int newsAmount) {
		super();
		this.catalogId = catalogId;
		this.catalog = catalog;
		this.subcatalog = subcatalog;
		this.newsAmount = newsAmount;
	}

	// ゲッターとセッター
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
		
}
