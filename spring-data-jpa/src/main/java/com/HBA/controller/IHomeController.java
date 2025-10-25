package com.HBA.controller;

import com.HBA.dto.DTOHome;

public interface IHomeController {

	public DTOHome findHomeByID(Long id);
}
