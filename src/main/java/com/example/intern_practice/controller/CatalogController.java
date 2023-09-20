package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

@Controller
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	// 管理者ページへのアクセスを提供する。
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	// カタログリストを表示し、モデルにカタログリストを追加する。
	@GetMapping("/catalog_list")
	public String catalogList(Model model) {
		model.addAttribute("catalogList", catalogService.getCatalog(null).getCatalogList());
		return "catalog-list";
	}

	// カタログを追加するためのページに導入する。
	@GetMapping("/add_catalog")
	public String addCatalog(Model model) {
		return toEditPage(model, new CatalogRequest(), true);
	}

	// カタログを修正するためのページに導入する。修正するカタログのIDを指定する。
	@GetMapping("/revise_catalog/{catalogId}")
	public String reviseCatalog(@PathVariable Integer catalogId, Model model) {
		return toEditPage(model, catalogService.getCatalog(new CatalogRequest(catalogId)).getCatalog(), false);
	}

	// カタログを追加し、結果メッセージをモデルに追加する。
	@PostMapping("/add_catalog")
	public String addCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		model.addAttribute("result", catalogService.addCatalog(request).getMessage());
		// レスポンスページに遷移する。
		return "response";
	}

	// カタログを修正し、結果メッセージをモデルに追加する。
	@PostMapping("/revise_catalog")
	public String reviseCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		model.addAttribute("result", catalogService.reviseCatalog(request).getMessage());
		// レスポンスページに遷移する。
		return "response";
	}

	// カタログを削除し、削除結果を返す。
	@PostMapping("/delete_catalog")
	@ResponseBody
	public CatalogResponse deleteCatalog(@RequestBody CatalogRequest request) {
		return catalogService.deleteCatalog(request);
	}

	// カタログを検索し、検索結果を返す。
	@PostMapping("/find_catalog")
	@ResponseBody
	public CatalogResponse findCatalog(@RequestBody CatalogRequest request) {
		return catalogService.findCatalog(request);
	}

	// カタログ編集ページに導入する。
	private String toEditPage(Model model, Object catalog, boolean isNew) {
		// カタログオプションを取得し、モデルに追加する。
		model.addAttribute("catalogOptions", findCatalog(new CatalogRequest()).getCatalogList());
		// カタログと新規カタログの狀態をモデルに追加する。
		model.addAttribute("catalog", catalog);
		model.addAttribute("isNew", isNew);
		return "catalog-edit";
	}

}
