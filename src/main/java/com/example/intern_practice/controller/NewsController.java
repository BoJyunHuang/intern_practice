package com.example.intern_practice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public String home(Model model) {
		model.addAttribute("newsList", newsService.getNews(null).getNewsList());
		return "home";
	}

	// ニュースリストを表示し、モデルにニュースリストを追加する。
	@GetMapping("/news_list")
	public String newsList(Model model) {
		model.addAttribute("newsList", newsService.getNews(null).getNewsList());
		return "news-list";
	}

	// ニュースを追加するためのページに導入する。
	@GetMapping("/add_news")
	public String addNews(Model model) {
		return toEditPage(model, new NewsRequest(), true);
	}

	// ニュースを修正するためのページに導入する。修正するニュースのIDを指定する。
	@GetMapping("/revise_news/{newsId}")
	public String reviseNews(@PathVariable Integer newsId, Model model) {
		return toEditPage(model, newsService.getNews(new NewsRequest(newsId)).getNews(), false);
	}

	// ニュースを追加し、結果メッセージをモデルに追加する。
	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
		String res = newsService.addNews(request).getMessage();
		if (res.equals(MSG.SUCCESS.getMessage())) {
			CatalogRequest req = new CatalogRequest();
			req.setName(request.getCatalog());
			req.setParent("none");
			Catalog catalog1 = catalogService.findCatalog(req).getCatalog();
			req.setName(request.getSubcatalog());
			req.setParent(request.getCatalog());
			Catalog catalog2 = catalogService.findCatalog(req).getCatalog();
			List<Integer> idList = new ArrayList<>(Arrays.asList(catalog1.getCatalogId(), catalog2.getCatalogId()));
			req.setIdList(idList);
			catalogService.plusNews(req);
		}
		model.addAttribute("result", newsService.addNews(request).getMessage());
		return "response";
	}

	// ニュースを修正し、結果メッセージをモデルに追加する。
	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") NewsRequest request, Model model) {
		model.addAttribute("result", newsService.reviseNews(request).getMessage());
		return "response";
	}

	// ニュースを削除し、削除結果を返す。
	@PostMapping("/delete_news")
	@ResponseBody
	public NewsResponse deleteNews(@RequestBody NewsRequest request) {
		return newsService.deleteNews(request);
	}

	@GetMapping("/read_news/{newsId}")
	public String readNews(@PathVariable Integer newsId, Model model) {
		newsService.viewNews(new NewsRequest(newsId));
		model.addAttribute("news", newsService.getNews(new NewsRequest(newsId)).getNews());
		return "news";
	}
	
	@GetMapping("/preview_news")
	public String previewNews(@RequestBody NewsRequest request, Model model) {
		model.addAttribute("news", request);
		return "news";
	}

	// ニュース編集ページに導入する。
	private String toEditPage(Model model, Object news, boolean isNew) {
		// カタログオプションを取得し、モデルに追加する。
		List<Catalog> res = catalogService.findCatalog(new CatalogRequest()).getCatalogList();
		model.addAttribute("catalogOptions", res);
		CatalogRequest request = new CatalogRequest();
		if (isNew) {
			request.setParent(res.get(0).getName());
			List<Catalog> res2 = catalogService.findCatalog(request).getCatalogList();
			model.addAttribute("subcatalogOptions", res2);
		}
		// ニュースと新規ニュースの狀態をモデルに追加する。
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "news-edit";
	}

}
