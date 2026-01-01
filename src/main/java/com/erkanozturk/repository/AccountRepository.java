package com.erkanozturk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erkanozturk.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
          
}
