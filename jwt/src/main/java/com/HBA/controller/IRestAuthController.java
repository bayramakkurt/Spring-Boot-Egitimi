package com.HBA.controller;

import com.HBA.dto.DTOUser;
import com.HBA.jwt.AuthRequest;
import com.HBA.jwt.AuthResponse;
import com.HBA.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DTOUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);

	public AuthResponse refreshToken(RefreshTokenRequest request);
}
