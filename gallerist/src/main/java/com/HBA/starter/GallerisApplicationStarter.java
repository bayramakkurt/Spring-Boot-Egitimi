package com.HBA.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(basePackages = {"com.HBA"})
@EntityScan(basePackages = {"com.HBA"})
@ComponentScan(basePackages = {"com.HBA"})
@SpringBootApplication
public class GallerisApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(GallerisApplicationStarter.class, args);
	}

}
