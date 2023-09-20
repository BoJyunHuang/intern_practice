package com.example.intern_practice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.Action;
import com.example.intern_practice.constants.MSG;
import com.example.intern_practice.repository.CatalogDao;
import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.vo.CatalogRequest;
import com.example.intern_practice.vo.CatalogResponse;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private CatalogDao catalogDao;

	@Override
	public CatalogResponse addCatalog(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.ADD) ? new CatalogResponse(MSG.CANNOT_EMPTY.getMessage())
				// 入力値の検証が正常であり、カタログの挿入が成功した場合に対応する結果を返す。
				: result(checkInput(request) && catalogDao.insertCatalog(request.getName(), request.getParent()) == 1);
	}

	@Override
	public CatalogResponse getCatalog(CatalogRequest request) {
		// 入力値チェックを行う。リクエストがnullの場合は全てのカタログを返す。
		return checkNull(request, Action.GET) ? new CatalogResponse(MSG.SUCCESS.getMessage(), catalogDao.findAll())
				// カタログIDに対応するカタログを取得する。カタログが存在しない場合、nullを返す。
				: new CatalogResponse(MSG.SUCCESS.getMessage(),
						catalogDao.findById(request.getCatalogId()).orElse(null));
	}

	@Override
	public CatalogResponse reviseCatalog(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.REVISE) ? new CatalogResponse(MSG.CANNOT_EMPTY.getMessage())
				// 入力値の検証が正常であり、カタログの更新が成功した場合に対応する結果を返す。
				: result(checkInput(request) && catalogDao.updateCatalog(request.getCatalogId(), request.getName(),
						request.getParent()) == 1);
	}

	@Override
	public CatalogResponse plusNews(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.PLUS) ? new CatalogResponse(MSG.CANNOT_EMPTY.getMessage())
				// カタログのニュース数を増やし、増加した数が要求された数と一致する場合、対応する結果を返す。
				: result(catalogDao.plusNewsAmount(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse minusNews(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.MINUS) ? new CatalogResponse(MSG.CANNOT_EMPTY.getMessage())
				// カタログのニュース数を減らし、増加した数が要求された数と一致する場合、対応する結果を返す。
				: result(catalogDao.minusNewsAmount(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse deleteCatalog(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.DELETE) ? new CatalogResponse(MSG.CANNOT_EMPTY.getMessage())
				// カタログを削除し、削除した数が要求された数と一致する場合、対応する結果を返す。
				: result(catalogDao.deleteCatalog(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse findCatalog(CatalogRequest request) {
		// 入力値チェックを行う。
		return checkNull(request, Action.FIND)
				// 名前と親カタログに基づいてカタログを検索し、該当する結果を返す。
				? new CatalogResponse(MSG.SUCCESS.getMessage(),
						catalogDao.findByNameAndParent(request.getName(), request.getParent()))
				// 親カタログと削除フラグに基づいてカタログを検索し、該当する結果を返す。
				: new CatalogResponse(MSG.SUCCESS.getMessage(),
						catalogDao.findByParentAndDeleteFlag(request.getParent(), false));
	}

	// 入力値チェックメソッド
	private boolean checkNull(CatalogRequest request, Action action) {
		switch (action) {
		case ADD:
			return !StringUtils.hasText(request.getName());
		case GET:
			return request == null;
		case REVISE:
			return request.getCatalogId() == 0 || !StringUtils.hasText(request.getName());
		case PLUS:
		case MINUS:
		case DELETE:
			return CollectionUtils.isEmpty(request.getIdList());
		case FIND:
			return StringUtils.hasText(request.getName());
		default:
			return true;
		}
	}

	// 入力値の検証メソッド。名前が15文字以上の場合はfalseを返す。
	private boolean checkInput(CatalogRequest request) {
		return request.getName().length() <= 15;
	}

	// 操作の結果に基づいて適切なレスポンスを返す。
	private CatalogResponse result(boolean isSuccess) {
		return isSuccess ? new CatalogResponse(MSG.SUCCESS.getMessage())
				: new CatalogResponse(MSG.INCORRECT.getMessage());
	}

}
