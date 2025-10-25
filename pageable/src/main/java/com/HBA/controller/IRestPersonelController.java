package com.HBA.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.HBA.dto.DTOPersonel;
import com.HBA.model.Personel;
import com.HBA.utils.RestPageableEntity;
import com.HBA.utils.RestPageableRequest;
import com.HBA.utils.RestRootEntity;

public interface IRestPersonelController {

	//public Page<Personel> findAllPageable(int pageNumber, int pageSize);  Eski hali böyleydi ModelAttribute anatosyonlu haline çevirdik
	
	public RestRootEntity<RestPageableEntity<DTOPersonel>> findAllPageable(RestPageableRequest pageableRequest);
	
	
}
