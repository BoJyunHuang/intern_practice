package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

public interface CatalogService {

	// カタログを新規
	public CatalogResponse addCatalog(CatalogRequest request);

	// カタログを取得
	public CatalogResponse getCatalog(CatalogRequest request);

	// カタログを編集
	public CatalogResponse reviseCatalog(CatalogRequest request);

	// カタログを削除
	public CatalogResponse deleteCatalog(CatalogRequest request);

	// カタログを検索
	public CatalogResponse findCatalog(CatalogRequest request);

}
