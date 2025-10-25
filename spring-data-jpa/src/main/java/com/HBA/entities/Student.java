package com.HBA.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Varlık olduğu belirtilir sayesinde veritabanında tablo açılır.
@Table(name = "Student") //Veritabanında oluşacak tablo adını belirtir.
@Data //Getter ve Setter birleşik yazımıdır
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	@Id //Primary key olduğunu belirtiriz.
	@Column(name = "id") //Tablodaki sütun adını belirtiriz
	@GeneratedValue(strategy = GenerationType.IDENTITY) //ID değerinin her eklenen elemanda 1 er 1er artacapını belirtiriz.
	private Integer id;
	
	@Column(name = "first_name", nullable = false, length = 40) //Sütun adı belirtilir, boş geçilemeyeği ve max 40 karakter uzunluk kısıtlaması koyulur.
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "birth_of_date", nullable = true)
	private Date birthOfDate;
	
	@ManyToMany //Çoka çok ilişki oluşturur ve ara tablo oluşur.
	@JoinTable(name = "student_course",     //Oluşacak ara tablonun parametrelerini elle girmek için kullanılır. Burası Tablonun adını belirler.
	joinColumns = @JoinColumn(name="student_id"), //Burası ilk ilişkideki foreign key kısmının sütun adını belirler.
	inverseJoinColumns = @JoinColumn(name="course_id")) //Burası ikinci ilişkideki foreign key kısmının sütun adını belirler.
	private List<Course> courses;
}
