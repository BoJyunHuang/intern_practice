package com.example.intern_practice.vo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class NewsRequest {

	private int newsId;
	private String catalog;
	private String subcatalog;
	private String title;
	private String subtitle;
	private String tags;
	private String content;
	private LocalDateTime createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime publishTime = LocalDateTime.now();
	private LocalDateTime editTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime expirationTime = LocalDateTime.now().plus(3, ChronoUnit.DAYS);
	private LocalDateTime removeTime;
	private String creator;
	private String editor;	
	private String remover;
	private int views;
	private int likes;
	private int dislikes;
	private int importance;
	private int audienceLevel;
	private boolean deleteFlag;
	private List<Integer> idList;
		
	public NewsRequest() {
		super();
	}
		
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
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
	public List<Integer> getIdList() {
		return idList;
	}
	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

}
