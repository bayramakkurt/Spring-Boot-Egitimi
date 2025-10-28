package com.HBA.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAccount;
import com.HBA.dto.DTOAddress;
import com.HBA.dto.DTOCustomer;
import com.HBA.dto.DTOCustomerIU;
import com.HBA.exception.BaseException;
import com.HBA.exception.ErrorMessage;
import com.HBA.exception.MessageType;
import com.HBA.model.Account;
import com.HBA.model.Address;
import com.HBA.model.Customer;
import com.HBA.repository.AccountRepository;
import com.HBA.repository.AddressRepository;
import com.HBA.repository.CustomerRepository;
import com.HBA.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private Customer createCustomer(DTOCustomerIU dtoCustomerIU) {
		Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if (optionalAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoCustomerIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
		}
		Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if (optionalAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(dtoCustomerIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
		}
		
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		customer.setAddress(optionalAddress.get());
		customer.setAccount(optionalAccount.get());
		
		return customer;
	}
	
	@Override
	public DTOCustomer saveCustomer(DTOCustomerIU dtoCustomerIU) {
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		DTOCustomer dtoCustomer = new DTOCustomer();
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		
		DTOAddress dtoAddress = new DTOAddress();
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		
		DTOAccount dtoAccount = new DTOAccount();
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		
		dtoCustomer.setAccount(dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
				
		return dtoCustomer;
	}

}
