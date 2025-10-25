package com.HBA.services;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.HBA.config.AppConfig;

public class LoginService {

	public void login() {
		//User listesi gerekiyor
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		UserServices userServices = context.getBean(UserServices.class);
	}
}
