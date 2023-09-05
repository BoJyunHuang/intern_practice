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
		return checkNull(request, CatalogAction.ADD) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: outOfLimit(request) || catalogDao.insertCatalog(request.getCatalog(), request.getSubcatalog()) != 1
						? new CatalogResponse(RtnCode.INCORRECT.getMessage())
						: new CatalogResponse(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public CatalogResponse findCatalog(CatalogRequest request) {
		return checkNull(request, CatalogAction.FIND)
				? new CatalogResponse(RtnCode.SUCCESS.getMessage(), catalogDao.findAll())
				: new CatalogResponse(RtnCode.SUCCESS.getMessage(), catalogDao.findById(request.getCatalogId()).get());
	}

	@Override
	public CatalogResponse reviseCatalog(CatalogRequest request) {
		return checkNull(request, CatalogAction.REVISE) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: outOfLimit(request) || catalogDao.updateCatalog(request.getCatalogId(), request.getCatalog(),
						request.getSubcatalog()) != 1 ? new CatalogResponse(RtnCode.INCORRECT.getMessage())
								: new CatalogResponse(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public CatalogResponse plusNew(CatalogRequest request) {
		return checkNull(request, CatalogAction.PLUS) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: catalogDao.plusNewsAmount(request.getCatalogId()) == 1
						? new CatalogResponse(RtnCode.SUCCESS.getMessage())
						: new CatalogResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public CatalogResponse minusNews(CatalogRequest request) {
		return checkNull(request, CatalogAction.MINUS) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: catalogDao.minusNewsAmount(request.getCatalogId()) == 1
						? new CatalogResponse(RtnCode.SUCCESS.getMessage())
						: new CatalogResponse(RtnCode.INCORRECT.getMessage());
	}

	@Override
	public CatalogResponse deleteCatalog(CatalogRequest request) {
		return checkNull(request, CatalogAction.DELETE) ? new CatalogResponse(RtnCode.CANNOT_EMPTY.getMessage())
				: catalogDao.deleteCatalog(request.getIdList()) != request.getIdList().size()
						? new CatalogResponse(RtnCode.INCORRECT.getMessage())
						: new CatalogResponse(RtnCode.SUCCESS.getMessage());
	}

	private boolean checkNull(CatalogRequest request, CatalogAction action) {
		switch (action) {
		case ADD:
			return !StringUtils.hasText(request.getCatalog()) || !StringUtils.hasText(request.getSubcatalog());
		case FIND:
			return request == null;
		case REVISE:
			return request.getCatalogId() == null || !StringUtils.hasText(request.getCatalog())
					|| !StringUtils.hasText(request.getSubcatalog());
		case PLUS:
		case MINUS:
			return request.getCatalogId() == null;
		case DELETE:
			return CollectionUtils.isEmpty(request.getIdList());
		default:
			return true;
		}
	}

	private boolean outOfLimit(CatalogRequest request) {
		return request.getCatalog().length() > 10 || request.getSubcatalog().length() > 10;
	}

}
