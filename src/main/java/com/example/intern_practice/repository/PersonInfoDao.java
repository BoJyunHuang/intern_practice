package com.example.intern_practice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.intern_practice.entity.PersonInfo;

@Repository
public interface PersonInfoDao extends JpaRepository<PersonInfo, Integer> {

	/**
	 * 個人情報を更新します。
	 *
	 * @param employeeNumber   マイナンバー
	 * @param fullName         名前
	 * @param kanaName         名前（カタカナ）
	 * @param romanizedName    名前（ローマ字）
	 * @param nationality      国籍
	 * @param gender           性別
	 * @param birthDate        生年月日
	 * @param joinDate         入社日
	 * @param departureDate    退職日
	 * @return 更新された行数（成功時は1、それ以外は0）
	 */
	@Transactional
	@Modifying
	@Query(value = "update person_info set "
			+ "full_name = :fullName, "
			+ "kana_name = :kanaName, "
			+ "romanized_name = :romanizedName,"
			+ "nationality = :nationality, "
			+ "gender = :gender, "
			+ "birth_date = :birthDate, "
			+ "join_date = :joinDate,"
			+ "departure_date = :departureDate "
			+ "where employee_number = :employeeNumber", nativeQuery = true)
	public int updatePersonInfo(
			@Param("employeeNumber") Integer employeeNumber, 
			@Param("fullName") String fullName,
			@Param("kanaName") String kanaName, 
			@Param("romanizedName") String romanizedName,
			@Param("nationality") String nationality, 
			@Param("gender") String gender,
			@Param("birthDate") LocalDate birthDate, 
			@Param("joinDate") LocalDate joinDate,
			@Param("departureDate") LocalDate departureDate);

	/**
	 * 指定された名前に部分一致する個人情報を検索します。
	 *
	 * @param name 名前または名前の一部
	 * @return 名前に部分一致する個人情報のリスト
	 */
	@Query(value = "select * from person_info "
			+ "where full_name like %:name% "
			+ "or kana_name like %:name% "
			+ "or romanized_name like %:name%", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByName(
			@Param("name") String name);

	/**
	 * 指定された国籍に基づいて個人情報を検索します。
	 *
	 * @param nationality 国籍
	 * @return 指定された国籍に該当する個人情報のリスト
	 */
	public List<PersonInfo> findByNationality(String nationality);

	/**
	 * 指定された性別に基づいて個人情報を検索します。
	 *
	 * @param gender 性別
	 * @return 指定された性別に該当する個人情報のリスト
	 */
	public List<PersonInfo> findByGender(String gender);

	/**
	 * 指定された入社日の範囲に基づいて個人情報を検索します。
	 *
	 * @param start 入社日の開始日
	 * @param end 入社日の終了日
	 * @return 指定された入社日範囲に該当する個人情報のリスト
	 */
	@Query(value = "select * from person_info "
			+ "where join_date between :start and :end", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByJoinDate(
			@Param("start") LocalDate start, 
			@Param("end") LocalDate end);

	/**
	 * 指定された退職日の範囲に基づいて個人情報を検索します。
	 *
	 * @param start 退職日の開始日
	 * @param end 退職日の終了日
	 * @return 指定された退職日範囲に該当する個人情報のリスト
	 */
	@Query(value = "select * from person_info "
			+ "where departure_date between :start and :end", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByDepartureDate(
			@Param("start") LocalDate start,
			@Param("end") LocalDate end);

	/**
	 * 指定されたマイナンバーに基づいて、個人情報を削除フラグを立てることにより論理的に削除します。
	 *
	 * @param employeeNumber 削除対象のマイナンバー
	 * @return 削除操作の結果影響を受けた行数
	 */
	@Transactional
	@Modifying
	@Query(value = "update person_info "
			+ "set deleted_flag = true "
			+ "where employee_number = :employeeNumber", nativeQuery = true)
	public int deletePersonInfo(
			@Param("employeeNumber") Integer employeeNumber);
	
}
