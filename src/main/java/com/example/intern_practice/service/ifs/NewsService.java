package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.NewsResponse;
import com.example.intern_practice.vo.NewsRequest;

public interface NewsService extends CRUD<NewsRequest, NewsResponse>{

	// ニュースを閲覧
	public NewsResponse viewNews(NewsRequest request);
	
}
