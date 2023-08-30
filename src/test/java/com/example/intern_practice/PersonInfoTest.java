package com.example.intern_practice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.PersonInfo;
import com.example.intern_practice.repository.PersonInfoDao;
import com.example.intern_practice.service.ifs.PersonInfoService;
import com.example.intern_practice.vo.AddPersonInfoRequest;
import com.example.intern_practice.vo.DeletePersonInfoRequest;
import com.example.intern_practice.vo.FindPersonInfoRequest;
import com.example.intern_practice.vo.RevisePersonInfoRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonInfoTest {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Autowired
	private PersonInfoService personInfoService;

	@BeforeAll
	private void BeforeAll() {
		personInfoDao.saveAll(new ArrayList<>(Arrays.asList(
				new PersonInfo("測試者1", "テスト1", "tesuto1", "日本", "男", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 9, 1),
						null),
				new PersonInfo("測試者2", "テスト2", "tesuto2", "日本", "男", LocalDate.of(1991, 2, 2), LocalDate.of(2021, 9, 1),
						null),
				new PersonInfo("測試者3", "テスト3", "tesuto3", "美國", "男", LocalDate.of(1992, 1, 10), LocalDate.of(2020, 4, 1),
						null),
				new PersonInfo("測試者4", "テスト4", "tesuto4", "美國", "女", LocalDate.of(1989, 10, 1), LocalDate.of(2022, 12, 1),
						null),
				new PersonInfo("測試者5", "テスト5", "tesuto5", "台灣", "女", LocalDate.of(1993, 12, 25), LocalDate.of(2020, 6, 1),
						null))));
	}

	@AfterAll
	private void AfterAll() {
		personInfoDao.deleteAll();
	}

	@Test
	public void updatePersonInfoTest() {
		// update 方法輸入參數不能含有null
		Assert.isTrue(personInfoDao.updatePersonInfo(0, "測試者1", "テスト1", "tesuto1", "日本", "男", LocalDate.of(1990, 1, 1),
				LocalDate.of(2020, 9, 1), LocalDate.of(2023, 9, 1)) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.updatePersonInfo(1, "測試者1", "テスト1", "tesuto1", "日本", "男", LocalDate.of(1990, 1, 1),
				LocalDate.of(2020, 9, 1), LocalDate.of(2023, 9, 1)) == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void searchPersonInfoByNameTest() {
		Assert.isTrue(personInfoDao.searchPersonInfoByName(null).size() == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByName("試").size() == 5, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByName("to").size() == 5, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByName("ス").size() == 5, RtnCode.TEST4_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByName("yy").size() == 0, RtnCode.TEST5_ERROR.getMessage());
	}

	@Test
	public void findByNationalityTest() {
		Assert.isTrue(personInfoDao.findByNationality(null).size() == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.findByNationality("Japan").size() == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao.findByNationality("日本").size() == 2, RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void findByGenderTest() {
		Assert.isTrue(personInfoDao.findByGender(null).size() == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.findByGender("").size() == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao.findByGender("好").size() == 0, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(personInfoDao.findByGender("女").size() == 2, RtnCode.TEST4_ERROR.getMessage());
	}

	@Test
	public void searchPersonInfoByJoinDateTest() {
		Assert.isTrue(personInfoDao.searchPersonInfoByJoinDate(null, null).size() == 0,
				RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByJoinDate(LocalDate.of(2000, 1, 1), LocalDate.of(2019, 12, 31))
				.size() == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao.searchPersonInfoByJoinDate(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31))
				.size() == 3, RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void searchPersonInfoByDepartureDateTest() {
		Assert.isTrue(personInfoDao.searchPersonInfoByDepartureDate(null, null).size() == 0,
				RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao
				.searchPersonInfoByDepartureDate(LocalDate.of(2000, 1, 1), LocalDate.of(2022, 12, 31)).size() == 0,
				RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao
				.searchPersonInfoByDepartureDate(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31)).size() == 1,
				RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void deletePersonInfoTest() {
		Assert.isTrue(personInfoDao.deletePersonInfo(-1) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(personInfoDao.deletePersonInfo(0) == 0, RtnCode.TEST2_ERROR.getMessage());
		Assert.isTrue(personInfoDao.deletePersonInfo(1000) == 0, RtnCode.TEST3_ERROR.getMessage());
		Assert.isTrue(personInfoDao.deletePersonInfo(6) == 1, RtnCode.TEST4_ERROR.getMessage());
	}

	@Test
	public void showPersonInfoTest() {
		Assert.isTrue(personInfoService.showPersonInfo().getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
	}

	@Test
	public void addPersonInfoTest() {
		Assert.isTrue(personInfoService.addPersonInfo(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		AddPersonInfoRequest request = new AddPersonInfoRequest();
		Assert.isTrue(personInfoService.addPersonInfo(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setFullName("測試者7");
		request.setKanaName("テスト7");
		request.setRomanizedName("tesuto7");
		request.setNationality("韓國");
		request.setGender("女");
		request.setBirthDate(LocalDate.of(1995, 3, 3));
		request.setJoinDate(LocalDate.of(2023, 4, 1));
		Assert.isTrue(personInfoService.addPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void findPersonInfoTest() {
		Assert.isTrue(personInfoService.findPersonInfo(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		FindPersonInfoRequest request = new FindPersonInfoRequest();
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setName("測試");
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setName("");
		request.setNationality("美國");
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
		request.setNationality(null);
		request.setGender("男");
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST5_ERROR.getMessage());
		request.setGender("");
		request.setStart(LocalDate.of(2010, 1, 1));
		request.setEnd(LocalDate.of(2018, 12, 31));
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST6_ERROR.getMessage());
		request.setDeparture(true);
		Assert.isTrue(personInfoService.findPersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST7_ERROR.getMessage());
	}

	@Test
	public void revisePersonInfoTest() {
		Assert.isTrue(personInfoService.revisePersonInfo(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		RevisePersonInfoRequest request = new RevisePersonInfoRequest();
		Assert.isTrue(personInfoService.revisePersonInfo(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(0);
		request.setFullName("測試者");
		request.setKanaName("テスト");
		request.setRomanizedName("tesuto");
		request.setNationality("日本");
		request.setGender("男");
		request.setBirthDate(LocalDate.of(1990, 1, 1));
		request.setJoinDate(LocalDate.of(2020, 9, 1));
		request.setDpartureDate(LocalDate.of(2023, 9, 1));
		Assert.isTrue(personInfoService.revisePersonInfo(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setEmployeeNumber(1);
		Assert.isTrue(personInfoService.revisePersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
	}
	
	@Test
	public void deletePersonInfoImplTest() {
		Assert.isTrue(personInfoService.deletePersonInfo(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		DeletePersonInfoRequest request = new DeletePersonInfoRequest();
		Assert.isTrue(personInfoService.deletePersonInfo(request).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(0);
		Assert.isTrue(personInfoService.deletePersonInfo(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setEmployeeNumber(1000);
		Assert.isTrue(personInfoService.deletePersonInfo(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
		request.setEmployeeNumber(1);
		Assert.isTrue(personInfoService.deletePersonInfo(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST5_ERROR.getMessage());
	}
}
