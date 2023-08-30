package com.example.intern_practice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "extended_profile")
public class ExtendedProfile {
	
	// 在留情報
	// マイナンバー
	@Id
	@Column(name = "employee_number")
	private Integer employeeNumber;
	
	//　固定電話
	@Column(name = "telephone")
	private String telephone;
	
	//　携帯電話
	@Column(name = "mobile_phone")
	private String mobilePhone;
	
	//　メールアドレス（社内）
	@Column(name = "company_email")
	private String companyEmail;
	
	//　メールアドレス（その他）
	@Column(name = "alternate_email")
	private String alternateEmail;
	
	//　郵便番号
	@Column(name = "postal_code")
	private String postalCode;
	
	//　住所
	@Column(name = "address")
	private String address;
	
	//　パスポート番号
	@Column(name = "passport_number")
	private String passportNumber;
	
	//　パスポート期限
	@Column(name = "passport_expiry_date")
	private LocalDate passportExpiryDate;
	
	//　在留カード番号
	@Column(name = "residence_card_number")
	private String residenceCardNumber;
	
	//　在留カード開始日付き
	@Column(name = "residence_card_start_date")
	private LocalDate residenceCardStartDate;
	
	//　在留カード終了日付き
	@Column(name = "residence_card_expiration_date")
	private LocalDate residenceCardExpirationDate;
	
	//　在留資格
	@Column(name = "residence_card_status")
	private String residenceCardStatus;
	
	//　給付情報
	//　雇用保険番号
	@Column(name = "employment_insurance_number")
	private String employmentInsuranceNumber;
	
	//　年金番号
	@Column(name = "pension_number")
	private String pensionNumber;
	
	//　銀行名称
	@Column(name = "bank_name")
	private String bankName;
	
	//　本店名
	@Column(name = "store_name")
	private String storeName;
	
	//　口座番号
	@Column(name = "account_number")
	private String accountNumber;
	
	// 削除フラグ
	@Column(name = "deleted_flag")
	private boolean deletedFlag = false;

	// コンストラクタ
	public ExtendedProfile() {
		super();
	}

	public ExtendedProfile(Integer employeeNumber, String telephone, String mobilePhone, String companyEmail,
			String alternateEmail, String postalCode, String address, String passportNumber,
			LocalDate passportExpiryDate, String residenceCardNumber, LocalDate residenceCardStartDate,
			LocalDate residenceCardExpirationDate, String residenceCardStatus, String employmentInsuranceNumber,
			String pensionNumber, String bankName, String storeName, String accountNumber) {
		super();
		this.employeeNumber = employeeNumber;
		this.telephone = telephone;
		this.mobilePhone = mobilePhone;
		this.companyEmail = companyEmail;
		this.alternateEmail = alternateEmail;
		this.postalCode = postalCode;
		this.address = address;
		this.passportNumber = passportNumber;
		this.passportExpiryDate = passportExpiryDate;
		this.residenceCardNumber = residenceCardNumber;
		this.residenceCardStartDate = residenceCardStartDate;
		this.residenceCardExpirationDate = residenceCardExpirationDate;
		this.residenceCardStatus = residenceCardStatus;
		this.employmentInsuranceNumber = employmentInsuranceNumber;
		this.pensionNumber = pensionNumber;
		this.bankName = bankName;
		this.storeName = storeName;
		this.accountNumber = accountNumber;
	}

	// ゲッターとセッター
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public LocalDate getPassportExpiryDate() {
		return passportExpiryDate;
	}

	public void setPassportExpiryDate(LocalDate passportExpiryDate) {
		this.passportExpiryDate = passportExpiryDate;
	}

	public String getResidenceCardNumber() {
		return residenceCardNumber;
	}

	public void setResidenceCardNumber(String residenceCardNumber) {
		this.residenceCardNumber = residenceCardNumber;
	}

	public LocalDate getResidenceCardStartDate() {
		return residenceCardStartDate;
	}

	public void setResidenceCardStartDate(LocalDate residenceCardStartDate) {
		this.residenceCardStartDate = residenceCardStartDate;
	}

	public LocalDate getResidenceCardExpirationDate() {
		return residenceCardExpirationDate;
	}

	public void setResidenceCardExpirationDate(LocalDate residenceCardExpirationDate) {
		this.residenceCardExpirationDate = residenceCardExpirationDate;
	}

	public String getResidenceCardStatus() {
		return residenceCardStatus;
	}

	public void setResidenceCardStatus(String residenceCardStatus) {
		this.residenceCardStatus = residenceCardStatus;
	}

	public String getEmploymentInsuranceNumber() {
		return employmentInsuranceNumber;
	}

	public void setEmploymentInsuranceNumber(String employmentInsuranceNumber) {
		this.employmentInsuranceNumber = employmentInsuranceNumber;
	}

	public String getPensionNumber() {
		return pensionNumber;
	}

	public void setPensionNumber(String pensionNumber) {
		this.pensionNumber = pensionNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

}
