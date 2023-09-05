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

	@Transactional
	@Modifying
	@Query(value = "insert into news (catalog_id, title, subtitle, tags, content, create_time, publish_time, edit_time, "
			+ "remove_time, creator, editor, remover, views, likes, dislikes, importance, audience_level, delete_flag) "
			+ "values (:catalogId, :title, :subtitle, :tags, :content, :createTime, :publishTime, null, :removeTime, "
			+ ":creator, null, :remover, 0, 0, 0, :importance, :audienceLevel, false)", nativeQuery = true)
	public int insertNews(@Param("catalogId") int catalogId, @Param("title") String title,
			@Param("subtitle") String subtitle, @Param("tags") String tags, @Param("content") String content,
			@Param("createTime") LocalDateTime createTime, @Param("publishTime") LocalDateTime publishTime,
			@Param("removeTime") LocalDateTime removeTime, @Param("creator") String creator,
			@Param("remover") String remover, @Param("importance") int importance,
			@Param("audienceLevel") int audienceLevel);

	@Transactional
	@Modifying
	@Query(value = "update news set catalogId = :catalogId, title = :title, subtitle = :subtitle, tags = :tags, "
			+ "content = :content, publish_time = :publishTime, edit_time = :editTime, remove_time = :removeTime, "
			+ "editor = :editor, importance = :importance, audience_level = :audienceLevel "
			+ "where news_id = :newsId", nativeQuery = true)
	public int updateNews(@Param("newsId") Integer newsId, @Param("catalogId") int catalogId,
			@Param("title") String title, @Param("subtitle") String subtitle, @Param("tags") String tags,
			@Param("content") String content, @Param("publishTime") LocalDateTime publishTime,
			@Param("editTime") LocalDateTime editTime, @Param("removeTime") LocalDateTime removeTime,
			@Param("editor") String editor, @Param("importance") int importance,
			@Param("audienceLevel") int audienceLevel);

	@Transactional
	@Modifying
	@Query(value = "update news set views = views + 1 where news_id = :newsId", nativeQuery = true)
	public int plusView(@Param("newsId") Integer newsId);

	@Transactional
	@Modifying
	@Query(value = "update news set likes = likes + 1 where news_id = :newsId", nativeQuery = true)
	public int plusLike(@Param("newsId") Integer newsId);

	@Transactional
	@Modifying
	@Query(value = "update news set dislikes = dislikes + 1 where news_id = :newsId", nativeQuery = true)
	public int plusDislike(@Param("newsId") Integer newsId);

	@Transactional
	@Modifying
	@Query(value = "update news set remove_time = :removeTime, remover = :remover, delete_flag = true "
			+ "where news_id = :newsId", nativeQuery = true)
	public int deleteNews(@Param("newsId") Integer newsId, @Param("removeTime") LocalDateTime removeTime,
			@Param("remover") String remover);

	@Transactional
	@Modifying
	@Query(value = "update news set delete_flag = true where remove_time < :currentTime", nativeQuery = true)
	public int checkRemoveTime(@Param("currentTime") LocalDateTime currentTime);

	public List<News> findByCatalogId(int catalogId);

	@Query(value = "select * from news where title regexp :word or subtitle regexp :word or tags regexp :word or "
			+ "content regexp :word", nativeQuery = true)
	public List<News> findByWord(@Param("word") String word);
	
}
