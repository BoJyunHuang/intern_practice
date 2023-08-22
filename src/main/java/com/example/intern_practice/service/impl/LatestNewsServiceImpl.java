package com.example.intern_practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

@Service
public class LatestNewsServiceImpl implements LatestNewsService {

	@Autowired
	private LatestNewsDao latestNewsDao;

	@Override
	public ShowNewsResponse showNews(ShowNewsRequest request) {
		if (request == null) {
			return new ShowNewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}

		boolean status = request.isReveal();
		List<LatestNews> result = latestNewsDao.searchNews(status);

		return CollectionUtils.isEmpty(result) ? new ShowNewsResponse(RtnCode.NOT_FOUND.getMessage())
				: new ShowNewsResponse(RtnCode.SUCCESS.getMessage(), result);
	}

	@Override
	public Response addNews(AddNewsRequest request) {
		if (request == null || !StringUtils.hasText(request.getCatalog())
				|| !StringUtils.hasText(request.getSubCatalog()) || !StringUtils.hasText(request.getTitle())
				|| !StringUtils.hasText(request.getContent())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		String newCatalog = request.getCatalog();
		String newSubCatalog = request.getSubCatalog();
		String newTitle = request.getTitle();
		String newContent = request.getContent();
		LatestNews news = new LatestNews(newCatalog, newSubCatalog, newTitle, newContent);
		latestNewsDao.save(news);

		return new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public Response reviseNews(ReviseNewsRequest request) {
		if (request == null || request.getSerialNumber() == null || !StringUtils.hasText(request.getCatalog())
				|| !StringUtils.hasText(request.getSubCatalog()) || !StringUtils.hasText(request.getTitle())
				|| !StringUtils.hasText(request.getContent())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		Integer newsId = request.getSerialNumber();
		String newCatalog = request.getCatalog();
		String newSubCatalog = request.getSubCatalog();
		String newTitle = request.getTitle();
		String newContent = request.getContent();

		return latestNewsDao.updateNews(newsId, newCatalog, newSubCatalog, newTitle, newContent) == 1
				? new Response(RtnCode.SUCCESS.getMessage())
				: new Response(RtnCode.NOT_FOUND.getMessage());
	}

	@Override
	public Response changeNewsStatus(ChangeNewsRequest request) {
		if (request == null || CollectionUtils.isEmpty(request.getNewsIdList())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		List<Integer> idList = request.getNewsIdList();
		return latestNewsDao.updateStatus(request.isReveal(), idList) == idList.size()
				? new Response(RtnCode.SUCCESS.getMessage())
				: new Response(RtnCode.INCORRECT.getMessage());
	}

}
