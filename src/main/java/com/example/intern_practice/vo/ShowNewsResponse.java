package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.entity.News;

public class ShowNewsResponse {

	private String message;
	private List<News> newsList;
	
	public ShowNewsResponse() {
		super();
	}

	public ShowNewsResponse(String message) {
		super();
		this.message = message;
	}

	public ShowNewsResponse(String message, List<News> newsList) {
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	
}
