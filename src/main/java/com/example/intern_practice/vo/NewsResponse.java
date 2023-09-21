package com.example.intern_practice.vo;

import java.util.List;

import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.entity.News;

public class NewsResponse {

	// メッセージ
	private MSG msg;
	// ニュース
	private News news;
	// ニュースリスト
	private List<News> newsList;
	
	// コンストラクタ
	public NewsResponse() {
		super();
	}
	
	public NewsResponse(MSG msg) {
		super();
		this.msg = msg;
	}

	public NewsResponse(MSG msg, News news) {
		super();
		this.msg = msg;
		this.news = news;
	}

	public NewsResponse(MSG msg, List<News> newsList) {
		super();
		this.msg = msg;
		this.newsList = newsList;
	}

	// ゲッターとセッター
	public MSG getMsg() {
		return msg;
	}

	public void setMsg(MSG msg) {
		this.msg = msg;
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
