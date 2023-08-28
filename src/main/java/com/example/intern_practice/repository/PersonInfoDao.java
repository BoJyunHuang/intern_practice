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

	// 個人情報の挿入
	@Transactional
	@Modifying
	@Query(value = "insert into person_info (full_name, kana_name, romanized_name, nationality, gender, birth_date,"
			+ "join_date, deleted_flag) values (fullName, kanaName, romanizedName, nationality, gender, birthDate,"
			+ "joinDate, false)", nativeQuery = true)
	public int insertPersonInfo(@Param("fullName") String fullName, @Param("kanaName") String kanaName,
			@Param("romanizedName") String romanizedName, @Param("nationality") String nationality,
			@Param("gender") String gender, @Param("birthDate") LocalDate birthDate,
			@Param("joinDate") LocalDate joinDate);

	// 個人情報の更新
	@Transactional
	@Modifying
	@Query(value = "update person_info set full_name = :fullName, kana_name = :kanaName, romanized_name = :romanizedName,"
			+ "nationality = :nationality, gender = :gender, birth_date = :birthDate, join_date = :joinDate,"
			+ "dparture_date = :dpartureDate where employee_number = :employeeNumber", nativeQuery = true)
	public int updatePersonInfo(@Param("employeeNumber") Integer employeeNumber, @Param("fullName") String fullName,
			@Param("kanaName") String kanaName, @Param("romanizedName") String romanizedName,
			@Param("nationality") String nationality, @Param("gender") String gender,
			@Param("birthDate") LocalDate birthDate, @Param("joinDate") LocalDate joinDate,
			@Param("dpartureDate") LocalDate dpartureDate);

	// 全ての個人情報を検索
	public List<PersonInfo> findAll();

	// 名前による個人情報の検索
	@Query(value = "select * from person_info where full_name regexp :name or kana_name regexp :name or romanized_name "
			+ "regexp :name", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByName(@Param("name") String name);

	// 国籍による個人情報の検索
	public List<PersonInfo> findByNationality(String nationality);

	// 性別による個人情報の検索
	public List<PersonInfo> findByGender(String gender);

	// 入社日による個人情報の検索
	@Query(value = "select * from person_info where join_date between :start and :end", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByJoinDate(@Param("start") LocalDate start, @Param("end") LocalDate end);

	// 退職日による個人情報の検索
	@Query(value = "select * from person_info where departure_date between :start and :end", nativeQuery = true)
	public List<PersonInfo> searchPersonInfoByDepartureDate(@Param("start") LocalDate start,
			@Param("end") LocalDate end);

	// 個人情報の削除（削除フラグを立てる）
	@Transactional
	@Modifying
	@Query(value = "update person_info set deleted_flag = true where employee_number = :employeeNumber", nativeQuery = true)
	public int deletePersonInfo(@Param("employeeNumber") Integer employeeNumber);
}
