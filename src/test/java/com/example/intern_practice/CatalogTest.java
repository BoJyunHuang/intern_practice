package com.example.intern_practice;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.Catalog;
import com.example.intern_practice.repository.CatalogDao;
import com.example.intern_practice.service.ifs.CatalogService;
import com.example.intern_practice.vo.CatalogRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CatalogTest {

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private CatalogService catalogService;

	@BeforeAll
	private void BeforeAll() {
		catalogDao.saveAll(new ArrayList<>(Arrays.asList(new Catalog(1, "トップ", "政治", 0, false),
				new Catalog(2, "トップ", "社会", 0, false), new Catalog(3, "スポーツ", "野球", 0, false))));
	}

	@Test
	public void insertCatalogTest() {
		Assert.isTrue(catalogDao.insertCatalog("スポーツ", "野球") == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.insertCatalog("スポーツ", "サッカー") == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void updateCatalogTest() {
		catalogDao.insertCatalog("科学", "");
		Assert.isTrue(catalogDao.updateCatalog(0, "科学", "原子力") == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.updateCatalog(4, "科学", "原子力") == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void plusNewsAmountTest() {
		Assert.isTrue(catalogDao.plusNewsAmount(0) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.plusNewsAmount(4) == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void minusNewsAmountTest() {
		Assert.isTrue(catalogDao.minusNewsAmount(0) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.minusNewsAmount(4) == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void deleteCatalogTest() {
		Assert.isTrue(catalogDao.deleteCatalog(new ArrayList<>(Arrays.asList(0, -1))) == 0,
				RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.deleteCatalog(new ArrayList<>(Arrays.asList(1, 2))) == 2,
				RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void addCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.addCatalog(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		request.setCatalog("");
		request.setSubcatalog("");
		Assert.isTrue(catalogService.addCatalog(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setCatalog("科学");
		request.setSubcatalog("医療医療医療医療医療医療医療医療");
		Assert.isTrue(catalogService.addCatalog(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setSubcatalog("医療");
		Assert.isTrue(catalogService.addCatalog(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
	}

	@Test
	public void findCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.findCatalog(null).getCatalogList().size() == 6, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogService.findCatalog(request).getCatalog() == null, RtnCode.TEST2_ERROR.getMessage());
		request.setCatalogId(1);
		Assert.isTrue(catalogService.findCatalog(request).getCatalog().getCatalogId() == 1,
				RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void reviseCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.reviseCatalog(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		request.setCatalog("");
		request.setSubcatalog("");
		Assert.isTrue(catalogService.reviseCatalog(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setCatalogId(7);
		request.setCatalog("科学");
		request.setSubcatalog("健康・医療");
		Assert.isTrue(catalogService.reviseCatalog(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setCatalogId(6);
		Assert.isTrue(catalogService.reviseCatalog(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
	}

	@Test
	public void plusNewsTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.plusNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		request.setCatalogId(7);
		Assert.isTrue(catalogService.plusNews(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setCatalogId(6);
		Assert.isTrue(catalogService.plusNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
	}
	
	@Test
	public void minusNewsTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.minusNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		request.setCatalogId(7);
		Assert.isTrue(catalogService.minusNews(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setCatalogId(6);
		Assert.isTrue(catalogService.minusNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
	}
	
	@Test
	public void deleteCatalogImplTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.deleteCatalog(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		request.setIdList(new ArrayList<>(Arrays.asList(0, -1)));
		Assert.isTrue(catalogService.deleteCatalog(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setIdList(new ArrayList<>(Arrays.asList(3)));
		Assert.isTrue(catalogService.deleteCatalog(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
	}
}
