package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestSaledCarController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.DtoSaledCar;
import com.erkanozturk.dto.DtoSaledCarIU;
import com.erkanozturk.services.ISaledCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarController extends RestBaseController implements IRestSaledCarController {

          @Autowired
          private ISaledCarService saledCarService;

          @PostMapping("/save")
          @Override
          public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {

                    return ok(saledCarService.buyCar(dtoSaledCarIU));
          }
}
