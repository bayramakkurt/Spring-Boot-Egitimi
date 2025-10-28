package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestGallerisCarController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOGalleristCar;
import com.HBA.dto.DTOGalleristCarIU;
import com.HBA.model.GalleristCar;
import com.HBA.repository.GalleristCarRepository;
import com.HBA.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGallerisCarController {
	
	@Autowired
	private IGalleristCarService galleristCarService;
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DTOGalleristCar> saveGalleristCar(@Valid @RequestBody DTOGalleristCarIU dtoGalleristCarIU) {
		return RootEntity.ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}
