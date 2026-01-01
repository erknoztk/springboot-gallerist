package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestAuthenticationController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.AuthRequest;
import com.erkanozturk.dto.AuthResponse;
import com.erkanozturk.dto.DtoUser;
import com.erkanozturk.dto.RefreshTokenRequest;
import com.erkanozturk.services.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

          @Autowired
          private IAuthenticationService authenticationService;

          @PostMapping("/register")
          @Override
          public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
                    
                    return ok(authenticationService.register(request));
          }

          @PostMapping("/authenticate")
          @Override
          public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
                   

                    return ok(authenticationService.authenticate(request));
          }

          @PostMapping(value = "/refreshToken")
          @Override
          public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
                    
                    return ok(authenticationService.refreshToken(request));
          }

          
}
