package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestGalleristCarController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.DtoGalleristCar;
import com.erkanozturk.dto.DtoGalleristCarIU;
import com.erkanozturk.services.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/galleristcar")
public class RestGalleristCarController extends RestBaseController implements IRestGalleristCarController {

          @Autowired
          private IGalleristCarService galleristCarService;

          @PostMapping("/save")
          @Override
          public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
                    

                    return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
          }

}
