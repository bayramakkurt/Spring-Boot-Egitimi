package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestCarController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOCar;
import com.HBA.dto.DTOCarIU;
import com.HBA.service.ICarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {
	
	@Autowired
	private ICarService carService;

	@PostMapping("/save")
	@Override
	public RootEntity<DTOCar> saveCar(@Valid @RequestBody DTOCarIU dtoCarIU) {
		return RootEntity.ok(carService.saveCar(dtoCarIU));
	}

}
