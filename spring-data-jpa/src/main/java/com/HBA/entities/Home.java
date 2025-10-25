package com.HBA.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "home")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@OneToMany //Burada Home, Room tabloları harici DB de home_room adında ara tablo oluşur ve PK bilgileri orada saklanır.
	private List<Room> room; //Birden fazla room ile ilişki olacağı için List olarak alınır.
	
	
	//OneToMany ilişkide yeni bir ara tablo oluşur.
}
