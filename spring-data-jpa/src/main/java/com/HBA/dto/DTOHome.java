package com.HBA.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOHome {

	
	private Long id;
	
	private BigDecimal price;
	
	private List<DTORoom> rooms = new ArrayList<>();
}
