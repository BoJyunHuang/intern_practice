package com.example.intern_practice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "latest_news")
public class LatestNews {

	// シリアルナンバー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;
	
	// カタログ
	@Column(name = "catalog")
	private String catalog;
	
	// サブカタログ
	@Column(name = "sub_catalog")
	private String subCatalog;
	
	// タイトル
	@Column(name = "title")
	private String title;
	
	// 内容
	@Column(name = "content")
	private String content;
		
	// 投稿時間
	@Column(name = "release_time")
	private LocalDateTime releaseTime = LocalDateTime.now();
		
	// 表示
	@Column(name = "show")
	private boolean show = true;

	// コンストラクタ
	public LatestNews() {
		super();
	}

	public LatestNews(String catalog, String subCatalog, String title, String content) {
		super();
		this.catalog = catalog;
		this.subCatalog = subCatalog;
		this.title = title;
		this.content = content;
	}

	// getters & setters
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

	public LocalDateTime getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(LocalDateTime releaseTime) {
		this.releaseTime = releaseTime;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
		
}
