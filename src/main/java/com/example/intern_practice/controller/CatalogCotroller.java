package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

@Controller
public class CatalogCotroller {

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/show_catalog_list")
	public String showCatalogList(Model model) {
		model.addAttribute("catalogList", catalogService.findCatalog(null).getCatalogList());
		return "catalog-list";
	}

	@GetMapping({ "/add_catalog", "/revise_catalog/{catalogId}" })
	public String editCatalog(@PathVariable(required = false) Integer catalogId, Model model) {
		CatalogRequest request = new CatalogRequest();
		if (catalogId != null) {
			request.setCatalogId(catalogId);
		}
		model.addAttribute("catalog", catalogId == null ? request : catalogService.findCatalog(request).getCatalog());
		return "catalog-edit";
	}

	@PostMapping("/edit_catalog")
	public String editCatalog(@ModelAttribute("catalog") CatalogRequest request, Model model) {
		CatalogResponse res = request.getCatalogId() != 0 ? catalogService.reviseCatalog(request)
				: catalogService.addCatalog(request);
		model.addAttribute(res.getMessage().equals(RtnCode.SUCCESS.getMessage()) ? "success" : "errorMessage",
				res.getMessage());
		return "catalog-edit";
	}

	public String deleteCatalog() {
		return "catalog-list";
	}
}
