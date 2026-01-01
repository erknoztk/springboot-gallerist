package com.erkanozturk.services;

import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoCarIU;

public interface ICarService  {

          public DtoCar saveCar(DtoCarIU dtoCarIU);
}
