package com.example.intern_practice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.CatalogAction;
import com.example.intern_practice.constants.RtnCode;
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
		// Nullチェックを行う。リクエストがnullの場合はエラーレスポンスを返す。
		return checkNull(request, CatalogAction.ADD) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(checkInput(request) && catalogDao.insertCatalog(request.getName(), request.getParent()) == 1);
	}

	@Override
	public CatalogResponse findCatalog(CatalogRequest request) {
		// Nullチェックを行う。リクエストがnullの場合は全てのカタログを返す。
		return checkNull(request, CatalogAction.FIND)
				? new CatalogResponse(RtnCode.SUCCESS.getMessage(), catalogDao.findAll())
				: new CatalogResponse(RtnCode.SUCCESS.getMessage(),
						catalogDao.findById(request.getCatalogId()).orElse(null));
	}

	@Override
	public CatalogResponse reviseCatalog(CatalogRequest request) {
		// Nullチェックを行う。リクエストがnull、またはカタログIDが0、または名前が空の場合はエラーレスポンスを返す。
		return checkNull(request, CatalogAction.REVISE) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(checkInput(request) && catalogDao.updateCatalog(request.getCatalogId(), request.getName(),
						request.getParent()) == 1);
	}

	@Override
	public CatalogResponse plusNews(CatalogRequest request) {
		// Nullチェックを行う。リクエストがnull、またはIDリストが空の場合はエラーレスポンスを返す。
		return checkNull(request, CatalogAction.PLUS) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(catalogDao.plusNewsAmount(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse minusNews(CatalogRequest request) {
		// Nullチェックを行う。リクエストがnull、またはIDリストが空の場合はエラーレスポンスを返す。
		return checkNull(request, CatalogAction.MINUS) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(catalogDao.minusNewsAmount(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse deleteCatalog(CatalogRequest request) {
		// Nullチェックを行う。リクエストがnull、またはIDリストが空の場合はエラーレスポンスを返す。
		return checkNull(request, CatalogAction.DELETE) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: result(catalogDao.deleteCatalog(request.getIdList()) == request.getIdList().size());
	}

	@Override
	public CatalogResponse findCatalogByParent(CatalogRequest request) {
		// 指定した親カタログに関連するカタログを取得する。
		return new CatalogResponse(RtnCode.SUCCESS.getMessage(),
				catalogDao.findByParentAndDeleteFlag(request.getParent(), false));
	}

	@Override
	public CatalogResponse findCatalogByNameAndParent(CatalogRequest request) {
		// 指定した名前または親カタログに関連するカタログを取得する。
		return new CatalogResponse(RtnCode.SUCCESS.getMessage(),
				catalogDao.findByNameAndParent(request.getName(), request.getParent()));
	}

	// 入力値チェックメソッド
	private boolean checkNull(CatalogRequest request, CatalogAction action) {
		switch (action) {
		case ADD:
			return !StringUtils.hasText(request.getName());
		case FIND:
			return request == null;
		case REVISE:
			return request.getCatalogId() == 0 || !StringUtils.hasText(request.getName());
		case PLUS:
		case MINUS:
		case DELETE:
			return CollectionUtils.isEmpty(request.getIdList());
		default:
			return true;
		}
	}

	// リクエストの名前長さチェックを行う。名前が15文字以上の場合はfalseを返す。
	private boolean checkInput(CatalogRequest request) {
		return request.getName().length() <= 15;
	}

	// 操作の結果に基づいて適切なレスポンスを返す。
	private CatalogResponse result(boolean isSuccess) {
		return isSuccess ? new CatalogResponse(RtnCode.SUCCESS.getMessage())
				: new CatalogResponse(RtnCode.INCORRECT.getMessage());
	}

}
