package com.example.intern_practice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.entity.Catalog;
import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private CatalogService catalogService;

	// ホームページを表示する
	@GetMapping("/home")
	public String Home(Model model) {
		model.addAttribute("newsList", newsService.findNews(null).getNewsList());
		return "home";
	}
	
	// ニュースリストを表示する
	@GetMapping("/news_list")
	public String showNewsList(Model model) {
		model.addAttribute("newsList", newsService.findNews(null).getNewsList());
		return "news-list";
	}

	@GetMapping("/add_news")
	public String addNews(Model model) {
		return showNewsForm(model, new NewsRequest(), true);
	}

	@GetMapping("/revise_news/{newsId}")
	public String ReviseNews(@PathVariable Integer newsId, Model model) {
		NewsRequest request = new NewsRequest();
		request.setNewsId(newsId);
		return showNewsForm(model, newsService.findNews(request).getNews(), false);
	}

	@PostMapping("/preview_news")
	@ResponseBody
	public NewsResponse previewNews(@RequestBody NewsRequest request, HttpSession session, Model model) {
		session.setAttribute("previewNews", request);
		return new NewsResponse(MSG.SUCCESS.getMessage());
	}

	@GetMapping("/preview_news")
	public String previewNews(Model model, HttpSession session) {
		model.addAttribute("news", (NewsRequest) session.getAttribute("previewNews"));
		return "news";
	}

	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
		String res = newsService.addNews(request).getMessage();
		if (res.equals(MSG.SUCCESS.getMessage())) {
			CatalogRequest req = new CatalogRequest();
			req.setName(request.getCatalog());
			req.setParent("none");
			Catalog catalog1 = catalogService.findCatalogByNameAndParent(req).getCatalog();
			req.setName(request.getSubcatalog());
			req.setParent(request.getCatalog());
			Catalog catalog2 = catalogService.findCatalogByNameAndParent(req).getCatalog();
			List<Integer> idList = new ArrayList<>(Arrays.asList(catalog1.getCatalogId(), catalog2.getCatalogId()));
			req.setIdList(idList);
			catalogService.plusNews(req);
		}
		model.addAttribute("result", newsService.addNews(request).getMessage());
		return "response";
	}

	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") NewsRequest request, Model model) {
		model.addAttribute("result", newsService.reviseNews(request).getMessage());
		return "response";
	}

	@PostMapping("/delete_news")
	@ResponseBody
	public NewsResponse changeNewsStatus(@RequestBody NewsRequest request) {
		return newsService.deleteNews(request);
	}

	@GetMapping("/read_news/{newsId}")
	public String ReadNews(@PathVariable Integer newsId, Model model) {
		NewsRequest request = new NewsRequest();
		request.setNewsId(newsId);
		newsService.viewNews(request);
		model.addAttribute("news", newsService.findNews(request).getNews());
		return "news";
	}

	private String showNewsForm(Model model, Object news, boolean isNew) {
		List<Catalog> res = catalogService.findCatalogByParent(new CatalogRequest()).getCatalogList();
		model.addAttribute("catalogOptions", res);
		CatalogRequest request = new CatalogRequest();
		if (isNew) {
			request.setParent(res.get(0).getName());
			List<Catalog> res2 = catalogService.findCatalogByParent(request).getCatalogList();
			model.addAttribute("subcatalogOptions", res2);
		}
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "news-edit";
	}

}
