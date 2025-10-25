package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.configuration.DataSource;
import com.HBA.configuration.GlobalProperties;

@RestController
@RequestMapping("/rest/api/property")
public class PropertySourceController {

	@Autowired
	private GlobalProperties globalProperties;
	
	@GetMapping(path = "/datasource")
	public DataSource getDataSource() {
		DataSource dataSource = new DataSource();
		dataSource.setUrl(globalProperties.getUrl());
		dataSource.setUsername(globalProperties.getUserName());
		dataSource.setPassword(globalProperties.getPassword());
		return dataSource; 
	}
}
