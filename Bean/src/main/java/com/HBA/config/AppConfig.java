package com.HBA.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.HBA.model.User;
import com.HBA.services.UserServices;

@Configuration
public class AppConfig {
	
	@Bean
	public UserServices userService() {
		UserServices userService = new UserServices();
		
		List<User> userList = new ArrayList<>();
		
		userList.add(new User("Bayram"));
		userList.add(new User("Harun"));
		
		userService.setUserList(userList);
		
		return userService;
	}
}
