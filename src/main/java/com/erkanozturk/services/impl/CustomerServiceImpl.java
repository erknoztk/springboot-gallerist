package com.erkanozturk.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoCustomerIU;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.model.Account;
import com.erkanozturk.model.Address;
import com.erkanozturk.model.Customer;
import com.erkanozturk.repository.AccountRepository;
import com.erkanozturk.repository.AddressRepository;
import com.erkanozturk.repository.CustomerRepository;
import com.erkanozturk.services.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{

          @Autowired
          private CustomerRepository customerRepository;

          @Autowired 
          private AddressRepository addressRepository;

          @Autowired 
          private AccountRepository accountRepository;

          private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

                    Optional<Address> optionalAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
                    if(optionalAddress.isEmpty()){

                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
                    }

                    Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
                    if(optionalAccount.isEmpty()){

                              throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
                    }



                    Customer customer = new Customer();
                    customer.setCreateTime(new Date());
                    BeanUtils.copyProperties(dtoCustomerIU, customer);

                    customer.setAddress(optionalAddress.get());
                    customer.setAccount(optionalAccount.get());
                    
                    return customer;
          }

          @Override
          public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
                    
                    DtoCustomer dtoCustomer = new DtoCustomer();
                    DtoAddress dtoAddress = new DtoAddress();
                    DtoAccount dtoAccount = new DtoAccount();

                    Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

                    BeanUtils.copyProperties(savedCustomer, dtoCustomer);
                    BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
                    BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);

                    dtoCustomer.setAccount(dtoAccount);
                    dtoCustomer.setAddress(dtoAddress);


                    return dtoCustomer;
          }


}
