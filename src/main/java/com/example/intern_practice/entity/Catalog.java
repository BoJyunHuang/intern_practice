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
	
	// 名前
	@Column(name = "name")
	private String name;
	
	// 親カタログ
	@Column(name = "parent")
	private String parent;
	
	// ニュース数
	@Column(name = "news_amount")
	private int newsAmount;
	
	// 削除フラグ
	@Column(name = "delete_flag")
	private boolean deleteFlag;

	// コンストラクタ
	public Catalog() {
		super();
	}

	public Catalog(Integer catalogId, String name, String parent, int newsAmount, boolean deleteFlag) {
		super();
		this.catalogId = catalogId;
		this.name = name;
		this.parent = parent;
		this.newsAmount = newsAmount;
		this.deleteFlag = deleteFlag;
	}

	// ゲッターとセッター
	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
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
