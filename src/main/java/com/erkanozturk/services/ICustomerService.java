package com.erkanozturk.services;

import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoCustomerIU;

public interface ICustomerService  {

          public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);          
}
