package com.HBA.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne //Customer ile Address 1-1 ilişkilendirilir. Foreign key olarak kullanılır. ve ilişkinin sahibi olduğu için ilişkiyi yazmak yeterli.
	private Address address;
	
	//OneToOne ilişkide ilişki sahibinde yeni bir kolon açılır tablo oluşmaz.
}
