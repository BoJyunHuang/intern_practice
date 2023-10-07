package com.example.intern_practice.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.Action;
import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.repository.NewsDao;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.NewsRequest;
import com.example.intern_practice.vo.NewsResponse;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao newsDao;

	// ニュースを新規
	@Override
	@Transactional
	public NewsResponse add(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.ADD) ? new NewsResponse(MSG.CANNOT_EMPTY)
				// 入力値の検証が正常であり、ニュースの挿入が成功した場合に対応する結果を返す。
				: result(checkInput(request, Action.ADD) && newsDao.insertNews(request.getCatalog().getCatalogId(),
						request.getTitle(), request.getSubtitle(), request.getTags(), request.getContent(),
						LocalDateTime.now(), request.getPublishTime(), request.getExpirationTime(),
						request.getCreator(), request.getImportance(), request.getAudienceLevel()) == 1);
	}

	// ニュースを取得
	@Override
	public NewsResponse get(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.GET)
				// リクエストがnullの場合は全てのニュースを返す。
				? new NewsResponse(MSG.SUCCESS, newsDao.findAllByOrderByPublishTimeDesc())
				// ニュースIDに対応するニュースを取得する。ニュースが存在しない場合、nullを返す。
				: new NewsResponse(MSG.SUCCESS, newsDao.findById(request.getNewsId()).orElse(null));
	}

	// ニュースを編集
	@Override
	@Transactional
	public NewsResponse revise(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.REVISE) ? new NewsResponse(MSG.CANNOT_EMPTY)
				// 入力値の検証が正常であり、ニュースの更新が成功した場合に対応する結果を返す。
				: result(checkInput(request, Action.REVISE)
						&& newsDao.updateNews(request.getNewsId(), request.getCatalog().getCatalogId(),
								request.getTitle(), request.getSubtitle(), request.getTags(), request.getContent(),
								request.getPublishTime(), LocalDateTime.now(), request.getExpirationTime(),
								request.getEditor(), request.getImportance(), request.getAudienceLevel()) == 1);
	}

	// ニュースを閲覧
	@Override
	public NewsResponse viewNews(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.VIEW) ? new NewsResponse(MSG.CANNOT_EMPTY)
				// ニュースの閲覧回数を増加し、増加した数が1と一致する場合、対応する結果を返す。
				: result(newsDao.plusView(request.getNewsId()) == 1);
	}

	// ニュースを削除
	@Override
	@Transactional
	public NewsResponse delete(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.DELETE) ? new NewsResponse(MSG.CANNOT_EMPTY)
				// ニュースを削除し、削除した数が要求された数と一致する場合、対応する結果を返す。
				: result(checkInput(request, Action.DELETE)
						? newsDao.deleteNews(request.getNewsId(), LocalDateTime.now(), Action.ADNIN.getType()) == 1
						: newsDao.deleteMultiNews(request.getIdList(), LocalDateTime.now(),
								Action.ADNIN.getType()) == request.getIdList().size());
	}

	// ニュースを検索
	@Override
	public NewsResponse find(NewsRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.FIND) ? new NewsResponse(MSG.CANNOT_EMPTY)
				: new NewsResponse(MSG.SUCCESS, (checkInput(request, Action.FIND))
						// 指定した開始時間と終了時間の間にあるニュース検索メソッド。
						? newsDao.findByPublishTimeAfterAndExpirationTimeBeforeOrderByPublishTimeDesc(
								request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay())
						: (request.getEndDate() != null
								// 指定した終了時間以前のニュース検索メソッド。
								? newsDao.findByExpirationTimeBeforeOrderByPublishTimeDesc(
										request.getEndDate().atStartOfDay())
								// 指定した開始時間以降のニュース検索メソッド。
								: newsDao.findByPublishTimeAfterOrderByPublishTimeDesc(
										request.getStartDate().atStartOfDay())));
	}

	// 入力値チェックメソッド
	private boolean checkNull(NewsRequest request, Action action) {
		switch (action) {
		case ADD:
			return request.getCatalog() == null || !StringUtils.hasText(request.getTitle())
					|| !StringUtils.hasText(request.getSubtitle()) || !StringUtils.hasText(request.getContent())
					|| request.getPublishTime() == null || request.getExpirationTime() == null
					|| !StringUtils.hasText(request.getCreator()) || request.getImportance() < 1
					|| request.getAudienceLevel() < 1;
		case GET:
			return request == null;
		case REVISE:
			return request.getNewsId() == 0 || request.getCatalog() == null || !StringUtils.hasText(request.getTitle())
					|| !StringUtils.hasText(request.getSubtitle()) || !StringUtils.hasText(request.getContent())
					|| request.getPublishTime() == null || request.getExpirationTime() == null
					|| !StringUtils.hasText(request.getEditor()) || request.getImportance() < 1
					|| request.getAudienceLevel() < 1;
		case VIEW:
			return request.getNewsId() == 0;
		case DELETE:
			return CollectionUtils.isEmpty(request.getIdList()) && request.getNewsId() == 0;
		case FIND:
			return request.getStartDate() == null && request.getEndDate() == null;
		default:
			return true;
		}
	}

	// 入力値の検証メソッド。
	private boolean checkInput(NewsRequest request, Action action) {
		switch (action) {
		case ADD:
			return request.getCatalog().getCatalogId() > 0 && request.getTitle().length() <= 40
					&& request.getSubtitle().length() <= 80 && request.getTags().length() <= 120
					&& request.getContent().length() <= 1000
					&& request.getExpirationTime().isAfter(request.getPublishTime())
					&& request.getCreator().length() <= 45;
		case REVISE:
			return request.getCatalog().getCatalogId() > 0 && request.getTitle().length() <= 40
					&& request.getSubtitle().length() <= 80 && request.getTags().length() <= 120
					&& request.getContent().length() <= 1000
					&& request.getExpirationTime().isAfter(request.getPublishTime())
					&& request.getEditor().length() <= 45;
		case DELETE:
			return request.getNewsId() > 0;
		case FIND:
			return request.getStartDate() != null && request.getEndDate() != null
					&& request.getStartDate().isBefore(request.getEndDate());
		default:
			return true;
		}
	}

	// 操作の結果に基づいて適切なレスポンスを返す。
	private NewsResponse result(boolean isSuccess) {
		return isSuccess ? new NewsResponse(MSG.SUCCESS) : new NewsResponse(MSG.INCORRECT);
	}

}
