package com.HBA.dto;

import lombok.Data;

@Data
public class DTOAddress extends DTOBase { //Get işlemleri için kullanılacak DTO.
	
	//Burada ID ve CreateTime alanlarını DTOBase üzerinden alıyoruz.

	private String city;
	
	private String district;
	
	private String neighborhood;
	
	private String street;
}
