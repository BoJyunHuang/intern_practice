package com.example.intern_practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.intern_practice.entity.Catalog;

@Repository
public interface CatalogDao extends JpaRepository<Catalog, Integer> {

	@Transactional
	@Modifying
	@Query(value = "insert into catalog (catalog, subcatalog, news_amount, delete_flag) "
			+ "select :catalog, :subcatalog, 0, false where not exists ("
			+ "select 1 from catalog where catalog = :catalog and subcatalog = :subcatalog)", nativeQuery = true)
	public int insertCatalog(@Param("catalog") String catalog, @Param("subcatalog") String subcatalog);

	@Transactional
	@Modifying
	@Query(value = "update catalog set catalog = :catalog, subcatalog = :subcatalog "
			+ "where catalog_id = :catalogId", nativeQuery = true)
	public int updateCatalog(@Param("catalogId") Integer catalogId, @Param("catalog") String catalog,
			@Param("subcatalog") String subcatalog);
	
	@Transactional
	@Modifying
	@Query(value = "update catalog set news_amount = news_amount + 1 "
			+ "where catalog_id = :catalogId", nativeQuery = true)
	public int plusNewsAmount(@Param("catalogId") Integer catalogId);
	
	@Transactional
	@Modifying
	@Query(value = "update catalog set news_amount = news_amount - 1 "
			+ "where catalog_id = :catalogId", nativeQuery = true)
	public int minusNewsAmount(@Param("catalogId") Integer catalogId);

	@Transactional
	@Modifying
	@Query(value = "update catalog set delete_flag = true "
			+ "where catalog_id in :idList and news_amount = 0", nativeQuery = true)
	public int deleteCatalog(@Param("idList") List<Integer> idList);
		
}
