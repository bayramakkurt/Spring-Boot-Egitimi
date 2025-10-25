package com.HBA.service;

import com.HBA.jwt.AuthResponse;
import com.HBA.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

	public AuthResponse refreshToken(RefreshTokenRequest request);
}
