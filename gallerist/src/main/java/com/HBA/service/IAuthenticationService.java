package com.HBA.service;

import com.HBA.dto.AuthRequest;
import com.HBA.dto.AuthResponse;
import com.HBA.dto.DTOUser;
import com.HBA.dto.RefreshTokenRequest;

public interface IAuthenticationService {

	public DTOUser register(AuthRequest input);
	
	public AuthResponse authenticate(AuthRequest input);
	
	public AuthResponse refreshToken(RefreshTokenRequest input);
}
