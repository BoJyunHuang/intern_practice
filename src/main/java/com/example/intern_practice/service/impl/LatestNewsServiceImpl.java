package com.example.intern_practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.intern_practice.constants.RtnCode;
import com.example.intern_practice.entity.LatestNews;
import com.example.intern_practice.repository.LatestNewsDao;
import com.example.intern_practice.service.ifs.LatestNewsService;
import com.example.intern_practice.vo.AddNewsRequest;
import com.example.intern_practice.vo.ChangeNewsRequest;
import com.example.intern_practice.vo.Response;
import com.example.intern_practice.vo.ReviseNewsRequest;
import com.example.intern_practice.vo.ShowNewsRequest;
import com.example.intern_practice.vo.ShowNewsResponse;

@Service
public class LatestNewsServiceImpl implements LatestNewsService {

	@Autowired
	private LatestNewsDao latestNewsDao;

	@Override
	public ShowNewsResponse showNews(ShowNewsRequest request) {
		// 空の入力を防止する
		if (request == null) {
			return new ShowNewsResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}

		// 表示・非表示の引数を設定する
		boolean status = request.isReveal();
		
		// Daoメソッドを実行してニュースのリストを取得する
		List<LatestNews> result = latestNewsDao.searchNews(status);

		// 結果を返す：結果が空の場合、Not_Foundのメッセージを返す。それ以外は成功メッセージとリストを返す
		return CollectionUtils.isEmpty(result) ? new ShowNewsResponse(RtnCode.NOT_FOUND.getMessage())
				: new ShowNewsResponse(RtnCode.SUCCESS.getMessage(), result);
	}

	@Override
	public Response addNews(AddNewsRequest request) {
		// 空の入力を防ぐ
		// リクエスト内の属性を含む
		// 空文字列を含むことはできません
		if (request == null || !StringUtils.hasText(request.getCatalog())
				|| !StringUtils.hasText(request.getSubcatalog()) || !StringUtils.hasText(request.getTitle())
				|| !StringUtils.hasText(request.getContent())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		// リクエスト内の属性を取得し、新しい LatestNews を宣言します
		// Dao メソッドを使用してデータベースに保存します
		String newCatalog = request.getCatalog();
		String newSubcatalog = request.getSubcatalog();
		String newTitle = request.getTitle();
		String newContent = request.getContent();
		LatestNews news = new LatestNews(newCatalog, newSubcatalog, newTitle, newContent);
		latestNewsDao.save(news);

		// 結果を返す：成功メッセージを返す
		return new Response(RtnCode.SUCCESS.getMessage());
	}

	@Override
	public Response reviseNews(ReviseNewsRequest request) {
		// 空の入力を防ぐ
		// リクエスト内の属性を含む
		// 空文字列を含むことはできません
		if (request == null || request.getSerialNumber() == null || !StringUtils.hasText(request.getCatalog())
				|| !StringUtils.hasText(request.getSubcatalog()) || !StringUtils.hasText(request.getTitle())
				|| !StringUtils.hasText(request.getContent())) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}

		// リクエスト内の属性を取得します
		Integer newsId = request.getSerialNumber();
		String newCatalog = request.getCatalog();
		String newSubcatalog = request.getSubcatalog();
		String newTitle = request.getTitle();
		String newContent = request.getContent();

		// Dao メソッドを使用して、新しいデータをデータベースに更新します
		// 結果を返す：更新が成功した場合、成功メッセージを返します。失敗した場合、データが見つからないメッセージを返します
		return latestNewsDao.updateNews(newsId, newCatalog, newSubcatalog, newTitle, newContent) == 1
				? new Response(RtnCode.SUCCESS.getMessage())
				: new Response(RtnCode.NOT_FOUND.getMessage());
	}

	@Override
	public Response changeNewsStatus(ChangeNewsRequest request) {
		// 空の入力を防ぐ
		// リクエスト内の属性を含む
		if (request == null || request.getSerialNumber() == null) {
			return new Response(RtnCode.CANNOT_EMPTY.getMessage());
		}
		
		// リクエスト内の属性を取得します
		Integer id = request.getSerialNumber();
		boolean reveal = request.isReveal();
		
		// Dao メソッドを使用して、データの状態をデータベースに更新します
		// 更新が成功した場合、成功メッセージを返します。失敗した場合、エラーメッセージを返します
		return latestNewsDao.updateStatus(!reveal, id) == 1
				? new Response(RtnCode.SUCCESS.getMessage())
				: new Response(RtnCode.INCORRECT.getMessage());
	}

}
