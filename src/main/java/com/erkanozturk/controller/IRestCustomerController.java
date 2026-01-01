package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoCustomerIU;

public interface IRestCustomerController {

          public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
