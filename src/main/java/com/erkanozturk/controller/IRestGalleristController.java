package com.erkanozturk.controller;

import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoGalleristIU;

public interface IRestGalleristController {

          public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
