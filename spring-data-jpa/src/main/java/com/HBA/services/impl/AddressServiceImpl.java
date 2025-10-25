package com.HBA.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOCustomer;
import com.HBA.entities.Address;
import com.HBA.entities.Customer;
import com.HBA.repository.AddressRepository;
import com.HBA.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public DTOAddress findAddressByID(Long id) {
		DTOAddress dtoAddress = new DTOAddress();
		
		Optional<Address> optional = addressRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		Address address = optional.get();
		BeanUtils.copyProperties(address, dtoAddress);
		
		DTOCustomer dtoCustomer = new DTOCustomer();
		dtoCustomer.setId(address.getCustomer().getId());
		dtoCustomer.setName(address.getCustomer().getName());
		
		dtoAddress.setCustomer(dtoCustomer);
		return dtoAddress;
	}
}
