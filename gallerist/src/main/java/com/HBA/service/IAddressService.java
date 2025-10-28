package com.HBA.service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOAdressIU;

public interface IAddressService {

	public DTOAddress saveAddress(DTOAdressIU dtoAdressIU);
}
