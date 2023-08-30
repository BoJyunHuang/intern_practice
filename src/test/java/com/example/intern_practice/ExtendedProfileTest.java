package com.example.intern_practice;

import java.time.LocalDate;
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
import com.example.intern_practice.entity.ExtendedProfile;
import com.example.intern_practice.repository.ExtendedProfileDao;
import com.example.intern_practice.service.ifs.ExtendedProfileService;
import com.example.intern_practice.vo.ExtendedProfileRequest;

@SpringBootTest(classes = InternPracticeApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExtendedProfileTest {

	@Autowired
	private ExtendedProfileDao extendedProfileDao;

	@Autowired
	private ExtendedProfileService extendedProfileService;

	@BeforeAll
	private void BeforeAll() {
		extendedProfileDao.saveAll(new ArrayList<>(Arrays.asList(
				new ExtendedProfile(1, "12345678", "0987654321", "test@Email.com", "alter@Email.com", "000000",
						"test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
						LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
						"city bank", "Taipei", "555-555-12345"),
				new ExtendedProfile(2, "12345678", "0987654321", "test@Email.com", "alter@Email.com", "000000",
						"test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
						LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
						"city bank", "Taipei", "555-555-12345"),
				new ExtendedProfile(3, "12345678", "0987654321", "test@Email.com", "alter@Email.com", "000000",
						"test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
						LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
						"city bank", "Taipei", "555-555-12345"),
				new ExtendedProfile(4, "12345678", "0987654321", "test@Email.com", "alter@Email.com", "000000",
						"test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
						LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
						"city bank", "Taipei", "555-555-12345"),
				new ExtendedProfile(5, "12345678", "0987654321", "test@Email.com", "alter@Email.com", "000000",
						"test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
						LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
						"city bank", "Taipei", "555-555-12345"))));
	}

	@AfterAll
	private void AfterAll() {
		extendedProfileDao.deleteAll();
	}

	@Test
	public void insertExtendedProfileTest() {
		Assert.isTrue(
				extendedProfileDao.insertExtendedProfile(1, "12345678", "0987654321", "test@Email.com",
						"alter@Email.com", "000000", "test adress from test class", "300000000",
						LocalDate.of(2030, 8, 31), "AB12345678CD", LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14),
						"永住者", "XXABC123456789", "1234-123456", "city bank", "Taipei", "555-555-12345") == 0,
				RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(
				extendedProfileDao.insertExtendedProfile(6, "12345678", "0987654321", "test@Email.com",
						"alter@Email.com", "000000", "test adress from test class", "300000000",
						LocalDate.of(2030, 8, 31), "AB12345678CD", LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14),
						"永住者", "XXABC123456789", "1234-123456", "city bank", "Taipei", "555-555-12345") == 1,
				RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void updateExtendedProfileTest() {
		Assert.isTrue(
				extendedProfileDao.updateExtendedProfile(1000, "12345678", "0987654321", "test@Email.com",
						"alter@Email.com", "000000", "test adress from test class", "300000000",
						LocalDate.of(2030, 8, 31), "AB12345678CD", LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14),
						"永住者", "XXABC123456789", "1234-123456", "city bank", "Taipei", "555-555-12345") == 0,
				RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(extendedProfileDao.updateExtendedProfile(1, "12345678", "0987654321", "test@Email.com", null,
				"000000", "test adress from test class", "300000000", LocalDate.of(2030, 8, 31), "AB12345678CD",
				LocalDate.of(2020, 6, 15), LocalDate.of(2023, 6, 14), "永住者", "XXABC123456789", "1234-123456",
				"city bank", "Taipei", "555-555-12345") == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void deleteExtendedProfileTest() {
		Assert.isTrue(extendedProfileDao.deleteExtendedProfile(0) == 0, RtnCode.TEST1_ERROR.getMessage());
		Assert.isTrue(extendedProfileDao.deleteExtendedProfile(1) == 1, RtnCode.TEST2_ERROR.getMessage());
	}

	@Test
	public void findExtendedProfileTest() {
		Assert.isTrue(
				extendedProfileService.findExtendedProfile(null).getMessage().equals(RtnCode.NOT_FOUND.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		ExtendedProfileRequest request = new ExtendedProfileRequest();
		request.setEmployeeNumber(0);
		Assert.isTrue(
				extendedProfileService.findExtendedProfile(request).getMessage().equals(RtnCode.NOT_FOUND.getMessage()),
				RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(1);
		Assert.isTrue(
				extendedProfileService.findExtendedProfile(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
	}

	@Test
	public void addExtendedProfileTest() {
		Assert.isTrue(
				extendedProfileService.addExtendedProfile(null).getMessage().equals(RtnCode.CANNOT_EMPTY.getMessage()),
				RtnCode.TEST1_ERROR.getMessage());
		ExtendedProfileRequest request = new ExtendedProfileRequest();
		Assert.isTrue(extendedProfileService.addExtendedProfile(request).getMessage()
				.equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(1);
		request.setTelephone("12345678");
		request.setMobilePhone("0987654321");
		request.setCompanyEmail("test@Email.com");
		request.setAlternateEmail("alter@Email.com");
		request.setPostalCode("000000");
		request.setAddress("test adress from test class");
		request.setPassportNumber("300000000");
		request.setPassportExpiryDate(LocalDate.of(2030, 8, 31));
		request.setResidenceCardNumber("AB12345678CD");
		request.setResidenceCardStartDate(LocalDate.of(2020, 6, 15));
		request.setResidenceCardExpirationDate(LocalDate.of(2023, 6, 14));
		request.setResidenceCardStatus("永住者");
		request.setEmploymentInsuranceNumber("XXABC123456789");
		request.setPensionNumber("1234-123456");
		request.setBankName("city bank");
		request.setStoreName("Taipei");
		request.setAccountNumber("555-555-12345");
		Assert.isTrue(
				extendedProfileService.addExtendedProfile(request).getMessage().equals(RtnCode.INCORRECT.getMessage()),
				RtnCode.TEST3_ERROR.getMessage());
		request.setEmployeeNumber(7);
		Assert.isTrue(
				extendedProfileService.addExtendedProfile(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
	}

	@Test
	public void reviseExtendedProfileTest() {
		Assert.isTrue(extendedProfileService.reviseExtendedProfile(null).getMessage()
				.equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		ExtendedProfileRequest request = new ExtendedProfileRequest();
		Assert.isTrue(extendedProfileService.reviseExtendedProfile(request).getMessage()
				.equals(RtnCode.CANNOT_EMPTY.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(0);
		request.setTelephone(null);
		request.setMobilePhone("0987654321");
		request.setCompanyEmail("test@Email.com");
		request.setAlternateEmail(null);
		request.setPostalCode(null);
		request.setAddress("test adress from test class");
		request.setPassportNumber("300000000");
		request.setPassportExpiryDate(LocalDate.of(2030, 8, 31));
		request.setResidenceCardNumber("AB12345678CD");
		request.setResidenceCardStartDate(LocalDate.of(2020, 6, 15));
		request.setResidenceCardExpirationDate(LocalDate.of(2023, 6, 14));
		request.setResidenceCardStatus("永住者");
		request.setEmploymentInsuranceNumber("XXABC123456789");
		request.setPensionNumber("1234-123456");
		request.setBankName("city bank");
		request.setStoreName("Taipei");
		request.setAccountNumber("555-555-12345");
		Assert.isTrue(extendedProfileService.reviseExtendedProfile(request).getMessage()
				.equals(RtnCode.INCORRECT.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		request.setEmployeeNumber(6);
		Assert.isTrue(
				extendedProfileService.reviseExtendedProfile(request).getMessage().equals(RtnCode.SUCCESS.getMessage()),
				RtnCode.TEST4_ERROR.getMessage());
	}
	
	@Test
	public void deleteExtendedProfileImplTest() {
		Assert.isTrue(extendedProfileService.deleteExtendedProfile(null).getMessage()
				.equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST1_ERROR.getMessage());
		ExtendedProfileRequest request = new ExtendedProfileRequest();
		Assert.isTrue(extendedProfileService.deleteExtendedProfile(request).getMessage()
				.equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST2_ERROR.getMessage());
		request.setEmployeeNumber(1000);
		Assert.isTrue(extendedProfileService.deleteExtendedProfile(request).getMessage()
				.equals(RtnCode.NOT_FOUND.getMessage()), RtnCode.TEST3_ERROR.getMessage());
		request.setEmployeeNumber(6);
		Assert.isTrue(extendedProfileService.deleteExtendedProfile(request).getMessage()
				.equals(RtnCode.SUCCESS.getMessage()), RtnCode.TEST4_ERROR.getMessage());
	}
}
