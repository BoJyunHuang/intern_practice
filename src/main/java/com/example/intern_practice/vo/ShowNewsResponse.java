package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.entity.LatestNews;

public class ShowNewsResponse {

	private String message;
	private List<LatestNews> newsList;
	
	public ShowNewsResponse() {
		super();
	}

	public ShowNewsResponse(String message) {
		super();
		this.message = message;
	}

	public ShowNewsResponse(String message, List<LatestNews> newsList) {
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

	public List<LatestNews> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<LatestNews> newsList) {
		this.newsList = newsList;
	}
	
}
