package com.erkanozturk.services;

import com.erkanozturk.dto.DtoSaledCar;
import com.erkanozturk.dto.DtoSaledCarIU;

public interface ISaledCarService {

          public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
