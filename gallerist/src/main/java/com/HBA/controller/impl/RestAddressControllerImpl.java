package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestAddressController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOAdressIU;
import com.HBA.service.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl implements IRestAddressController {

	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DTOAddress> savedAddress(@Valid @RequestBody DTOAdressIU dtoAdressIU) {
		return RootEntity.ok(addressService.saveAddress(dtoAdressIU));
	}

}
