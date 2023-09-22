package com.example.intern_practice.vo;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class NewsVO {

	// カタログ
	private String catalog;
	// サブカタログ
	private String subcatalog;
	// タイトル
	private String title;
	// サブタイトル
	private String subtitle;
	// タグ
	private String tags;
	// 本文
	private String content;
	// 公開日時
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime publishTime = LocalDateTime.now();
	// 著者
	private String creator;
	
	// ゲッターとセッター
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
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}

}
