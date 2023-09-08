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
public class CatalogCotroller {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/catalog_list")
	public String showCatalogList(Model model) {
		model.addAttribute("catalogList", catalogService.findCatalog(null).getCatalogList());
		return "catalog-list";
	}

	@GetMapping("/add_catalog")
	public String addCatalog(Model model) {
		return toEditPage(model, new CatalogRequest(), true);
	}

	@GetMapping("/revise_catalog/{catalogId}")
	public String reviseCatalog(@PathVariable Integer catalogId, Model model) {
		CatalogRequest request = new CatalogRequest();
		request.setCatalogId(catalogId);
		return toEditPage(model, catalogService.findCatalog(request).getCatalog(), false);
	}

	@PostMapping("/add_catalog")
	public String addCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		model.addAttribute("result", catalogService.addCatalog(request).getMessage());
		return "response";
	}

	@PostMapping("/revise_catalog")
	public String reviseCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		model.addAttribute("result", catalogService.reviseCatalog(request).getMessage());
		return "response";
	}

	@PostMapping("/delete_catalog")
	@ResponseBody
	public CatalogResponse deleteCatalog(@RequestBody CatalogRequest request) {
		return catalogService.deleteCatalog(request);
	}

	private String toEditPage(Model model, Object catalog, boolean isNew) {
		model.addAttribute("catalog", catalog);
		model.addAttribute("isNew", isNew);
		return "catalog-edit";
	}

}
