package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestAuthenticationController;
import com.HBA.controller.RestBaseController;
import com.HBA.controller.RootEntity;
import com.HBA.dto.AuthRequest;
import com.HBA.dto.AuthResponse;
import com.HBA.dto.DTOUser;
import com.HBA.dto.RefreshTokenRequest;
import com.HBA.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController

public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController{

	@Autowired
	private IAuthenticationService authenticationService;
	 
	@PostMapping("/register")
	@Override
	public RootEntity<DTOUser> register(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.register(input));
	}
	
	@PostMapping("/authenticate")
	@Override
	public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest input) {
		return ok(authenticationService.authenticate(input));
	}

	@PostMapping("/refreshToken")
	@Override
	public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest input) {
		return ok(authenticationService.refreshToken(input));
	}

}
