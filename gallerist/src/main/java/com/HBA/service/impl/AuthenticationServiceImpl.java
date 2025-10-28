package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import com.HBA.dto.AuthRequest;
import com.HBA.dto.AuthResponse;
import com.HBA.dto.DTOUser;
import com.HBA.dto.RefreshTokenRequest;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.jwt.JWTService;
import com.HBA.model.RefreshToken;
import com.HBA.model.User;
import com.HBA.repository.RefreshTokenRepository;
import com.HBA.repository.UserRepository;
import com.HBA.service.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final SecurityFilterChain filterChain;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

    AuthenticationServiceImpl(SecurityFilterChain filterChain) {
        this.filterChain = filterChain;
    }
	
	private User createUser(AuthRequest input) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(input.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
		
		return user;
	}
	
	@Override
	public DTOUser register(AuthRequest input) {
		DTOUser dtoUser = new DTOUser();
		
		User savedUser = userRepository.save(createUser(input));
		
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		return refreshToken;
	}

	@Override
	public AuthResponse authenticate(AuthRequest input) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword());
			
			authenticationProvider.authenticate(authenticationToken); //Giriş başarılıysa devam eder değilse exception düşer.
			
			Optional<User> optionalUser = userRepository.findByUsername(input.getUsername());
			String accessToken = jwtService.generateToken(optionalUser.get());
			RefreshToken refreshToken = createRefreshToken(optionalUser.get());
			RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.USERNAME_OR_PASSWORD_INVALID));
		}
	}
	
	public boolean isValidRefreshToken(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest input) {
		 Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());
		 if (optionalRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(input.getRefreshToken(), MessageType.REFRESH_TOKEN_NOT_FOUND));
		}
		if (!isValidRefreshToken(optionalRefreshToken.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(input.getRefreshToken(), MessageType.REFRESH_TOKEN_IS_EXPIRED));
		}
		User user = optionalRefreshToken.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken savedRefreshToken =  refreshTokenRepository.save(createRefreshToken(user)); 
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	} 

}
