package com.HBA.controller;

import com.HBA.dto.DTOAccount;
import com.HBA.dto.DTOAccountIU;

public interface IRestAccountController {

	public RootEntity<DTOAccount> saveAccount(DTOAccountIU dtoAccountIU);
}
