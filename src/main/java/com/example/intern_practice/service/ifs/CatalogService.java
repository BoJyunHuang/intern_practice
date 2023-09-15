package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

public interface CatalogService {

	// カタログを新規
	public CatalogResponse addCatalog(CatalogRequest request);

	// カタログを検索
	public CatalogResponse findCatalog(CatalogRequest request);

	// カタログを編集
	public CatalogResponse reviseCatalog(CatalogRequest request);

	// カタログのニュース数を1つ増やす
	public CatalogResponse plusNews(CatalogRequest request);

	// カタログのニュース数を1つ減らす
	public CatalogResponse minusNews(CatalogRequest request);

	// カタログを削除
	public CatalogResponse deleteCatalog(CatalogRequest request);

	// カタログを特定検索1
	public CatalogResponse findCatalogByParent(CatalogRequest request);

	// カタログを特定検索2
	public CatalogResponse findCatalogByNameAndParent(CatalogRequest request);
}
