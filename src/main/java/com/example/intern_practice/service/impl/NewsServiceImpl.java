package com.example.intern_practice.service.impl;

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
				: newsDao.insertNews(0, request.getTitle(), request.getSubtitle(), request.getTags(),
						request.getContent(), request.getCreateTime(), request.getPublishTime(),
						request.getRemoveTime(), request.getCreator(), request.getRemover(), 0,
						0) == 1 ? new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage())
								: new NewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
	}

	@Override
	public NewsResponse findNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsResponse reviseNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsResponse viewNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsResponse likeNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsResponse dislikeNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NewsResponse deleteNews(NewsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean checkNull(NewsRequest request, NewsAction action) {
		switch (action) {
		case ADD:
			return request.getCatalogId() < 1 || StringUtils.hasText(request.getTitle())
					|| StringUtils.hasText(request.getSubtitle()) || StringUtils.hasText(request.getTags())
					|| StringUtils.hasText(request.getContent()) || request.getCreateTime() == null
					|| request.getPublishTime() == null || request.getRemoveTime() == null
					|| StringUtils.hasText(request.getCreator()) || StringUtils.hasText(request.getEditor())
					|| StringUtils.hasText(request.getRemover()) || request.getImportance() < 1
					|| request.getAudienceLevel() < 1;
		default:
			return true;
		}
	}

}
