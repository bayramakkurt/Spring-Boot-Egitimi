package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IRestAuthController;
import com.HBA.dto.DTOUser;
import com.HBA.jwt.AuthRequest;
import com.HBA.jwt.AuthResponse;
import com.HBA.jwt.RefreshTokenRequest;
import com.HBA.service.IAuthService;
import com.HBA.service.IRefreshTokenService;

import jakarta.validation.Valid;

@RestController
public class RestAuthControllerImpl implements IRestAuthController{

	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IRefreshTokenService refreshTokenService;
	
	@PostMapping(path = "/register")
	@Override
	public DTOUser register(@Valid @RequestBody AuthRequest request) {
		return authService.register(request);
	}
	
	@PostMapping(path = "/authenticate")
	@Override
	public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
		return authService.authenticate(request);
	}

	@PostMapping(path = "/refreshToken")
	@Override
	public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
		return refreshTokenService.refreshToken(request);
	}

}
