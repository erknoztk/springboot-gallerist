package com.erkanozturk.services.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAccountIU;
import com.erkanozturk.model.Account;
import com.erkanozturk.repository.AccountRepository;
import com.erkanozturk.services.IAccountService;

@Service
public class AccountService  implements IAccountService{

          @Autowired
          private AccountRepository accountRepository;

          private Account createAccount(DtoAccountIU dtoAccountIU){

                    Account account = new Account();
                    account.setCreateTime(new Date());

                    BeanUtils.copyProperties(dtoAccountIU, account);

                    return account;
          }

          @Override
          public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {

                    DtoAccount dtoAccount = new DtoAccount();
                    Account savedAccount = accountRepository.save(createAccount(dtoAccountIU));

                    BeanUtils.copyProperties(savedAccount, dtoAccount);
                    return dtoAccount;
          }


}
