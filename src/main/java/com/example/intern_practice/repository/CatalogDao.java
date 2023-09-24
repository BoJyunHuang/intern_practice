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

	// カタログを挿入する。同じ名前と親が存在しない場合のみ挿入する。
	@Transactional
	@Modifying
	@Query(value = "insert into catalog (name, parent, delete_flag) select :name, :parent, false where not exists ("
			+ "select 1 from catalog where name = :name and parent = :parent)", nativeQuery = true)
	public int insertCatalog(@Param("name") String name, @Param("parent") String parent);

	// カタログを更新する。
	@Transactional
	@Modifying
	@Query(value = "update catalog set name = :name, parent = :parent where catalog_id = :catalogId", nativeQuery = true)
	public int updateCatalog(@Param("catalogId") Integer catalogId, @Param("name") String name,
			@Param("parent") String parent);

	// カタログを削除する。
	@Transactional
	@Modifying
	@Query(value = "update catalog set delete_flag = true where catalog_id = :catalogId", nativeQuery = true)
	public int deleteCatalog(@Param("catalogId") Integer catalogId);

	// カタログリストを削除する。
	@Transactional
	@Modifying
	@Query(value = "update catalog set delete_flag = true where catalog_id in :idList", nativeQuery = true)
	public int deleteMultiCatalog(@Param("idList") List<Integer> idList);

	// 親と削除フラグに基づいてカタログを検索する。
	public List<Catalog> findByParentAndDeleteFlag(String parent, boolean deleteFlag);

	// 指定された名前,親カタログ,削除フラグに一致するカタログを検索する。
	public List<Catalog> findByNameAndParentAndDeleteFlag(String name, String parent, boolean deleteFlag);

}
