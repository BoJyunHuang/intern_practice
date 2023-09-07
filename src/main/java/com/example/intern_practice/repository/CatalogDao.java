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
	@Query(value = "insert into catalog (name, parent, news_amount, delete_flag) "
			+ "select :name, :parent, 0, false where not exists ("
			+ "select 1 from catalog where name = :name and parent = :parent)", nativeQuery = true)
	public int insertCatalog(@Param("name") String name, @Param("parent") String parent);

	@Transactional
	@Modifying
	@Query(value = "update catalog set name = :name, parent = :parent "
			+ "where catalog_id = :catalogId and news_amount = 0", nativeQuery = true)
	public int updateCatalog(@Param("catalogId") Integer catalogId, @Param("name") String name,
			@Param("parent") String parent);

	@Transactional
	@Modifying
	@Query(value = "update catalog set news_amount = news_amount + 1 "
			+ "where catalog_id in :idList", nativeQuery = true)
	public int plusNewsAmount(@Param("idList") List<Integer> idList);

	@Transactional
	@Modifying
	@Query(value = "update catalog set news_amount = news_amount - 1 "
			+ "where catalog_id in :idList", nativeQuery = true)
	public int minusNewsAmount(@Param("idList") List<Integer> idList);

	@Transactional
	@Modifying
	@Query(value = "update catalog set delete_flag = true "
			+ "where catalog_id in :idList and news_amount = 0", nativeQuery = true)
	public int deleteCatalog(@Param("idList") List<Integer> idList);

}
