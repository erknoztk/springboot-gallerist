package com.erkanozturk.services;

import com.erkanozturk.dto.AuthRequest;
import com.erkanozturk.dto.AuthResponse;
import com.erkanozturk.dto.DtoUser;
import com.erkanozturk.dto.RefreshTokenRequest;

public interface IAuthenticationService {

          public DtoUser register(AuthRequest request);
          
          public AuthResponse authenticate(AuthRequest request);

          public AuthResponse refreshToken(RefreshTokenRequest request);
          
}
