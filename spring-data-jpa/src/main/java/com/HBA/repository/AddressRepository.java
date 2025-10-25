package com.HBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HBA.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
