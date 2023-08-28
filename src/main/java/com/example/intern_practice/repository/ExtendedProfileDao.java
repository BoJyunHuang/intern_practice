package com.example.intern_practice.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.intern_practice.entity.ExtendedProfile;

@Repository
public interface ExtendedProfileDao extends JpaRepository<ExtendedProfile, Integer> {

	// 拡張プロファイルの挿入
	@Transactional
	@Modifying
	@Query(value = "insert into extended_profile (employee_number, telephone, mobile_phone, company_email, "
			+ "alternate_email, postal_code, address, passport_number, passport_expiry_date, residence_card_number, "
			+ "residence_card_start_date, residence_card_expiration_date, residence_card_status, "
			+ "employment_insurance_number, pension_number, bank_name, store_name, account_number, deleted_flag) "
			+ "select :employeeNumber, :telephone, :mobilePhone, :companyEmail, :alternateEmail, :postalCode, :address, "
			+ ":passportNumber, :passportExpiryDate, :residenceCardNumber, :residenceCardStartDate, "
			+ ":residenceCardExpirationDate, :residenceCardStatus, :employmentInsuranceNumber, :pensionNumber, :bankName, "
			+ ":storeName, :accountNumber, false where not exists (select 1 from extended_profile "
			+ "where employee_number = :employeeNumber)", nativeQuery = true)
	public int insertExtendedProfile(@Param("employeeNumber") int employeeNumber, @Param("telephone") String telephone,
			@Param("mobilePhone") String mobilePhone, @Param("companyEmail") String companyEmail,
			@Param("alternateEmail") String alternateEmail, @Param("postalCode") String postalCode,
			@Param("address") String address, @Param("passportNumber") String passportNumber,
			@Param("passportExpiryDate") LocalDate passportExpiryDate,
			@Param("residenceCardNumber") String residenceCardNumber,
			@Param("residenceCardStartDate") LocalDate residenceCardStartDate,
			@Param("residenceCardExpirationDate") LocalDate residenceCardExpirationDate,
			@Param("residenceCardStatus") String residenceCardStatus,
			@Param("employmentInsuranceNumber") String employmentInsuranceNumber,
			@Param("pensionNumber") String pensionNumber, @Param("bankName") String bankName,
			@Param("storeName") String storeName, @Param("accountNumber") String accountNumber);

	// 拡張プロファイルの更新
	@Transactional
	@Modifying
	@Query(value = "update extended_profile set telephone = :telephone, mobile_phone = :mobilePhone, company_email = "
			+ ":companyEmail, alternate_email = :alternateEmail, postal_code = :postalCode, address = :address, "
			+ "passport_number = :passportNumber, passport_expiry_date = :passportExpiryDate, residence_card_number = "
			+ ":residenceCardNumber, residence_card_start_date = :residenceCardStartDate, "
			+ "residence_card_expiration_date = :residenceCardExpirationDate, residence_card_status = "
			+ ":residenceCardStatus, employment_insurance_number = :employmentInsuranceNumber, pension_number = "
			+ ":pensionNumber, bank_name = :bankName, store_name = :storeName, account_number = :accountNumber, "
			+ "where employee_number = :employeeNumber", nativeQuery = true)
	public int updateExtendedProfile(@Param("employeeNumber") int employeeNumber, @Param("telephone") String telephone,
			@Param("mobilePhone") String mobilePhone, @Param("companyEmail") String companyEmail,
			@Param("alternateEmail") String alternateEmail, @Param("postalCode") String postalCode,
			@Param("address") String address, @Param("passportNumber") String passportNumber,
			@Param("passportExpiryDate") LocalDate passportExpiryDate,
			@Param("residenceCardNumber") String residenceCardNumber,
			@Param("residenceCardStartDate") LocalDate residenceCardStartDate,
			@Param("residenceCardExpirationDate") LocalDate residenceCardExpirationDate,
			@Param("residenceCardStatus") String residenceCardStatus,
			@Param("employmentInsuranceNumber") String employmentInsuranceNumber,
			@Param("pensionNumber") String pensionNumber, @Param("bankName") String bankName,
			@Param("storeName") String storeName, @Param("accountNumber") String accountNumber);

	// 拡張プロファイルの削除（削除フラグを立てる）
	@Transactional
	@Modifying
	@Query(value = "update extended_profile set deleted_flag = true where employee_number = :employeeNumber", nativeQuery = true)
	public int deleteExtendedProfile(@Param("employeeNumber") int employeeNumber);

}
