package com.HBA.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@EntityScan(basePackages = "com.HBA") //Modelleri görüp veritabanında oluşturması için
@EnableJpaRepositories(basePackages = "com.HBA") //Veritabanı işlemlerini gerçekleştirmek için
@ComponentScan(basePackages = "com.HBA") //Beanlerin oluşması ve görülmesi için.
@EnableScheduling //Scheduled anatasyonlu metodların çalışması için gerekli
@SpringBootApplication
public class ExceptionManagementStarter {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionManagementStarter.class, args);
	}

}
