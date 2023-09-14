package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.NewsResponse;
import com.example.intern_practice.vo.NewsRequest;

public interface NewsService {

	// ニュースを新規
	public NewsResponse addNews(NewsRequest request);

	// ニュースを検索
	public NewsResponse findNews(NewsRequest request);
	public NewsResponse pageNews(NewsRequest request);
	
	// ニュースを編集
	public NewsResponse reviseNews(NewsRequest request);
	
	// ニュースを閲覧
	public NewsResponse viewNews(NewsRequest request);
		
	// ニュースにいいねする
	public NewsResponse likeNews(NewsRequest request);
	
	// ニュースを嫌いな情報にする
	public NewsResponse dislikeNews(NewsRequest request);
	
	// ニュースを削除
	public NewsResponse deleteNews(NewsRequest request);
}
