package com.HBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HBA.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
