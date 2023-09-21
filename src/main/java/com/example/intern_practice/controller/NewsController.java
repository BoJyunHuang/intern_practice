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

import com.example.intern_practice.constants.Action;
import com.example.intern_practice.constants.HTML;
import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.entity.Catalog;
import com.example.intern_practice.entity.News;
import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;
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
		return HTML.HOME_PAGE.getPage();
	}

	// ニュースリストを表示し、モデルにニュースリストを追加する。
	@GetMapping("/news_manage")
	public String newsManagement(Model model) {
		model.addAttribute("newsList", newsService.getNews(null).getNewsList());
		return HTML.NEWS_MANAGEMENT_PAGE.getPage();
	}

	// ニュースを追加するためのページに導入する。
	@GetMapping("/add_news")
	public String addNews(Model model) {
		return toEditPage(model, new NewsRequest(), null, true);
	}

	// ニュースを修正するためのページに導入する。修正するニュースのIDを指定する。
	@GetMapping("/revise_news/{newsId}")
	public String reviseNews(@PathVariable Integer newsId, Model model) {
		News news = newsService.getNews(new NewsRequest(newsId)).getNews();
		Catalog catalog = catalogService.getCatalog(new CatalogRequest(news.getCatalog())).getCatalog();
		return toEditPage(model, news, catalog.getName(), false);
	}

	// ニュースを追加し、結果メッセージを返す。
	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
		// カタログにニュースを追加し、結果メッセージを取得する。
		CatalogResponse catalogResponse = catalogService.plusNews(
				new CatalogRequest(new ArrayList<>(Arrays.asList(request.getCatalog(), request.getSubcatalog()))));
		// ニュースを追加し、結果メッセージを取得する。
		NewsResponse newsResponse = newsService.addNews(request);
		return toResponsePage(model, catalogResponse.getMsg(), newsResponse.getMsg());
	}

	// ニュースを修正し、結果メッセージを返す。
	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") NewsRequest request, Model model) {
		News oldNews = newsService.getNews(request).getNews();
		// カタログからニュースを削除し、結果メッセージを取得する。
		CatalogResponse minusNewsResponse = catalogService.minusNews(
				new CatalogRequest(new ArrayList<>(Arrays.asList(oldNews.getCatalog(), oldNews.getSubcatalog()))));
		// カタログにニュースを追加し、結果メッセージを取得する。
		CatalogResponse plusNewsResponse = catalogService.plusNews(
				new CatalogRequest(new ArrayList<>(Arrays.asList(request.getCatalog(), request.getSubcatalog()))));
		// ニュースを修正し、結果メッセージを取得する。
		NewsResponse newsResponse = newsService.reviseNews(request);
		return toResponsePage(model, minusNewsResponse.getMsg(), plusNewsResponse.getMsg(), newsResponse.getMsg());
	}

	// ニュースを削除し、削除結果を返す。
	@PostMapping("/delete_news")
	@ResponseBody
	public NewsResponse deleteNews(@RequestBody NewsRequest request) {
		return newsService.deleteNews(request);
	}

	// ニュースを読むためのページに移動する。
	@GetMapping("/read_news/{newsId}")
	public String readNews(@PathVariable Integer newsId, Model model) {
		newsService.viewNews(new NewsRequest(newsId));
		return toNewsPage(model, newsService.getNews(new NewsRequest(newsId)).getNews());
	}

	// ニュースをプレビューし、セッションにプレビュー情報を保存する
	@PostMapping("/preview_news")
	@ResponseBody
	public NewsResponse previewNews(@RequestBody NewsRequest request, HttpSession session, Model model) {
		session.setAttribute("previewNews", request);
		return new NewsResponse(MSG.SUCCESS);
	}

	// ニュースのプレビュー情報を表示するためのページに移動する。
	@GetMapping("/preview_news")
	public String previewNews(Model model, HttpSession session) {
		return toNewsPage(model, (NewsRequest) session.getAttribute("previewNews"));
	}

	// ニュース編集ページに導入する。
	private String toEditPage(Model model, Object news, String catalogName, boolean isNew) {
		// カタログオプションを取得し、モデルに追加する。
		CatalogRequest request = new CatalogRequest();
		List<Catalog> catalogList = catalogService.findCatalog(request).getCatalogList();
		model.addAttribute("catalogOptions", catalogList);
		request.setParent(isNew ? catalogList.get(0).getName() : catalogName);
		model.addAttribute("subcatalogOptions", catalogService.findCatalog(request).getCatalogList());
		// ニュースと新規ニュースの狀態をモデルに追加する。
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return HTML.NEWS_EDIT_PAGE.getPage();
	}

	// レスポンスページに導入する。
	private String toResponsePage(Model model, MSG... msgs) {
		model.addAttribute("type", Action.TYPE_NEWS.getType());
		// レスポンスをチェックする。
		MSG totalMsg = Arrays.stream(msgs).allMatch(msg -> msg.equals(MSG.SUCCESS)) ? MSG.SUCCESS : MSG.INCORRECT;
		model.addAttribute("code", totalMsg.getCode());
		model.addAttribute("message", totalMsg.getMessage());
		return HTML.RESPONSE_PAGE.getPage();
	}

	// ニュースページに導入する。
	private String toNewsPage(Model model, Object news) {
		model.addAttribute("news", news);
		return HTML.NEWS_PAGE.getPage();
	}

}
