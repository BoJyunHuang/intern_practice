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
import com.example.intern_practice.entity.LatestNews;
import com.example.intern_practice.repository.LatestNewsDao;
import com.example.intern_practice.service.ifs.LatestNewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LatestNewsTest {

	@Autowired
	private LatestNewsDao lD;

	@Autowired
	private LatestNewsService lS;

	@BeforeAll
	private void BeforeAll() {
		LatestNews test1 = new LatestNews("classA", "program", "Test Title", "This is the test！");
		test1.setSerialNumber(1);
		LatestNews test2 = new LatestNews("classA", "program", "Test Title", "This is the test！");
		test2.setSerialNumber(2);
		test2.setShow(false);
		LatestNews test3 = new LatestNews("classB", "program", "Test Title", "This is the test！");
		test3.setSerialNumber(3);
		LatestNews test4 = new LatestNews("classB", "coding", "Test Title", "This is the test！");
		test4.setSerialNumber(4);
		lD.saveAll(new ArrayList<>(Arrays.asList(test1, test2, test3, test4)));
	}

	@AfterAll
	private void AfterAll() {
		lD.deleteAllById(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
	}

	@Test
	public void searchNewsTest() {
		Assert.isTrue(lD.searchNews(false).size() == 1, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(true).size() == 3, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void updateNewsTest() {
		Assert.isTrue(lD.updateNews(0, null, null, null, null) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lD.updateNews(4, null, null, null, null) == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lD.updateNews(4, "", "", "", "") == 1, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.updateNews(4, "classC", "New", "New", "Change！") == 1, RtnCode.TEST4_ERROR.getMessage());
	}
	
	@Test
	public void updateStatusTest() {
		Assert.isTrue(lD.updateStatus(false, new ArrayList<>(Arrays.asList(-1, 0))) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(false, new ArrayList<>(Arrays.asList(9999, 10000))) == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(false, new ArrayList<>(Arrays.asList(1, 3, 4))) == 3, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(true, new ArrayList<>(Arrays.asList(1, 3))) == 2, RtnCode.TEST4_ERROR.getMessage());
		Assert.isTrue(lD.updateStatus(true, new ArrayList<>(Arrays.asList(4))) == 1, RtnCode.TEST5_ERROR.getMessage());
	}
	
	@Test
	public void showNewsTest() {
		ShowNewsRequest request = new ShowNewsRequest();
		Assert.isTrue(lS.showNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.showNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(lS.showNews(request).getNewsList().size() == 1, RtnCode.TEST2_ERROR.getMessage());
		lD.updateStatus(true, new ArrayList<>(Arrays.asList(2)));
		Assert.isTrue(lS.showNews(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		request.setShow(true);
		Assert.isTrue(lS.showNews(request).getNewsList().size() == 4, RtnCode.TEST4_ERROR.getMessage());
		lD.updateStatus(false, new ArrayList<>(Arrays.asList(2)));
	}
	
	@Test
	public void addNewsTest() {
		AddNewsRequest request = new AddNewsRequest();
		Assert.isTrue(lS.addNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.addNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setCatalog("classA");
		request.setSubCatalog("program");
		request.setTitle("Add News Test");
		request.setContent("If you can see this, it must be something wrong.");
		Assert.isTrue(lS.addNews(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(true).size() == 4, RtnCode.TEST4_ERROR.getMessage());
		lD.deleteById(5);
	}
	
	@Test
	public void reviseNewsTest() {
		ReviseNewsRequest request = new ReviseNewsRequest();
		Assert.isTrue(lS.reviseNews(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setSerialNumber(0);
		Assert.isTrue(lS.reviseNews(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		request.setCatalog("classA");
		request.setSubCatalog("program");
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
		request.setNewsIdList(new ArrayList<>(Arrays.asList(0, 1)));
		Assert.isTrue(lS.changeNewsStatus(request).getMessage().equals(RtnCode.INCORRECT.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(lD.searchNews(false).size() == 1, RtnCode.TEST4_ERROR.getMessage());
		request.setNewsIdList(new ArrayList<>(Arrays.asList(3, 4)));
		Assert.isTrue(lS.changeNewsStatus(request).getMessage().equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST5_ERROR.getMessage());
		request.setShow(true);
		lS.changeNewsStatus(request);
	}
}
