package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoSaledCar;
import com.erkanozturk.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

          public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
