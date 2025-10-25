package com.HBA.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.HBA.jwt.AuthEntryPoint;
import com.HBA.jwt.JwtAuthenticatonFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	public static final String AUTHENTİCATE = "/authenticate";
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "refreshToken"; 
	public static final String[] SWAGGER_PATHS = {
			"/swagger-ui/**",
			"/v3/api-docs/**",
			"/swagger-ui.html"
	};
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticatonFilter jwtAuthenticatonFilter;
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeHttpRequests(request-> 
		request.requestMatchers(AUTHENTİCATE, REGISTER, REFRESH_TOKEN) //Bu path için ayrı işlemler uygulanır.
		.permitAll() //Bu path gelen istekler Yetkilendirme işlemine tabi tutulmaz direkt controller gönderilir erişime izin verilir.
		.requestMatchers(SWAGGER_PATHS).permitAll()
		.anyRequest() //Bunun harici diğer bütün isteklerde
		.authenticated()) //Yetkilendirme işlemi gerçekleşmesi gerektirğini belirtir.
		.sessionManagement(session-> 
		session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticatonFilter, UsernamePasswordAuthenticationFilter.class)
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and(); //Yetksiz girişte 403 hata kodu yerine 401 hata kodu vermesin diye ekleniyor.
		
		
		return http.build();
	}
	
}
