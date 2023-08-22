package com.example.intern_practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.intern_practice.entity.LatestNews;

@Repository
public interface LatestNewsDao extends JpaRepository<LatestNews, Integer>{

	@Query(value = "select * from latest_news where show = :show", nativeQuery = true)
	public List<LatestNews> searchNews(@Param("show") boolean show);
	
	@Transactional
	@Modifying
	@Query(value = "update latest_news set catalog = :catalog, sub_catalog = :subCatalog, title = :title, "
			+ "content = :content where serial_number = :serialNumber", nativeQuery = true)
	public int updateNews(@Param("serialNumber") Integer serialNumber, @Param("catalog") String catalog, 
			@Param("subCatalog") String subCatalog, @Param("title") String title, @Param("content") String content);
	
	@Transactional
	@Modifying
	@Query(value = "update latest_news set show = :show where serial_number in :newsIdList", nativeQuery = true)
	public int updateStatus(@Param("show") boolean show, @Param("newsList") List<Integer> newsIdList);
	
}