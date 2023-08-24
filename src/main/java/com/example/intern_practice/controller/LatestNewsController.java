package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.intern_practice.service.ifs.LatestNewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

@Controller
public class LatestNewsController {

	@Autowired
	private LatestNewsService latestNewsService;

//	@GetMapping(value = "show_opened_news")
//	public ShowNewsResponse showOpenedNews() {
//		ShowNewsRequest request = new ShowNewsRequest();
//		request.setReveal(true);
//		return latestNewsService.showNews(request);
//	}
	@GetMapping(value = "/news_list_open")
	public String showOpenedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(true);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list"; // 對應到模板名稱
	}

//	@GetMapping(value = "show_closed_news")
//	public ShowNewsResponse showClosedNews() {
//		ShowNewsRequest request = new ShowNewsRequest();
//		request.setReveal(false);
//		return latestNewsService.showNews(request);
//	}
	@GetMapping(value = "/news_list_close")
	public String showClosedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(false);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list"; // 對應到模板名稱
	}

//	@PostMapping(value = "add_news")
//	public Response addNews(@RequestBody AddNewsRequest request) {
//		return latestNewsService.addNews(request);
//	}
	@PostMapping(value = "/add_news")
	public String addNews(@ModelAttribute AddNewsRequest request) {
		latestNewsService.addNews(request);
		return "redirect:/news_list_open"; // 重定向到列表頁面
	}

//	@PostMapping(value = "revise_news")
//	public Response reviseNews(@RequestBody ReviseNewsRequest request) {
//		return latestNewsService.reviseNews(request);
//	}
	@PostMapping(value = "/revise_news")
	public String reviseNews(@ModelAttribute ReviseNewsRequest request) {
		latestNewsService.reviseNews(request);
		return "redirect:/news_list_open"; // 重定向到列表頁面
	}

//	@PostMapping(value = "change_news_status")
//	public Response changeNewsStatus(ChangeNewsRequest request) {
//		return latestNewsService.changeNewsStatus(request);
//	}
	@PostMapping(value = "/change_news_status")
	public String changeNewsStatus(@ModelAttribute ChangeNewsRequest request) {
		latestNewsService.changeNewsStatus(request);
		return "redirect:/news_list_open"; // 重定向到列表頁面
	}

	@GetMapping(value = "turn_page")
	public String turnPage() {
		return "add-news";
	}

}
