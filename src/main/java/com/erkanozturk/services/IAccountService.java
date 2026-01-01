package com.erkanozturk.services;

import com.erkanozturk.dto.DtoAccount;
import com.erkanozturk.dto.DtoAccountIU;

public interface IAccountService {

          public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
