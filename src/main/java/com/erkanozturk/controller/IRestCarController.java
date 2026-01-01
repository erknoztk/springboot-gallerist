package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoCar;
import com.erkanozturk.dto.DtoCarIU;

public interface IRestCarController {

          public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
