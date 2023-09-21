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

import com.example.intern_practice.constants.Action;
import com.example.intern_practice.constants.HTML;
import com.example.intern_practice.constants.MSG;
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
		return HTML.ADMIN_PAGE.getPage();
	}

	// カタログリストを表示し、モデルにカタログリストを追加する。
	@GetMapping("/catalog_manage")
	public String catalogList(Model model) {
		model.addAttribute("catalogList", catalogService.getCatalog(null).getCatalogList());
		return HTML.CATALOG_MANAGEMENT_PAGE.getPage();
	}

	// カタログを追加するためのページに導入する。
	@GetMapping("/add_catalog")
	public String addCatalog(Model model) {
		return toEditPage(model, new CatalogRequest(), true);
	}

	// カタログを修正するためのページに導入する。
	@GetMapping("/revise_catalog/{catalogId}")
	public String reviseCatalog(@PathVariable Integer catalogId, Model model) {
		return toEditPage(model, catalogService.getCatalog(new CatalogRequest(catalogId)).getCatalog(), false);
	}

	// カタログを追加し、レスポンスページに遷移する。
	@PostMapping("/add_catalog")
	public String addCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		MSG msg = catalogService.addCatalog(request).getMsg();
		return toResponsePage(model, msg.getCode(), msg.getMessage());
	}

	// カタログを修正し、レスポンスページに遷移する。
	@PostMapping("/revise_catalog")
	public String reviseCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		MSG msg = catalogService.reviseCatalog(request).getMsg();
		return toResponsePage(model, msg.getCode(), msg.getMessage());
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
		return HTML.CATALOG_EDIT_PAGE.getPage();
	}

	// レスポンスページに導入する。
	private String toResponsePage(Model model, String code, String message) {
		model.addAttribute("type", Action.TYPE_CATALOG.getType());
		model.addAttribute("code", code);
		model.addAttribute("message", message);
		return HTML.RESPONSE_PAGE.getPage();
	}

}
