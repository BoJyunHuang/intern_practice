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

	// データベースから、表示されたり非表示になったりするデータを取得する
	@Query(value = "select * from latest_news where reveal = :reveal", nativeQuery = true)
	public List<LatestNews> searchNews(@Param("reveal") boolean reveal);
	
	// キー値に基づいて、データのタイトル、カタログ、サブカタログ、内容の4つの情報を更新する
	@Transactional
	@Modifying
	@Query(value = "update latest_news set catalog = :catalog, subcatalog = :subcatalog, title = :title, "
			+ "content = :content where serial_number = :serialNumber", nativeQuery = true)
	public int updateNews(@Param("serialNumber") Integer serialNumber, @Param("catalog") String catalog, 
			@Param("subcatalog") String subcatalog, @Param("title") String title, @Param("content") String content);
	
	// キー値に基づいて、表示の情報を更新する
	@Transactional
	@Modifying
	@Query(value = "update latest_news set reveal = :reveal where serial_number = :serialNumber", nativeQuery = true)
	public int updateStatus(@Param("reveal") boolean reveal, @Param("serialNumber") Integer serialNumber);
	
}
