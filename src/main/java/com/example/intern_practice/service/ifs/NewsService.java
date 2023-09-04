package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.NewsResponse;
import com.example.intern_practice.vo.NewsRequest;

public interface NewsService {

	// ニュースを追加または変更
	public NewsResponse editNews(NewsRequest request);

	// ニュースを検索
	public NewsResponse findNews(NewsRequest request);
	
	// ニュースを削除
	public NewsResponse deleteNews(NewsRequest request);
}
