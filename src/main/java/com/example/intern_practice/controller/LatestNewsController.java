package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.service.ifs.LatestNewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

@Controller
public class LatestNewsController {

	@Autowired
	private LatestNewsService latestNewsService;

	@GetMapping(value = "/news_list_open")
	public String showOpenedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(true);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list"; // 對應到模板名稱
	}

	@GetMapping(value = "/news_list_close")
	public String showClosedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(false);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list"; // 對應到模板名稱
	}

	@PostMapping(value = "/add_news")
	public String addNews(@ModelAttribute("news") AddNewsRequest request, Model model) {
		Response res = latestNewsService.addNews(request);
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
	        return "redirect:/news_list_open"; // 重定向到列表頁面
	    } else {
	        model.addAttribute("errorMessage", res.getMessage());
	        return "add-news"; // 返回錯誤頁面（或其他適當的處理）
	    }
	}

	@PostMapping(value = "/revise_news")
	public String reviseNews(@ModelAttribute ReviseNewsRequest request) {
		latestNewsService.reviseNews(request);
		return "redirect:/news_list_open"; // 重定向到列表頁面
	}

	@PostMapping(value = "/change_news_status")
	public String changeNewsStatus(@ModelAttribute ChangeNewsRequest request) {
		latestNewsService.changeNewsStatus(request);
		return "redirect:/news_list_open"; // 重定向到列表頁面
	}

	@GetMapping(value = "/turn_page")
	public String turnPage(Model model) {
		model.addAttribute("news", new AddNewsRequest());
		return "add-news";
	}

}
