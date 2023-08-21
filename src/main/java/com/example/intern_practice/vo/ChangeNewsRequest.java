package com.example.intern_practice.vo;

import java.util.List;

public class ChangeNewsRequest {

	private List<Integer> newsIdList;
	private boolean show;
	
	public List<Integer> getNewsIdList() {
		return newsIdList;
	}
	public void setNewsIdList(List<Integer> newsIdList) {
		this.newsIdList = newsIdList;
	}
	public boolean isShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	
}
