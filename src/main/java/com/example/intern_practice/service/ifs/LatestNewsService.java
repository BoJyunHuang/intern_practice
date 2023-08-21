package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

public interface LatestNewsService {

	// 顯示消息列表
	public ShowNewsResponse showNews(ShowNewsRequest request);
	
	// 新增最新消息
	public Response addNews(AddNewsRequest request);
	
	// 編輯消息
	public Response reviseNews(ReviseNewsRequest request);
	
	// 變更消息狀態
	public Response changeNewsStatus(ChangeNewsRequest request);
}
