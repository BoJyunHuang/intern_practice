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
import com.example.intern_practice.entity.LatestNews;
import com.example.intern_practice.repository.LatestNewsDao;
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
	private LatestNewsDao latestNewsDao;
	
	@Autowired
	private LatestNewsService latestNewsService;

	@GetMapping("/news_list_open")
	public String showOpenedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(true);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list";
	}

	@GetMapping("/news_list_close")
	public String showClosedNews(Model model) {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setReveal(false);
		ShowNewsResponse response = latestNewsService.showNews(request);
		model.addAttribute("newsList", response.getNewsList());
		return "news-list";
	}
		
	@GetMapping("/add_news")
	public String toAddPage(Model model) {
	    return showNewsForm(model, new AddNewsRequest(), true);
	}

	@PostMapping("/add_news")
	public String addNews(@ModelAttribute("news") AddNewsRequest request, Model model) {
	    Response res = latestNewsService.addNews(request);
	    return processResponse(res, model, true);
	}

	@GetMapping("/revise_news/{newsId}")
	public String showReviseNewsForm(@PathVariable Integer newsId, Model model) {
	    Optional<LatestNews> news = latestNewsDao.findById(newsId);
	    return showNewsForm(model, news.get(), false);
	}

	@PostMapping("/revise_news")
	public String reviseNews(@ModelAttribute("news") ReviseNewsRequest request, Model model) {
	    Response res = latestNewsService.reviseNews(request);
	    return processResponse(res, model, false);
	}

	@PostMapping("/change_news_status")
	public String changeNewsStatus(@ModelAttribute ChangeNewsRequest request, Model model) {
		Response res = latestNewsService.changeNewsStatus(request);
		model.addAttribute("errorMessage", res.getMessage());
		return "redirect:/news_list_open";
	}
	
	private String showNewsForm(Model model, Object news, boolean isNew) {
		model.addAttribute("news", news);
		model.addAttribute("isNew", isNew);
		return "add-news";
	}
	
	private String processResponse(Response res, Model model, boolean isNew) {
		if (res.getMessage().equals(RtnCode.SUCCESS.getMessage())) {
			return "redirect:/news_list_open";
		} else {
			model.addAttribute("errorMessage", res.getMessage());
			return showNewsForm(model, model.getAttribute("news"), isNew);
		}
	}

}
