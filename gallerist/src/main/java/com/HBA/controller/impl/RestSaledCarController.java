package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestSaledCarController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOSaledCar;
import com.HBA.dto.DTOSaledCarIU;
import com.HBA.service.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarController extends RestBaseController implements IRestSaledCarController {

	@Autowired
	private ISaledCarService saledCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DTOSaledCar> buyCar(@Valid @RequestBody DTOSaledCarIU dtoSaledCarIU) {
		return RootEntity.ok(saledCarService.buyCar(dtoSaledCarIU));
	}

}
