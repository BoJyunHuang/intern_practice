package com.example.intern_practice.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.intern_practice.entity.Catalog;

public class NewsRequest {

	// ニュースID
	private int newsId;
	// カタログ
	private Catalog catalog;
	// タイトル
	private String title;
	// サブタイトル
	private String subtitle;
	// タグ
	private String tags;
	// 本文
	private String content;
	// 作成日時
	private LocalDateTime createTime;
	// 公開日時
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime publishTime = LocalDateTime.now();
	// 編集日時
	private LocalDateTime editTime;
	// 取り下げる日時
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime expirationTime = LocalDateTime.now().plus(3, ChronoUnit.DAYS);
	// 削除日時
	private LocalDateTime removeTime;
	// 著者
	private String creator;
	// 編者
	private String editor;
	// 削除者
	private String remover;
	// 再生回数
	private int views;
	// 重要性
	private int importance;
	// 視聴者のレベル
	private int audienceLevel;
	// 削除フラグ
	private boolean deleteFlag;
	// IDリスト
	private List<Integer> idList;
	// 開始日付
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	// 終了日付
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	// 評価
	private Boolean like;
	
	// コンストラクタ
	public NewsRequest() {
		super();
	}

	public NewsRequest(int newsId) {
		super();
		this.newsId = newsId;
	}

	// ゲッターとセッター
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
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
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Boolean isLike() {
		return like;
	}
	public void setLike(Boolean like) {
		this.like = like;
	}

}
