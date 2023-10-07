package com.example.intern_practice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;
import com.example.intern_practice.vo.NewsVO;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private CatalogService catalogService;

	// ホームページを表示する
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("newsList", newsService.get(null).getNewsList().stream()
				// 公開済みで有効期限内のニュースを選別する
				.filter(news -> news.getPublishTime().isBefore(LocalDateTime.now())
						&& news.getExpirationTime().isAfter(LocalDateTime.now()))
				.collect(Collectors.toList()));
		return HTML.HOME_PAGE.getPage();
	}

	// ニュースリストを表示し、モデルにニュースリストを追加する。
	@GetMapping("/news_manage")
	public String newsManagement(Model model) {
		model.addAttribute("timeRequest", new NewsRequest());
		model.addAttribute("newsList", newsService.get(null).getNewsList());
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
		News news = newsService.get(new NewsRequest(newsId)).getNews();
		return toEditPage(model, news, news.getCatalog(), false);
	}

	// ニュースを追加し、結果メッセージを返す。
	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
		return toResponsePage(model, newsService.add(request).getMsg());
	}

	// ニュースを修正し、結果メッセージを返す。
	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") NewsRequest request, Model model) {
		return toResponsePage(model, newsService.revise(request).getMsg());
	}

	// ニュースを削除し、削除結果を返す。
	@PostMapping("/delete_news")
	@ResponseBody
	public NewsResponse deleteNews(@RequestBody NewsRequest request) {
		return newsService.delete(request);
	}
	
	// 指定した時間ニュースリストを検索する。
	@PostMapping("/find_news")
	public String findNews(@ModelAttribute("timeRequest") NewsRequest request, Model model) {
		model.addAttribute("timeRequest", request);
		model.addAttribute("newsList", newsService.find(request).getNewsList());
		return HTML.NEWS_MANAGEMENT_PAGE.getPage();
	}

	// ニュースを読むためのページに移動する。
	@GetMapping("/read_news/{newsId}")
	public String readNews(@PathVariable Integer newsId, Model model) {
		newsService.viewNews(new NewsRequest(newsId));
		return toNewsPage(model, newsService.get(new NewsRequest(newsId)).getNews(), Action.TYPE_NEWS);
	}

	// ニュースをプレビューし、セッションにプレビュー情報を保存する
	@PostMapping("/preview_news")
	@ResponseBody
	public NewsResponse previewNews(@RequestBody NewsVO vo, HttpSession session, Model model) {
		session.setAttribute("previewNews", vo);
		return new NewsResponse(MSG.SUCCESS);
	}

	// ニュースのプレビュー情報を表示するためのページに移動する。
	@GetMapping("/preview_news")
	public String previewNews(Model model, HttpSession session) {
		return toNewsPage(model, (NewsVO) session.getAttribute("previewNews"), Action.TYPE_VIEW);
	}

	// ニュース編集ページに導入する。
	private String toEditPage(Model model, Object news, Catalog catalog, boolean isNew) {
		// カタログオプションを取得し、モデルに追加する。
		CatalogRequest request = new CatalogRequest();
		List<Catalog> catalogList = catalogService.find(request).getCatalogList();
		model.addAttribute("catalogOptions", catalogList);
		request.setParent(isNew ? catalogList.get(0).getName() : catalog.getParent());
		model.addAttribute("subcatalogOptions", catalogService.find(request).getCatalogList());
		// ニュースと新規ニュースの狀態をモデルに追加する。
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return HTML.NEWS_EDIT_PAGE.getPage();
	}

	// レスポンスページに導入する。
	private String toResponsePage(Model model, MSG msg) {
		model.addAttribute("type", Action.TYPE_NEWS.getType());
		model.addAttribute("code", msg.getCode());
		model.addAttribute("message", msg.getMessage());
		return HTML.RESPONSE_PAGE.getPage();
	}

	// ニュースページに導入する。
	private String toNewsPage(Model model, Object news, Action act) {
		model.addAttribute("type", act.getType());
		model.addAttribute("news", news);
		return HTML.NEWS_PAGE.getPage();
	}

}
