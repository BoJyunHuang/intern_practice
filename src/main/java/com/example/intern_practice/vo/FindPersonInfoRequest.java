package com.example.intern_practice.vo;

import java.time.LocalDate;

public class FindPersonInfoRequest {

	private String name;
	private String nationality;
	private String gender;
	private LocalDate start;
	private LocalDate end;
	private boolean isDeparture;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnd() {
		return end;
	}
	public void setEnd(LocalDate end) {
		this.end = end;
	}
	public boolean isDeparture() {
		return isDeparture;
	}
	public void setDeparture(boolean isDeparture) {
		this.isDeparture = isDeparture;
	}
	
}
