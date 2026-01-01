package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoGalleristCar;
import com.erkanozturk.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

          public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
