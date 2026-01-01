package com.erkanozturk.services;

import com.erkanozturk.dto.DtoGalleristCar;
import com.erkanozturk.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

          public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
