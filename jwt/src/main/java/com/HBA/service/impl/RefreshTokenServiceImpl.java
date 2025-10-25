package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.jwt.AuthResponse;
import com.HBA.jwt.JwtService;
import com.HBA.jwt.RefreshTokenRequest;
import com.HBA.model.RefreshToken;
import com.HBA.model.User;
import com.HBA.repository.RefreshTokenRepository;
import com.HBA.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	public boolean isRefreshTokenExpired(Date expireDate) {
		return new Date().before(expireDate);
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 4 ));
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional =  refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if (optional.isEmpty()) {
			System.out.println("RefreshToken geçersiz: " + request.getRefreshToken());
		}
		RefreshToken refreshToken = optional.get();
		if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
			System.out.println("RefreshToken expire olmuştur: " + request.getRefreshToken());
		}
		
		String accessToken = jwtService.generateToken(refreshToken.getUser());
		
		RefreshToken savedRefreshToken =  refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
