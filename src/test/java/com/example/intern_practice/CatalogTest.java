package com.example.intern_practice;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.intern_practice.repository.CatalogDao;
import com.example.intern_practice.service.ifs.CatalogService;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CatalogTest {

	@Autowired
	private CatalogDao catalogDao;

	@Autowired
	private CatalogService catalogService;

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
