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
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;

@Controller
public class NewsController {

	@Autowired
	private NewsDao latestNewsDao;
	
	@Autowired
	private NewsService latestNewsService;

	@GetMapping("/news_list_open")
	public String showOpenedNews(Model model) {
		model.addAttribute("newsList", "");
		return "news-list";
	}
		
	@GetMapping("/add_news")
	public String toAddPage(Model model) {
	    return showNewsForm(model, new NewsRequest(), true);
	}

	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") NewsRequest request, Model model) {
	    NewsResponse res = latestNewsService.addNews(request);
	    return processResponse(res, model, true);
	}

	@GetMapping("/revise_news/{newsId}")
	public String showReviseNewsForm(@PathVariable Integer newsId, Model model) {
	    Optional<News> news = latestNewsDao.findById(newsId);
	    return showNewsForm(model, news.get(), false);
	}

	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") NewsRequest request, Model model) {
	    NewsResponse res = latestNewsService.reviseNews(request);
	    return processResponse(res, model, false);
	}

	@PostMapping("/change_news_status")
	public String changeNewsStatus(@ModelAttribute NewsRequest request, Model model) {
		model.addAttribute("errorMessage", "");
		return "redirect:/news_list_open";
	}
	
	private String showNewsForm(Model model, Object news, boolean isNew) {
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "add-news";
	}
	
	private String processResponse(NewsResponse res, Model model, boolean isNew) {
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
			return "redirect:/news_list_open";
		} else {
			model.addAttribute("errorMessage", res.getMessage());
			return showNewsForm(model, model.getAttribute("news"), isNew);
		}
	}

}
