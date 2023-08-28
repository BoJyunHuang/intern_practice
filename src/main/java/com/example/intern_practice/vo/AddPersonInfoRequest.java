package com.example.intern_practice.vo;

import java.time.LocalDate;

public class AddPersonInfoRequest {

	private String fullName;
	private String kanaName;
	private String romanizedName;
	private String nationality;
	private String gender;
	private LocalDate birthDate;
	private LocalDate joinDate;
	
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
	
}
