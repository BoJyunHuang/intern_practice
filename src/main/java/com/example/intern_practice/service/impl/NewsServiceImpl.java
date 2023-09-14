package com.example.intern_practice.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.NewsAction;
import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.repository.NewsDao;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	@Override
	public NewsResponse addNews(NewsRequest request) {
		return checkNull(request, NewsAction.ADD) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(checkInput(request, NewsAction.ADD)
						&& newsDao.insertNews(request.getCatalog(), request.getSubcatalog(), request.getTitle(),
								request.getSubtitle(), request.getTags(), request.getContent(), LocalDateTime.now(),
								request.getPublishTime(), request.getExpirationTime(), request.getCreator(),
								request.getImportance(), request.getAudienceLevel()) == 1);
	}

	@Override
	public NewsResponse findNews(NewsRequest request) {
		return checkNull(request, NewsAction.FIND) ? new NewsResponse(RtnCode.SUCCESS.getMessage(), newsDao.findAll())
				: new NewsResponse(RtnCode.SUCCESS.getMessage(), newsDao.findById(request.getNewsId()).orElse(null));
	}
	
	@Override
	public NewsResponse reviseNews(NewsRequest request) {
		return checkNull(request, NewsAction.REVISE) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(checkInput(request, NewsAction.REVISE)
						&& newsDao.updateNews(request.getNewsId(), request.getCatalog(), request.getSubcatalog(),
								request.getTitle(), request.getSubtitle(), request.getTags(), request.getContent(),
								request.getPublishTime(), LocalDateTime.now(), request.getExpirationTime(),
								request.getEditor(), request.getImportance(), request.getAudienceLevel()) == 1);
	}

	@Override
	public NewsResponse viewNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(newsDao.plusView(request.getNewsId()) == 1);
	}

	@Override
	public NewsResponse likeNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(newsDao.plusLike(request.getNewsId()) == 1);
	}

	@Override
	public NewsResponse dislikeNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(newsDao.plusDislike(request.getNewsId()) == 1);
	}

	@Override
	public NewsResponse deleteNews(NewsRequest request) {
		return checkNull(request, NewsAction.DELETE) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(checkInput(request, NewsAction.DELETE) && newsDao.deleteNews(request.getIdList(),
						LocalDateTime.now(), request.getRemover()) == request.getIdList().size());
	}

	private boolean checkNull(NewsRequest request, NewsAction action) {
		switch (action) {
		case ADD:
			return !StringUtils.hasText(request.getCatalog()) || !StringUtils.hasText(request.getSubcatalog())
					|| !StringUtils.hasText(request.getTitle()) || !StringUtils.hasText(request.getSubtitle())
					|| !StringUtils.hasText(request.getContent()) || request.getPublishTime() == null
					|| request.getExpirationTime() == null || !StringUtils.hasText(request.getCreator())
					|| request.getImportance() < 1 || request.getAudienceLevel() < 1;
		case FIND:
			return request == null;
		case REVISE:
			return request.getNewsId() == 0 || !StringUtils.hasText(request.getCatalog())
					|| !StringUtils.hasText(request.getSubcatalog()) || !StringUtils.hasText(request.getTitle())
					|| !StringUtils.hasText(request.getSubtitle()) || !StringUtils.hasText(request.getContent())
					|| request.getPublishTime() == null || request.getExpirationTime() == null
					|| !StringUtils.hasText(request.getEditor()) || request.getImportance() < 1
					|| request.getAudienceLevel() < 1;
		case PLUS:
			return request.getNewsId() == 0;
		case DELETE:
			return CollectionUtils.isEmpty(request.getIdList()) || !StringUtils.hasText(request.getRemover());
		default:
			return true;
		}
	}

	private boolean checkInput(NewsRequest request, NewsAction action) {
		switch (action) {
		case ADD:
			return request.getCatalog().length() <= 15 && request.getSubcatalog().length() <= 15
					&& request.getTitle().length() <= 40 && request.getSubtitle().length() <= 80
					&& request.getTags().length() <= 120 && request.getContent().length() <= 1000
					&& request.getPublishTime().isAfter(LocalDateTime.now())
					&& request.getExpirationTime().isAfter(request.getPublishTime())
					&& request.getCreator().length() <= 45;
		case REVISE:
			return request.getCatalog().length() <= 15 && request.getSubcatalog().length() <= 15
					&& request.getTitle().length() <= 40 && request.getSubtitle().length() <= 80
					&& request.getTags().length() <= 120 && request.getContent().length() <= 1000
					&& request.getPublishTime().isAfter(LocalDateTime.now())
					&& request.getExpirationTime().isAfter(request.getPublishTime())
					&& request.getEditor().length() <= 45;
		case DELETE:
			return request.getRemover().length() <= 45;
		default:
			return true;
		}
	}

	private NewsResponse result(boolean isSuccess) {
		return isSuccess ? new NewsResponse(RtnCode.SUCCESS.getMessage())
				: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

}
