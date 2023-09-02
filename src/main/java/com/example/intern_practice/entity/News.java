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
public class News {

	// シリアルナンバー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;
	
	// カタログ
	@Column(name = "catalog")
	private String catalog;
	
	// サブカタログ
	@Column(name = "subcatalog")
	private String subcatalog;
	
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
	@Column(name = "reveal")
	private boolean reveal = true;

	// コンストラクタ
	public News() {
		super();
	}

	public News(String catalog, String subcatalog, String title, String content) {
		super();
		this.catalog = catalog;
		this.subcatalog = subcatalog;
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

	public String getSubcatalog() {
		return subcatalog;
	}

	public void setSubcatalog(String subcatalog) {
		this.subcatalog = subcatalog;
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

	public boolean isReveal() {
		return reveal;
	}

	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}
	
}
