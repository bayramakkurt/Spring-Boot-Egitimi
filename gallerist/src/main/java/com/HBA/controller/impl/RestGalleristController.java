package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestGalleristController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOGallerist;
import com.HBA.dto.DTOGalleristIU;
import com.HBA.service.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController {

	
	@Autowired
	private IGalleristService galleristService;

	@PostMapping("/save")
	@Override
	public RootEntity<DTOGallerist> saveGallerist(@Valid @RequestBody DTOGalleristIU dtoGalleristIU) {
		return RootEntity.ok(galleristService.saveGallerist(dtoGalleristIU));
	}
}
