
package com.erkanozturk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erkanozturk.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
