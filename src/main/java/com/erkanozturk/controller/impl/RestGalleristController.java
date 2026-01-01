package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestGalleristController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.DtoGallerist;
import com.erkanozturk.dto.DtoGalleristIU;
import com.erkanozturk.services.IGalleristService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController{

          @Autowired
          private IGalleristService galleristService;

          @PostMapping("/save")
          @Override
          public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
                    
                    
                    return ok(galleristService.saveGallerist(dtoGalleristIU));
          }

}
