package com.example.intern_practice.vo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.intern_practice.entity.News;

public class NewsResponse {

	private String message;
	private News news;
	private List<News> newsList;
	private Page<News> newsPage;
	
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

	public NewsResponse(String message, Page<News> newsPage) {
		super();
		this.message = message;
		this.newsPage = newsPage;
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

	public Page<News> getNewsPage() {
		return newsPage;
	}

	public void setNewsPage(Page<News> newsPage) {
		this.newsPage = newsPage;
	}
	
}
