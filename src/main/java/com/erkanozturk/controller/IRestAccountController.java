package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAccountIU;

public interface IRestAccountController {

          public RootEntity<DtoAccount> savedAccount(DtoAccountIU dtoAccountIU);
}
