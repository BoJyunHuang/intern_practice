package com.example.intern_practice.service.ifs;

public interface CatalogService {

	// カタログを新規
	public void addCatalog();
	
	// カタログを検索
	public void findCatalog();
	
	// カタログを編集
	public void reviseCatalog();
	
	// カタログを削除
	public void deleteCatalog();
}
