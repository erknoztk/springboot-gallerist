package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestCustomerController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.DtoCustomer;
import com.erkanozturk.dto.DtoCustomerIU;
import com.erkanozturk.services.ICustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/customer")
public class RestControllerImpl extends RestBaseController  implements IRestCustomerController{

          @Autowired
          private ICustomerService customerService;

          @PostMapping("/save")
          @Override
          public RootEntity<DtoCustomer> saveCustomer(@Valid @RequestBody DtoCustomerIU dtoCustomerIU) {
                    
                    return ok(customerService.saveCustomer(dtoCustomerIU));
          }

}
