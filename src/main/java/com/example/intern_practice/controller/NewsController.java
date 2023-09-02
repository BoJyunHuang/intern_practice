package com.example.intern_practice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.News;
import com.example.intern_practice.repository.NewsDao;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

@Controller
public class NewsController {

	@Autowired
	private NewsDao latestNewsDao;
	
	@Autowired
	private NewsService latestNewsService;

	/**
	 * 開かれたニュースを表示するためのメソッドです。
	 * 
	 * @param model モデルオブジェクト
	 * @return ニュースリストページのテンプレート名
	 */
	@GetMapping("/news_list_open")
	public String showOpenedNews(Model model) {
		// ニュースを表示するためのリクエストオブジェクトを作成し、表示を設定します
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(true);
		// ニュースの表示リクエストをサービスに送信し、レスポンスを受け取ります
		ShowNewsResponse response = latestNewsService.showNews(request);
		// モデルに表示するニュースリストを追加します
		model.addAttribute("newsList", response.getNewsList());
		// ニュースリストの表示画面に遷移します
		return "news-list";
	}

	/**
	 * 閉じられたニュースを表示するためのメソッドです。
	 * 
	 * @param model モデルオブジェクト
	 * @return ニュースリストページのテンプレート名
	 */
	@GetMapping("/news_list_close")
	public String showClosedNews(Model model) {
		// ニュースを表示するためのリクエストオブジェクトを作成し、表示を設定します
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(false);
		// ニュースの表示リクエストをサービスに送信し、レスポンスを受け取ります
		ShowNewsResponse response = latestNewsService.showNews(request);
		// モデルに表示するニュースリストを追加します
		model.addAttribute("newsList", response.getNewsList());
		// ニュースリストの表示画面に遷移します
		return "news-list";
	}
		
	/**
	 * ニュース追加ページを表示するためのメソッドです。
	 * 
	 * @param model モデルオブジェクト
	 * @return ニュース追加ページのテンプレート名
	 */
	@GetMapping("/add_news")
	public String toAddPage(Model model) {
		// モデルにニュースリクエストオブジェクトと新規作成フラグをセットし、共通メソッドを呼び出して表示します
	    return showNewsForm(model, new AddNewsRequest(), true);
	}

	/**
	 * ニュースを追加するためのメソッドです。
	 * 
	 * @param request 追加リクエストオブジェクト
	 * @param model モデルオブジェクト
	 * @return ニュース一覧ページのリダイレクトテンプレート名
	 */
	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") AddNewsRequest request, Model model) {
		// ニュースを追加するためのサービスメソッドを呼び出し、結果を処理するための共通メソッドを呼び出します
	    Response res = latestNewsService.addNews(request);
	    return processResponse(res, model, true);
	}

	/**
	 * ニュース修正ページを表示するためのメソッドです。
	 * 
	 * @param newsId ニュースID
	 * @param model モデルオブジェクト
	 * @return ニュース修正ページのテンプレート名
	 */
	@GetMapping("/revise_news/{newsId}")
	public String showReviseNewsForm(@PathVariable Integer newsId, Model model) {
		// ニュースIDを用いてデータベースからニュースを取得し、共通メソッドを呼び出して表示します
	    Optional<News> news = latestNewsDao.findById(newsId);
	    return showNewsForm(model, news.get(), false);
	}

	/**
	 * ニュースを修正するためのメソッドです。
	 * 
	 * @param request 修正リクエストオブジェクト
	 * @param model モデルオブジェクト
	 * @return ニュース一覧ページのリダイレクトテンプレート名
	 */
	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") ReviseNewsRequest request, Model model) {
		// ニュースを修正するためのサービスメソッドを呼び出し、結果を処理するための共通メソッドを呼び出します
	    Response res = latestNewsService.reviseNews(request);
	    return processResponse(res, model, false);
	}

	/**
	 * ニュースの表示状態を変更するためのメソッドです。
	 * 
	 * @param request 変更リクエストオブジェクト
	 * @param model モデルオブジェクト
	 * @return ニュース一覧ページのリダイレクトテンプレート名
	 */
	@PostMapping("/change_news_status")
	public String changeNewsStatus(@ModelAttribute ChangeNewsRequest request, Model model) {
		// ニュースの表示状態を変更するためのサービスメソッドを呼び出します
		Response res = latestNewsService.changeNewsStatus(request);
		// レスポンスのメッセージをモデルにセットして、ニュース一覧ページにリダイレクトします
		model.addAttribute("errorMessage", res.getMessage());
		return "redirect:/news_list_open";
	}
	
	/**
	 * ニュースフォームを表示するためのメソッドです。
	 * 
	 * @param model モデルオブジェクト
	 * @param news ニュースオブジェクト
	 * @param isNew 新規作成フラグ（true: 新規作成モード, false: 修正モード）
	 * @return ニュースフォームのテンプレート名
	 */
	private String showNewsForm(Model model, Object news, boolean isNew) {
		// ニュースオブジェクトと新規作成フラグをモデルにセットしてニュースフォームのテンプレート名を返します
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "add-news";
	}
	
	/**
	 * サービスメソッドの結果を処理するための共通メソッドです。
	 * 
	 * @param res レスポンスオブジェクト
	 * @param model モデルオブジェクト
	 * @param isNew 新規作成フラグ（true: 新規作成モード, false: 修正モード）
	 * @return レスポンスに基づいた遷移先テンプレート名
	 */
	private String processResponse(Response res, Model model, boolean isNew) {
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
			// 成功時はリダイレクトしてニュース一覧を表示します
			return "redirect:/news_list_open";
		} else {
			// 失敗時はエラーメッセージをモデルにセットしてニュースフォームを再表示します
			model.addAttribute("errorMessage", res.getMessage());
			return showNewsForm(model, model.getAttribute("news"), isNew);
		}
	}

}
