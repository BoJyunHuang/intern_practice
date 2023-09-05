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

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsTest {

	@Autowired
	private NewsDao lD;

	@Autowired
	private NewsService lS;

	@BeforeAll
	private void BeforeAll() {
		
	}

	@AfterAll
	private void AfterAll() {
	}

	@Test
	public void searchNewsTest() {
		
	}

	@Test
	public void updateNewsTest() {
		
	}
	
	@Test
	public void updateStatusTest() {
		
	}
	
	@Test
	public void showNewsTest() {
		
	}
	
	@Test
	public void addNewsTest() {
		
	}
	
	@Test
	public void reviseNewsTest() {
		
	}

	@Test
	public void changeNewsStatusTest() {
		
	}
}
