package com.HBA.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HBA.controller.IHomeController;
import com.HBA.dto.DTOHome;
import com.HBA.services.IHomeService;

@RestController
@RequestMapping("/rest/api/home")
public class HomeControllerImpl implements IHomeController {

	@Autowired
	private IHomeService homeService;

	@GetMapping(path = "/{id}")
	@Override
	public DTOHome findHomeByID(@PathVariable(name = "id") Long id) {
		return homeService.findHomeByID(id);
	}
	
	
}
