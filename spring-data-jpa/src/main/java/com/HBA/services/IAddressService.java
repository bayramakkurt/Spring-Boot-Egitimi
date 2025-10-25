package com.HBA.services;

import com.HBA.dto.DTOAddress;

public interface IAddressService {
	
	public DTOAddress findAddressByID(Long id);

}
