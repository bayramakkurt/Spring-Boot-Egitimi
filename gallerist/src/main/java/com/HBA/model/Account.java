package com.HBA.model;

import java.math.BigDecimal;

import com.HBA.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "iban")
	private String iban;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "currency_type")
	@Enumerated(EnumType.STRING) //Burada String seçilirse parasal değer olarak veritabanına işlenir.Ordinal seçilirse veritabanına 0-1 olarak yansır.
	private CurrencyType currencyType;
}
