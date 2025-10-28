package com.HBA.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HBA.dto.DTOAccount;
import com.HBA.dto.DTOAccountIU;
import com.HBA.model.Account;
import com.HBA.repository.AccountRepository;
import com.HBA.service.IAccountService;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;	
	
	
	private Account createAccount(DTOAccountIU dtoAccountIU) {
		Account account = new Account();
		account.setCreateTime(new Date());
		
		BeanUtils.copyProperties(dtoAccountIU, account);
		return account;
	}
	
	@Override
	public DTOAccount saveAccount(DTOAccountIU dtoAccountIU) {
		DTOAccount dtoAccount = new DTOAccount();
		Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));
		
		BeanUtils.copyProperties(savedAccount, dtoAccount);
		return dtoAccount;
	}

}
