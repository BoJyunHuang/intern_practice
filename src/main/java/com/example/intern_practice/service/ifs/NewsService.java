package com.example.intern_practice.service.ifs;

public interface NewsService {

	// ニュースを新規
	public void addNews();

	// ニュースを検索
	public void findNews();
	
	// ニュースを編集
	public void reviseNews();
	
	// ニュースを削除
	public void deleteNews();
}
