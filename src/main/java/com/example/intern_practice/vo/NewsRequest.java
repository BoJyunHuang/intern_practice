package com.example.intern_practice.vo;

import java.time.LocalDateTime;

public class NewsRequest {

	private Integer newsId;
	private int catalogId;
	private String title;
	private String subtitle;
	private String tags;
	private String content;
	private LocalDateTime createTime;
	private LocalDateTime publishTime;
	private LocalDateTime editTime;
	private LocalDateTime removeTime;
	private boolean autoRemoval;
	private String creator;
	private String editor;	
	private String remover;
	private int views;
	private int likes;
	private int dislikes;
	private int importance;
	private int audienceLevel;
	private boolean deleteFlag;
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public int getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
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
	public LocalDateTime getRemoveTime() {
		return removeTime;
	}
	public void setRemoveTime(LocalDateTime removeTime) {
		this.removeTime = removeTime;
	}
	public boolean isAutoRemoval() {
		return autoRemoval;
	}
	public void setAutoRemoval(boolean autoRemoval) {
		this.autoRemoval = autoRemoval;
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
