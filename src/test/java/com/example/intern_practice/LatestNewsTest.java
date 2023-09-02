package com.example.intern_practice;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.News;
import com.example.intern_practice.repository.NewsDao;
import com.example.intern_practice.service.ifs.NewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LatestNewsTest {

	@Autowired
	private NewsDao lD;

	@Autowired
	private NewsService lS;

	@BeforeAll
	private void BeforeAll() {
		News test1 = new News("classA", "program", "Test Title", "This is the test！");
		News test2 = new News("classA", "program", "Test Title", "This is the test！");
		test2.setReveal(false);
		News test3 = new News("classB", "program", "Test Title", "This is the test！");
		News test4 = new News("classB", "coding", "Test Title", "This is the test！");
		lD.saveAll(new ArrayList<>(Arrays.asList(test1, test2, test3, test4)));
	}

	@AfterAll
	private void AfterAll() {
		lD.deleteAll();
	}

	@Test
	public void searchNewsTest() {
		Assert.isTrue(lD.searchNews(false).size() == 1, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(true).size() == 3, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void updateNewsTest() {
		Assert.isTrue(lD.updateNews(0, null, null, null, null) == 0, RtnCode.TEST1_ERROR.getMessage());
		// JPQL can not insert 'null'
//		Assert.isTrue(lD.updateNews(4, null, null, null, null) == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lD.updateNews(4, "", "", "", "") == 1, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.updateNews(4, "classC", "New", "New", "Change！") == 1, RtnCode.TEST4_ERROR.getMessage());
	}
	
	@Test
	public void updateStatusTest() {
		Assert.isTrue(lD.updateStatus(false, -1) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(false, 10000) == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(true, 1) == 1, RtnCode.TEST3_ERROR.getMessage());
	}
	
	@Test
	public void showNewsTest() {
		ShowNewsRequest request = new ShowNewsRequest();
		Assert.isTrue(lS.showNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.showNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lS.showNews(request).getNewsList().size() == 1, RtnCode.TEST3_ERROR.getMessage());
		lD.updateStatus(false, 2);
		Assert.isTrue(lS.showNews(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST4_ERROR.getMessage());
		request.setReveal(true);
		Assert.isTrue(lS.showNews(request).getNewsList().size() == 4, RtnCode.TEST5_ERROR.getMessage());
		lD.updateStatus(true, 2);
	}
	
	@Test
	public void addNewsTest() {
		AddNewsRequest request = new AddNewsRequest();
		Assert.isTrue(lS.addNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.addNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setCatalog("classA");
		request.setSubcatalog("program");
		request.setTitle("Add News Test");
		request.setContent("If you can see this, it must be something wrong.");
		Assert.isTrue(lS.addNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(true).size() == 4, RtnCode.TEST4_ERROR.getMessage());
	}
	
	@Test
	public void reviseNewsTest() {
		ReviseNewsRequest request = new ReviseNewsRequest();
		Assert.isTrue(lS.reviseNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setSerialNumber(0);
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		request.setCatalog("classA");
		request.setSubcatalog("program");
		request.setTitle("Add News Test");
		request.setContent("If you can see this, it must be something wrong.");
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST4_ERROR.getMessage());
		request.setSerialNumber(1);
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST5_ERROR.getMessage());
	}

	@Test
	public void changeNewsStatusTest() {
		ChangeNewsRequest request = new ChangeNewsRequest();
		Assert.isTrue(lS.changeNewsStatus(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.changeNewsStatus(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setSerialNumber(0);
		Assert.isTrue(lS.changeNewsStatus(request).getMessage().equals(RtnCode.INCORRECT.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(false).size() == 1, RtnCode.TEST4_ERROR.getMessage());
		request.setSerialNumber(3);
		Assert.isTrue(lS.changeNewsStatus(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST5_ERROR.getMessage());
		request.setReveal(true);
		lS.changeNewsStatus(request);
	}
}
