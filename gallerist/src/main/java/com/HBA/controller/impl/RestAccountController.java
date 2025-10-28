package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestAccountController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.DTOAccount;
import com.HBA.dto.DTOAccountIU;
import com.HBA.service.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountController implements IRestAccountController{
	
	@Autowired
	private IAccountService accountService;

	@PostMapping("/save")
	@Override
	public RootEntity<DTOAccount> saveAccount(@Valid @RequestBody DTOAccountIU dtoAccountIU) {
		return RootEntity.ok(accountService.saveAccount(dtoAccountIU));
	}

}
