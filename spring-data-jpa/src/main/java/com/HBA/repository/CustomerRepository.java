package com.HBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HBA.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
