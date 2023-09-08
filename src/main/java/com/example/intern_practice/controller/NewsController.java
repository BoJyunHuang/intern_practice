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

import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;

@Controller
public class NewsController {
	
	@Autowired
	private NewsService newsService;

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

	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
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
	
	private String showNewsForm(Model model, Object news, boolean isNew) {
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "news-edit";
	}
	
}
