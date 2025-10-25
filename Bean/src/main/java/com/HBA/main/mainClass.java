package com.HBA.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.HBA.config.AppConfig;
import com.HBA.model.User;
import com.HBA.services.LoginService;
import com.HBA.services.UserServices;

public class mainClass {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new  AnnotationConfigApplicationContext(AppConfig.class);
		
		UserServices userService = context.getBean(UserServices.class);
		
		for (User user : userService.getUserList()) {
			System.out.println(user);
			
		}
		
		LoginService loginService = new LoginService();
		loginService.login();
	}
}
