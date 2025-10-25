package com.HBA.service;

import com.HBA.dto.DTOUser;
import com.HBA.jwt.AuthRequest;
import com.HBA.jwt.AuthResponse;

public interface IAuthService {

	public DTOUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
}
