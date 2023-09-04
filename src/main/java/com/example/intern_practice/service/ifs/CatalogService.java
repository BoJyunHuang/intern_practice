package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

public interface CatalogService {

	// カタログを追加または変更
	public CatalogResponse editCatalog(CatalogRequest request);
	
	// カタログを検索
	public CatalogResponse findCatalog(CatalogRequest request);
		
	// カタログを削除
	public CatalogResponse deleteCatalog(CatalogRequest request);
}
