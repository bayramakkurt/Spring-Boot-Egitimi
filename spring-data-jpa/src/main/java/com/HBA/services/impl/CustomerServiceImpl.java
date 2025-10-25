package com.HBA.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOCustomer;
import com.HBA.entities.Address;
import com.HBA.entities.Customer;
import com.HBA.repository.CustomerRepository;
import com.HBA.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public DTOCustomer findCustomerByID(Long id) {
		DTOCustomer dtoCustomer = new DTOCustomer();
		DTOAddress dtoAddress = new DTOAddress();
		
		Optional<Customer> optional = customerRepository.findById(id); //Burada veritabanından gerekli verileri çektik Optional türünde.
		if (optional.isEmpty()) {
			return null;
		}
		Customer customer = optional.get(); //Optional nesnesi içinde Customer türünede olan verileri nesneye aktardık.
		Address address = optional.get().getAddress(); //Optional nesnesi içindeki verilerden Address verisini ayrı alıp Address türündeki nesneye aktardık.
		
		BeanUtils.copyProperties(customer, dtoCustomer); //Verileri DTO kopyaladık ancak Customer verileri aktarılır Address verileri nesneye aktarılmaz.
		BeanUtils.copyProperties(address, dtoAddress);
		
		dtoCustomer.setAddress(dtoAddress); //Adress verilerini aktarmak için ayrı bir SET işlemi gerçekleştirdik.
		return dtoCustomer;
	}

}
