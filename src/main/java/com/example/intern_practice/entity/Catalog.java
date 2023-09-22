package com.example.intern_practice.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	// 削除フラグ
	@Column(name = "delete_flag")
	private boolean deleteFlag;

	// ニュース数
	@JsonManagedReference
	@OneToMany(mappedBy = "catalog", fetch = FetchType.EAGER)
	private Set<News> news;

	// コンストラクタ
	public Catalog() {
		super();
	}

	public Catalog(Integer catalogId, String name, String parent, boolean deleteFlag) {
		super();
		this.catalogId = catalogId;
		this.name = name;
		this.parent = parent;
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

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Set<News> getNews() {
		return news;
	}
	
	public void setNews(Set<News> news) {
		this.news = news;
	}
	
}
