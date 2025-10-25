package com.HBA.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component //Bean oluşması için
@Data
//@ConfigurationProperties(prefix = "app")
public class GlobalProperties {

	@Value("${spring.datasource.url}") //Property dosyasından veri okumak için
	private String url;
	
	@Value("${spring.datasource.username}")
	private String userName;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	//private List<Server> servers;
}
