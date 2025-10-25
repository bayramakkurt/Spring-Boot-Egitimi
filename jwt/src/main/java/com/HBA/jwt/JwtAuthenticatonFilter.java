package com.HBA.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticatonFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header;
		String token;
		String username;
		
		header = request.getHeader("Authorization");
		if (header == null) { //Eğer kullanıcı istekte Auth Token göndermemişse direkt olarak boş cevap verilsin.
			filterChain.doFilter(request, response);
			return;
		}
		
		token = header.substring(7); //Gelen tokeni almak için "Bearer TOKEN" kısmında Bearer kısmını silip sadece Token bıraktık
		
		try {
			username = jwtService.getUsernameByToken(token);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username); //Username DB de varmı yokmu onu kontrol eder.Dönüş olarak giriş yapan kullanıcı bilgilerini döner.
				if (userDetails != null && jwtService.isTokenExpired(token)) { //Kullanıcı DB de varsa ve Token süresi devam ediyorsa
					//Burada artık kullanıcı girişi oldu Controller katmanına erişebilir.
					UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
					
					authentication.setDetails(userDetails);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
				
			}
		} catch (ExpiredJwtException e) {
			System.out.println("Token süresi dolmuştur: " + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Genel bir hata oluştu:" + e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

}
