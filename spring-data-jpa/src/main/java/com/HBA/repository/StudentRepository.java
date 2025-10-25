package com.HBA.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HBA.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> { //JpaRepository Interface extends edilir ve CRUD işlemleri kolayca yapılır.
																			 //Parametre olarak işlem yapılacak Model ve Primary Key veri türü girilir.
	
	
	//Burada JpaRepository içerisinde bulunmayan hazır SQL sorgularımızı kendimiz elle yazarız.
	//HQL: Tablo adı olarak Entity ismi ve sütun adı olarak Değişken isimleri kullanılır.nativeQuery parametresi false olmalı
	//SQL: Tablo adı ve tablo içindeki sütunlar kullanılarak yazılır. nativeQuery parametresi True olmalı
	
	//HQL
	@Query(value = "from Student", nativeQuery = false) //Buradaki sorgular HQL dilinde yazılır yani Hibernate formatında tablo adı olarak Entity ismi sütun adı olarak Field kısımları yazılır.
	List<Student> findAllStudents();
	
	//SQL
	@Query(value = "select * from student.student", nativeQuery = true) //Önce schema adı sonra . sonrada tablo adı gelir.
	List<Student> findAllStudentsNative();
	
	@Query(value = "from Student s where s.id = :studentId")
	Optional<Student> findStudentById(Integer studentId);
	
	
}
