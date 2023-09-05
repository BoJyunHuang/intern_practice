package com.example.intern_practice.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
				: newsDao.insertNews(request.getCatalogId(), request.getTitle(), request.getSubtitle(),
						request.getTags(), request.getContent(), LocalDateTime.now(), request.getPublishTime(),
						request.getRemoveTime(), request.getCreator(), NewsAction.SYSTEM.getCode(),
						request.getImportance(), request.getAudienceLevel()) == 1
								? new NewsResponse(RtnCode.SUCCESS.getMessage())
								: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public NewsResponse findNews(NewsRequest request) {
		return checkNull(request, NewsAction.FIND) ? new NewsResponse(RtnCode.SUCCESS.getMessage(), newsDao.findAll())
				: new NewsResponse(RtnCode.SUCCESS.getMessage(), newsDao.findById(request.getNewsId()).get());
	}

	@Override
	public NewsResponse reviseNews(NewsRequest request) {
		return checkNull(request, NewsAction.REVISE) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: newsDao.updateNews(request.getNewsId(), request.getCatalogId(), request.getTitle(),
						request.getSubtitle(), request.getTags(), request.getContent(), request.getPublishTime(),
						LocalDateTime.now(), request.getRemoveTime(), request.getEditor(), request.getImportance(),
						request.getAudienceLevel()) == 1 ? new NewsResponse(RtnCode.SUCCESS.getMessage())
								: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public NewsResponse viewNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: newsDao.plusView(request.getNewsId()) == 1 ? new NewsResponse(RtnCode.SUCCESS.getMessage())
						: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public NewsResponse likeNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: newsDao.plusLike(request.getNewsId()) == 1 ? new NewsResponse(RtnCode.SUCCESS.getMessage())
						: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public NewsResponse dislikeNews(NewsRequest request) {
		return checkNull(request, NewsAction.PLUS) ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: newsDao.plusDislike(request.getNewsId()) == 1 ? new NewsResponse(RtnCode.SUCCESS.getMessage())
						: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public NewsResponse deleteNews(NewsRequest request) {
		return (checkNull(request, NewsAction.DELETE) ? newsDao.checkRemoveTime(LocalDateTime.now())
				: newsDao.deleteNews(request.getNewsId(), LocalDateTime.now(), request.getRemover())) == 1
						? new NewsResponse(RtnCode.SUCCESS.getMessage())
						: new NewsResponse(RtnCode.INCORRECT.getMessage());
	}

	private boolean checkNull(NewsRequest request, NewsAction action) {
		switch (action) {
		case ADD:
			return request.getCatalogId() < 1 || !StringUtils.hasText(request.getTitle())
					|| !StringUtils.hasText(request.getSubtitle()) || !StringUtils.hasText(request.getTags())
					|| !StringUtils.hasText(request.getContent()) || request.getPublishTime() == null
					|| request.getRemoveTime() == null || !StringUtils.hasText(request.getCreator())
					|| request.getImportance() < 1 || request.getAudienceLevel() < 1;
		case FIND:
			return request == null;
		case REVISE:
			return request.getNewsId() == null || request.getCatalogId() < 1 || !StringUtils.hasText(request.getTitle())
					|| !StringUtils.hasText(request.getSubtitle()) || !StringUtils.hasText(request.getTags())
					|| !StringUtils.hasText(request.getContent()) || request.getPublishTime() == null
					|| request.getRemoveTime() == null || !StringUtils.hasText(request.getEditor())
					|| request.getImportance() < 1 || request.getAudienceLevel() < 1;
		case PLUS:
			return request.getNewsId() == null;
		case DELETE:
			return request.getNewsId() == null || request.getRemoveTime() == null
					|| !StringUtils.hasText(request.getRemover());
		default:
			return true;
		}
	}

}
