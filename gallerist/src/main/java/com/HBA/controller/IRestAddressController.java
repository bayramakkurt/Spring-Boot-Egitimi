package com.HBA.controller;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOAdressIU;

public interface IRestAddressController {

	public RootEntity<DTOAddress> savedAddress(DTOAdressIU dtoAdressIU);
}
