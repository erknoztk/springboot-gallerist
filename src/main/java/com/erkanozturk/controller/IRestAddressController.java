package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoAddressIU;

public interface IRestAddressController {

          public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
