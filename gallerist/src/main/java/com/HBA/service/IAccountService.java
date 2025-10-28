package com.HBA.service;

import com.HBA.dto.DTOAccount;
import com.HBA.dto.DTOAccountIU;

public interface IAccountService {

	public DTOAccount saveAccount(DTOAccountIU dtoAccountIU);
}
