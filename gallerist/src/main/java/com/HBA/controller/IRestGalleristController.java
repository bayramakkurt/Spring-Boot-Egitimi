package com.HBA.controller;

import com.HBA.dto.DTOGallerist;
import com.HBA.dto.DTOGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DTOGallerist> saveGallerist(DTOGalleristIU dtoGalleristIU);
}
