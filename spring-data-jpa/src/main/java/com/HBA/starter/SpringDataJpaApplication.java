package com.HBA.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.HBA.configuration.GlobalProperties;

@EnableJpaRepositories(basePackages = {"com.HBA"}) //Repositoryleri taramak için kullanılır.
@ComponentScan(basePackages = {"com.HBA"}) //Beanleri taramak için kullanılır
@EntityScan(basePackages = {"com.HBA"}) //Modelleri taramak için kullanılır.
@SpringBootApplication
@PropertySource(value = "classpath:application.properties") //Application.properties dosyası adı değişirse burdaki ad üzerinden okunur.
//@EnableConfigurationProperties(value = GlobalProperties.class) //ConfigurationProperties aktif hale getirir.
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
