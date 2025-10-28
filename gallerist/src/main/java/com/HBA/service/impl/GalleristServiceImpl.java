package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOGallerist;
import com.HBA.dto.DTOGalleristIU;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.model.Address;
import com.HBA.model.Gallerist;
import com.HBA.repository.AddressRepository;
import com.HBA.repository.GalleristRepository;
import com.HBA.service.IGalleristService;

@Service
public class GalleristServiceImpl implements IGalleristService{
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	private Gallerist createGallerist(DTOGalleristIU dtoGalleristIU) {
		Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if (optionalAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoGalleristIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
		}

		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		gallerist.setAddress(optionalAddress.get());
		return gallerist;
	}

	@Override
	public DTOGallerist saveGallerist(DTOGalleristIU dtoGalleristIU) {
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU)); 
		DTOGallerist dtoGallerist = new DTOGallerist();
		
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		
		DTOAddress dtoAddress = new DTOAddress();
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress);
		return dtoGallerist;
	}

}
