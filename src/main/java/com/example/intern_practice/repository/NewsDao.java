package com.example.intern_practice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.intern_practice.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer> {

	// ニュースを挿入する。
	@Transactional
	@Modifying
	@Query(value = "insert into news (catalog_id, title, subtitle, tags, content, create_time, publish_time, edit_time, "
			+ "expiration_time, remove_time, creator, editor, remover, views, likes, dislikes, importance, audience_level, delete_flag) "
			+ "values (:catalogId, :title, :subtitle, :tags, :content, :createTime, :publishTime, null, :expirationTime, null, "
			+ ":creator, null, null, 0, 0, 0, :importance, :audienceLevel, false)", nativeQuery = true)
	public int insertNews(@Param("catalogId") Integer catalogId, @Param("title") String title,
			@Param("subtitle") String subtitle, @Param("tags") String tags, @Param("content") String content,
			@Param("createTime") LocalDateTime createTime, @Param("publishTime") LocalDateTime publishTime,
			@Param("expirationTime") LocalDateTime expirationTime, @Param("creator") String creator,
			@Param("importance") int importance, @Param("audienceLevel") int audienceLevel);

	// ニュースを更新する。
	@Transactional
	@Modifying
	@Query(value = "update news set catalog_id = :catalogId, title = :title, subtitle = :subtitle, tags = :tags, content = :content, "
			+ "publish_time = :publishTime, edit_time = :editTime, expiration_time = :expirationTime, editor = :editor, "
			+ "importance = :importance, audience_level = :audienceLevel where news_id = :newsId", nativeQuery = true)
	public int updateNews(@Param("newsId") Integer newsId, @Param("catalogId") Integer catalogId,
			@Param("title") String title, @Param("subtitle") String subtitle, @Param("tags") String tags,
			@Param("content") String content, @Param("publishTime") LocalDateTime publishTime,
			@Param("editTime") LocalDateTime editTime, @Param("expirationTime") LocalDateTime expirationTime,
			@Param("editor") String editor, @Param("importance") int importance,
			@Param("audienceLevel") int audienceLevel);

	// ニュースの閲覧数を増やす。
	@Transactional
	@Modifying
	@Query(value = "update news set views = views + 1 where news_id = :newsId", nativeQuery = true)
	public int plusView(@Param("newsId") Integer newsId);

	// ニュースを削除する。
	@Transactional
	@Modifying
	@Query(value = "update news set remove_time = :removeTime, remover = :remover, delete_flag = true "
			+ "where news_id = :newsId", nativeQuery = true)
	public int deleteNews(@Param("newsId") Integer newsId, @Param("removeTime") LocalDateTime removeTime,
			@Param("remover") String remover);

	// ニュースリストを削除する。
	@Transactional
	@Modifying
	@Query(value = "update news set remove_time = :removeTime, remover = :remover, delete_flag = true "
			+ "where news_id in :idList", nativeQuery = true)
	public int deleteMultiNews(@Param("idList") List<Integer> idList, @Param("removeTime") LocalDateTime removeTime,
			@Param("remover") String remover);

	// 発行日時の降順ですべてのニュースを取得する。
	public List<News> findAllByOrderByPublishTimeDesc();

	// 指定した開始時間以降のニュースを、発行日時の降順で検索する。
	public List<News> findByPublishTimeAfterOrderByPublishTimeDesc(LocalDateTime startTime);

	// 指定した終了時間以前のニュースを、発行日時の降順で検索する。
	public List<News> findByExpirationTimeBeforeOrderByPublishTimeDesc(LocalDateTime endTime);

	// 指定した開始時間と終了時間の間にあるニュースを、発行日時の降順で検索する。
	public List<News> findByPublishTimeAfterAndExpirationTimeBeforeOrderByPublishTimeDesc(LocalDateTime startTime,
			LocalDateTime endTime);
}
