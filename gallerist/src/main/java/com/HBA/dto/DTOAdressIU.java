package com.HBA.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DTOAdressIU { //Insert - Update işlemleri sırasında kullanılacak DTO. 

	//Burada ID ve CreateTime alanları otomatik oluştuğu için vermeye gerek yok.
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String district;
	
	@NotEmpty
	private String neighborhood;
	
	@NotEmpty
	private String street;
}
