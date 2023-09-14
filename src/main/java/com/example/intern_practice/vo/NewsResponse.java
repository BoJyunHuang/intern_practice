package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.entity.News;

public class NewsResponse {

	private String message;
	private News news;
	private List<News> newsList;
	
	public NewsResponse() {
		super();
	}
	
	public NewsResponse(String message) {
		super();
		this.message = message;
	}
	
	public NewsResponse(String message, News news) {
		super();
		this.message = message;
		this.news = news;
	}
	
	public NewsResponse(String message, List<News> newsList) {
		super();
		this.message = message;
		this.newsList = newsList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

}
