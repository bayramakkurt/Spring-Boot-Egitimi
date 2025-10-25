package com.HBA.controller;

import com.HBA.dto.DTOAddress;

public interface IAddressController {

	public DTOAddress findAddressByID(Long id);
}
