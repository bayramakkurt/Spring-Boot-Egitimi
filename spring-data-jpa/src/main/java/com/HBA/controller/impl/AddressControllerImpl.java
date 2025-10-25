package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IAddressController;
import com.HBA.dto.DTOAddress;
import com.HBA.services.IAddressService;

@RestController
@RequestMapping("/rest/api/address")
public class AddressControllerImpl implements IAddressController {
	
	@Autowired
	private IAddressService addressService;

	@GetMapping(path = "/list/{id}")
	@Override
	public DTOAddress findAddressByID(@PathVariable(name ="id")  Long id) {
		return addressService.findAddressByID(id);
	}
}
