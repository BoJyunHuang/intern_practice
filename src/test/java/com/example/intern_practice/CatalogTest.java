package com.example.intern_practice;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.intern_practice.constants.MSG;
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
		catalogDao.saveAll(new ArrayList<>(Arrays.asList(new Catalog(1, "トップ", "none", false),
				new Catalog(2, "政治", "トップ", false), new Catalog(3, "社会", "トップ", false),
				new Catalog(4, "スポーツ", "none", false), new Catalog(5, "野球", "スポーツ", false))));
	}

	@Test
	public void insertCatalogTest() {
		Assert.isTrue(catalogDao.insertCatalog("スポーツ", "none") == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.insertCatalog("野球", "スポーツ") == 0, MSG.TEST2_ERROR.getMessage());
		Assert.isTrue(catalogDao.insertCatalog("科学", "none") == 1, MSG.TEST3_ERROR.getMessage());
		Assert.isTrue(catalogDao.insertCatalog("サッカー", "スポーツ") == 1, MSG.TEST4_ERROR.getMessage());
	}

	@Test
	public void updateCatalogTest() {
		catalogDao.insertCatalog("原力", "none");
		Assert.isTrue(catalogDao.updateCatalog(0, "原子力", "科学") == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.updateCatalog(8, "原子力", "科学") == 1, MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void deleteCatalogTest() {
		Assert.isTrue(catalogDao.deleteMultiCatalog(new ArrayList<>(Arrays.asList(0, -1))) == 0,
				MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.deleteMultiCatalog(new ArrayList<>(Arrays.asList(1, 2))) == 2,
				MSG.TEST2_ERROR.getMessage());
	}

	@Test
	public void findByParentTest() {
		Assert.isTrue(catalogDao.findByParentAndDeleteFlag(null, false).size() == 0, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogDao.findByParentAndDeleteFlag("", false).size() == 0, MSG.TEST2_ERROR.getMessage());
		Assert.isTrue(catalogDao.findByParentAndDeleteFlag("トップ", false).size() == 3, MSG.TEST3_ERROR.getMessage());
		Assert.isTrue(catalogDao.findByParentAndDeleteFlag("none", false).size() == 4, MSG.TEST4_ERROR.getMessage());
	}

	@Test
	public void addCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.add(request).getMsg().equals(MSG.CANNOT_EMPTY), MSG.TEST1_ERROR.getMessage());
		request.setName("");
		Assert.isTrue(catalogService.add(request).getMsg().equals(MSG.CANNOT_EMPTY), MSG.TEST2_ERROR.getMessage());
		request.setParent("科学");
		request.setName("医療医療医療医療医療医療医療医療");
		Assert.isTrue(catalogService.add(request).getMsg().equals(MSG.INCORRECT), MSG.TEST3_ERROR.getMessage());
		request.setName("医療");
		Assert.isTrue(catalogService.add(request).getMsg().equals(MSG.SUCCESS), MSG.TEST4_ERROR.getMessage());
	}

	@Test
	public void findCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.get(null).getCatalogList().size() == 6, MSG.TEST1_ERROR.getMessage());
		Assert.isTrue(catalogService.get(request).getCatalog() == null, MSG.TEST2_ERROR.getMessage());
		request.setCatalogId(1);
		Assert.isTrue(catalogService.get(request).getCatalog().getCatalogId() == 1, MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void reviseCatalogTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.revise(request).getMsg().equals(MSG.CANNOT_EMPTY), MSG.TEST1_ERROR.getMessage());
		request.setName("");
		Assert.isTrue(catalogService.revise(request).getMsg().equals(MSG.CANNOT_EMPTY), MSG.TEST2_ERROR.getMessage());
		request.setCatalogId(100);
		request.setParent("科学");
		request.setName("健康・医療");
		Assert.isTrue(catalogService.revise(request).getMsg().equals(MSG.INCORRECT), MSG.TEST3_ERROR.getMessage());
		request.setCatalogId(9);
		Assert.isTrue(catalogService.revise(request).getMsg().equals(MSG.SUCCESS), MSG.TEST4_ERROR.getMessage());
	}

	@Test
	public void deleteCatalogImplTest() {
		CatalogRequest request = new CatalogRequest();
		Assert.isTrue(catalogService.delete(request).getMsg().equals(MSG.CANNOT_EMPTY), MSG.TEST1_ERROR.getMessage());
		request.setIdList(new ArrayList<>(Arrays.asList(0, -1)));
		Assert.isTrue(catalogService.delete(request).getMsg().equals(MSG.INCORRECT), MSG.TEST2_ERROR.getMessage());
		request.setIdList(new ArrayList<>(Arrays.asList(3)));
		Assert.isTrue(catalogService.delete(request).getMsg().equals(MSG.SUCCESS), MSG.TEST3_ERROR.getMessage());
	}

	@Test
	public void Test() {
		System.out.println(catalogDao.findById(1).get().getNews().size());
	}

}
