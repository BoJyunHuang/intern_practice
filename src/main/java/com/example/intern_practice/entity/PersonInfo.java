package com.example.intern_practice.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person_info")
public class PersonInfo {
	
	// マイナンバー
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_number")
	private Integer employeeNumber;
	
	//　名前
	@Column(name = "full_name")
	private String fullName;
	
	//　名前（カタカナ）
	@Column(name = "kana_name")
	private String kanaName;
	
	//　名前（ローマ字）
	@Column(name = "romanized_name")
	private String romanizedName;
	
	//　国籍
	@Column(name = "nationality")
	private String nationality;
	
	//　性別
	@Column(name = "gender")
	private String gender;
	
	//　生年月日
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	//　入社日
	@Column(name = "join_date")
	private LocalDate joinDate;
	
	//　退職日
	@Column(name = "departure_date")
	private LocalDate departureDate;
	
	// 削除フラグ
	@Column(name = "deleted_flag")
	private boolean deletedFlag = false;
	
	// コンストラクタ
	public PersonInfo() {
		super();
	}

	public PersonInfo(String fullName, String kanaName, String romanizedName, String nationality, String gender,
			LocalDate birthDate, LocalDate joinDate, LocalDate departureDate) {
		super();
		this.fullName = fullName;
		this.kanaName = kanaName;
		this.romanizedName = romanizedName;
		this.nationality = nationality;
		this.gender = gender;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.departureDate = departureDate;
	}

	// ゲッターとセッター
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getRomanizedName() {
		return romanizedName;
	}

	public void setRomanizedName(String romanizedName) {
		this.romanizedName = romanizedName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public boolean isDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(boolean deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

}
