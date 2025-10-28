package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestCustomerController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOCustomer;
import com.HBA.dto.DTOCustomerIU;
import com.HBA.service.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerController extends RestBaseController implements IRestCustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DTOCustomer> saveCustomer(@Valid @RequestBody DTOCustomerIU dtoCustomerIU) {
		return RootEntity.ok(customerService.saveCustomer(dtoCustomerIU));
	}

}
