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

	/**
	 * 拡張プロファイル情報を挿入します。
	 *
	 * @param employeeNumber マイナンバー
	 * @param telephone 固定電話
	 * @param mobilePhone 携帯電話
	 * @param companyEmail メールアドレス（社内）
	 * @param alternateEmail メールアドレス（その他）
	 * @param postalCode 郵便番号
	 * @param address 住所
	 * @param passportNumber パスポート番号
	 * @param passportExpiryDate パスポート期限
	 * @param residenceCardNumber 在留カード番号
	 * @param residenceCardStartDate 在留カード開始日付き
	 * @param residenceCardExpirationDate 在留カード終了日付き
	 * @param residenceCardStatus 在留資格
	 * @param employmentInsuranceNumber 雇用保険番号
	 * @param pensionNumber 年金番号
	 * @param bankName 銀行名称
	 * @param storeName 本店名
	 * @param accountNumber 口座番号
	 * @return 挿入の結果
	 */
	@Transactional
	@Modifying
	@Query(value = "insert into extended_profile ("
			+ "employee_number, telephone, mobile_phone, company_email, alternate_email, "
			+ "postal_code, address, passport_number, passport_expiry_date, "
			+ "residence_card_number, residence_card_start_date, residence_card_expiration_date, residence_card_status, "
			+ "employment_insurance_number, pension_number, bank_name, store_name, account_number, deleted_flag) "
			+ "select :employeeNumber, :telephone, :mobilePhone, :companyEmail, :alternateEmail, "
			+ ":postalCode, :address, :passportNumber, :passportExpiryDate, "
			+ ":residenceCardNumber, :residenceCardStartDate, :residenceCardExpirationDate, :residenceCardStatus, "
			+ ":employmentInsuranceNumber, :pensionNumber, :bankName, :storeName, :accountNumber, false "
			+ "where not exists (select 1 from extended_profile where employee_number = :employeeNumber)", nativeQuery = true)
	public int insertExtendedProfile(
			@Param("employeeNumber") Integer employeeNumber, 
			@Param("telephone") String telephone,
			@Param("mobilePhone") String mobilePhone, 
			@Param("companyEmail") String companyEmail,
			@Param("alternateEmail") String alternateEmail, 
			@Param("postalCode") String postalCode,
			@Param("address") String address, 
			@Param("passportNumber") String passportNumber,
			@Param("passportExpiryDate") LocalDate passportExpiryDate,
			@Param("residenceCardNumber") String residenceCardNumber,
			@Param("residenceCardStartDate") LocalDate residenceCardStartDate,
			@Param("residenceCardExpirationDate") LocalDate residenceCardExpirationDate,
			@Param("residenceCardStatus") String residenceCardStatus,
			@Param("employmentInsuranceNumber") String employmentInsuranceNumber,
			@Param("pensionNumber") String pensionNumber, 
			@Param("bankName") String bankName,
			@Param("storeName") String storeName, 
			@Param("accountNumber") String accountNumber);

	/**
	 * 拡張プロファイル情報を更新します。
	 *
	 * @param employeeNumber マイナンバー
	 * @param telephone 固定電話
	 * @param mobilePhone 携帯電話
	 * @param companyEmail メールアドレス（社内）
	 * @param alternateEmail メールアドレス（その他）
	 * @param postalCode 郵便番号
	 * @param address 住所
	 * @param passportNumber パスポート番号
	 * @param passportExpiryDate パスポート期限
	 * @param residenceCardNumber 在留カード番号
	 * @param residenceCardStartDate 在留カード開始日付き
	 * @param residenceCardExpirationDate 在留カード終了日付き
	 * @param residenceCardStatus 在留資格
	 * @param employmentInsuranceNumber 雇用保険番号
	 * @param pensionNumber 年金番号
	 * @param bankName 銀行名称
	 * @param storeName 本店名
	 * @param accountNumber 口座番号
	 * @return 挿入の結果
	 */
	@Transactional
	@Modifying
	@Query(value = "update extended_profile set "
			+ "telephone = :telephone, mobile_phone = :mobilePhone, "
			+ "company_email = :companyEmail, alternate_email = :alternateEmail, "
			+ "postal_code = :postalCode, address = :address, "
			+ "passport_number = :passportNumber, passport_expiry_date = :passportExpiryDate, "
			+ "residence_card_number = :residenceCardNumber, residence_card_start_date = :residenceCardStartDate, "
			+ "residence_card_expiration_date = :residenceCardExpirationDate, residence_card_status = :residenceCardStatus, "
			+ "employment_insurance_number = :employmentInsuranceNumber, pension_number = :pensionNumber, "
			+ "bank_name = :bankName, store_name = :storeName, account_number = :accountNumber "
			+ "where employee_number = :employeeNumber", nativeQuery = true)
	public int updateExtendedProfile(
			@Param("employeeNumber") Integer employeeNumber, 
			@Param("telephone") String telephone,
			@Param("mobilePhone") String mobilePhone, 
			@Param("companyEmail") String companyEmail,
			@Param("alternateEmail") String alternateEmail, 
			@Param("postalCode") String postalCode,
			@Param("address") String address, 
			@Param("passportNumber") String passportNumber,
			@Param("passportExpiryDate") LocalDate passportExpiryDate,
			@Param("residenceCardNumber") String residenceCardNumber,
			@Param("residenceCardStartDate") LocalDate residenceCardStartDate,
			@Param("residenceCardExpirationDate") LocalDate residenceCardExpirationDate,
			@Param("residenceCardStatus") String residenceCardStatus,
			@Param("employmentInsuranceNumber") String employmentInsuranceNumber,
			@Param("pensionNumber") String pensionNumber, 
			@Param("bankName") String bankName,
			@Param("storeName") String storeName, 
			@Param("accountNumber") String accountNumber);

	/**
	 * 拡張プロファイル情報を削除フラグを設定して削除します。
	 *
	 * @param employeeNumber マイナンバー
	 * @return 削除の結果を表す整数値
	 */
	@Transactional
	@Modifying
	@Query(value = "update extended_profile "
			+ "set deleted_flag = true "
			+ "where employee_number = :employeeNumber", nativeQuery = true)
	public int deleteExtendedProfile(
			@Param("employeeNumber") Integer employeeNumber);

}
