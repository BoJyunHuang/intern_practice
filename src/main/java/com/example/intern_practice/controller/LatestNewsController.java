package com.example.intern_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.intern_practice.service.ifs.LatestNewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

@RestController
public class LatestNewsController {
	
	@Autowired
	private LatestNewsService latestNewsService;
	
	@GetMapping(value = "show_opened_news")
	public ShowNewsResponse showOpenedNews() {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setShow(true);
		return latestNewsService.showNews(request);
	}
	
	@GetMapping(value = "show_closed_news")
	public ShowNewsResponse showClosedNews() {
		ShowNewsRequest request = new ShowNewsRequest();
		request.setShow(false);
		return latestNewsService.showNews(request);
	}
	
	@PostMapping(value = "add_news")
	public Response addNews(@RequestBody AddNewsRequest request) {
		return latestNewsService.addNews(request);
	}

	@PostMapping(value = "revise_news")
	public Response reviseNews(@RequestBody ReviseNewsRequest request) {
		return latestNewsService.reviseNews(request);
	}
	
	@PostMapping(value = "change_news_status")
	public Response changeNewsStatus(ChangeNewsRequest request) {
		return latestNewsService.changeNewsStatus(request);
	}
}
