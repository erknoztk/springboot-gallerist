package com.erkanozturk.services;

import com.erkanozturk.dto.DtoAddress;
import com.erkanozturk.dto.DtoAddressIU;

public interface IAddressService {

          public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
