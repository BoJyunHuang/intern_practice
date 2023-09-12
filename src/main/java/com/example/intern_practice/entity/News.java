package com.example.intern_practice.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "news")
public class News {

	// ニュースID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Integer newsId;

	// カタログ
	@Column(name = "catalog")
	private String catalog;

	// サブカタログ
	@Column(name = "subcatalog")
	private String subcatalog;

	// タイトル
	@Column(name = "title")
	private String title;

	// サブタイトル
	@Column(name = "subtitle")
	private String subtitle;

	// タグ
	@Column(name = "tags")
	private String tags;

	// 本文
	@Column(name = "content")
	private String content;

	// 作成日時
	@Column(name = "create_time")
	private LocalDateTime createTime;

	// 公開日時
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "publish_time")
	private LocalDateTime publishTime;

	// 編集日時
	@Column(name = "edit_time")
	private LocalDateTime editTime;

	// 取り下げる日時
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "expiration_time")
	private LocalDateTime expirationTime;

	// 削除日時
	@Column(name = "remove_time")
	private LocalDateTime removeTime;

	// 創造者
	@Column(name = "creator")
	private String creator;

	// 編集者
	@Column(name = "editor")
	private String editor;

	// 削除者
	@Column(name = "remover")
	private String remover;

	// 再生回数
	@Column(name = "views")
	private int views;

	// いいね数
	@Column(name = "likes")
	private int likes;

	// 嫌い数
	@Column(name = "dislikes")
	private int dislikes;

	// 重要性
	@Column(name = "importance")
	private int importance;

	// 視聴者のレベル
	@Column(name = "audience_level")
	private int audienceLevel;

	// 削除フラグ
	@Column(name = "delete_flag")
	private boolean deleteFlag;

	// コンストラクタ
	public News() {
		super();
	}

	public News(Integer newsId, String catalog, String subcatalog, String title, String subtitle, String tags,
			String content, LocalDateTime createTime, LocalDateTime publishTime, LocalDateTime editTime,
			LocalDateTime expirationTime, LocalDateTime removeTime, String creator, String editor, String remover,
			int views, int likes, int dislikes, int importance, int audienceLevel, boolean deleteFlag) {
		super();
		this.newsId = newsId;
		this.catalog = catalog;
		this.subcatalog = subcatalog;
		this.title = title;
		this.subtitle = subtitle;
		this.tags = tags;
		this.content = content;
		this.createTime = createTime;
		this.publishTime = publishTime;
		this.editTime = editTime;
		this.expirationTime = expirationTime;
		this.removeTime = removeTime;
		this.creator = creator;
		this.editor = editor;
		this.remover = remover;
		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
		this.importance = importance;
		this.audienceLevel = audienceLevel;
		this.deleteFlag = deleteFlag;
	}

	// ゲッターとセッター
	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
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

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}

	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}

	public LocalDateTime getRemoveTime() {
		return removeTime;
	}

	public void setRemoveTime(LocalDateTime removeTime) {
		this.removeTime = removeTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getRemover() {
		return remover;
	}

	public void setRemover(String remover) {
		this.remover = remover;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public int getAudienceLevel() {
		return audienceLevel;
	}

	public void setAudienceLevel(int audienceLevel) {
		this.audienceLevel = audienceLevel;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
