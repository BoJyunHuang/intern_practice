package com.example.intern_practice.service.ifs;

import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

public interface LatestNewsService {

	// ニュース一覧を表示する
	public ShowNewsResponse showNews(ShowNewsRequest request);
	
	// ニュースを新規追加する
	public Response addNews(AddNewsRequest request);
	
	// ニュースを編集する
	public Response reviseNews(ReviseNewsRequest request);
	
	// ニュースの状態を変更する(削除)
	public Response changeNewsStatus(ChangeNewsRequest request);
}
