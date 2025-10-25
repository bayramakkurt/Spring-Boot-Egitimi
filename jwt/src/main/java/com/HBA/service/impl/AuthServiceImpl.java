package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOUser;
import com.HBA.jwt.AuthRequest;
import com.HBA.jwt.AuthResponse;
import com.HBA.jwt.JwtService;
import com.HBA.model.RefreshToken;
import com.HBA.model.User;
import com.HBA.repository.RefreshTokenRepository;
import com.HBA.repository.UserRepository;
import com.HBA.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 4 ));
		refreshToken.setUser(user);
		return refreshToken;
	}
	
	@Override
	public DTOUser register(AuthRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User savedUser = userRepository.save(user);
		
		DTOUser dtoUser = new DTOUser();
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			authenticationProvider.authenticate(auth);
			
			Optional<User> optional = userRepository.findByUsername(request.getUsername());
			String accessToken = jwtService.generateToken(optional.get());
			
			RefreshToken refreshToken = createRefreshToken(optional.get());
			refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accessToken, refreshToken.getRefreshToken());
		} catch (Exception e) {
			System.out.println("Kullanıcı adı veya şifre yanlış!" + e.getMessage());
		}
		return null;
	}

}
