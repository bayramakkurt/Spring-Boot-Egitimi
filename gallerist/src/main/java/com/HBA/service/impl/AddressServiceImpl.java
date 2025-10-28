package com.HBA.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOAdressIU;
import com.HBA.model.Address;
import com.HBA.repository.AddressRepository;
import com.HBA.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	private Address createAddress(DTOAdressIU dtoAdressIU) {
		Address address = new Address();
		address.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoAdressIU, address);
		return address;
	}
	
	@Override
	public DTOAddress saveAddress(DTOAdressIU dtoAdressIU) {
		DTOAddress dtoAddress = new DTOAddress();
		Address savedAddress =  addressRepository.save(createAddress(dtoAdressIU));
		
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		return dtoAddress;
	}

	
}
