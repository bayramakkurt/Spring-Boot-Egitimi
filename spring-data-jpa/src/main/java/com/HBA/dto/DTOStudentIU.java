package com.HBA.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOStudentIU {	
	//Blank: DTOStudent yeniStudent; olarak yazılırsa değişken Blank olarak atanır.
	//Null: DTOStudent yeniStudent=null; olarak yazılırsa değişken Null olarak atanır.
	//Empty: DTOStudent yeniStudent=""; olarak yazılırsa değişjen Empty olarak atanır.
	
	@NotEmpty(message = "FirstName alanı boş bırakılamaz.") //Değişken alanı Null veya Empty olamaz.
	@Min(value = 3) //Karakter sayısı min 3 olmalı
	@Max(value = 25) //Karakter sayısı max 25 olmalı
	private String firstName;
	
	@Size(min = 2, max = 30) //Karakter sayısı 2-30 arasında olmalı
	private String lastName;
	
	private Date birthOfDate;
	
	@Email(message = "Email formatında adres girilmelidir.")
	private String email;
	
	@Size(min = 11, max = 11, message = "TCKN alanı 11 karakter olmalıdır.")
	@NotEmpty(message = "TCKN alanı boş bırakılamaz.")
	private String tckn;
}
