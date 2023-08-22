package com.example.intern_practice.vo;

import java.util.List;

public class ChangeNewsRequest {

	private List<Integer> newsIdList;
	private boolean reveal;
	
	public List<Integer> getNewsIdList() {
		return newsIdList;
	}
	public void setNewsIdList(List<Integer> newsIdList) {
		this.newsIdList = newsIdList;
	}
	public boolean isReveal() {
		return reveal;
	}
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}

}
