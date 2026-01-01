package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestAccountController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAccountIU;
import com.erkanozturk.services.IAccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl  extends RestBaseController implements IRestAccountController{

          @Autowired
          private IAccountService accountService;

          @PostMapping("/save")
          @Override
          public RootEntity<DtoAccount> savedAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
                    

                    return ok(accountService.saveAccount(dtoAccountIU));
          }

}
