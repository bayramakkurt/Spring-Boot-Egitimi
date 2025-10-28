package com.HBA.controller;

import com.HBA.dto.AuthRequest;
import com.HBA.dto.AuthResponse;
import com.HBA.dto.DTOUser;
import com.HBA.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

	public RootEntity<DTOUser> register(AuthRequest input);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest input);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);
}
