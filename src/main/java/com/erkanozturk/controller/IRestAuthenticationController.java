package com.erkanozturk.controller;

import com.erkanozturk.dto.AuthRequest;
import com.erkanozturk.dto.AuthResponse;
import com.erkanozturk.dto.DtoUser;
import com.erkanozturk.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

          public RootEntity<DtoUser> register(AuthRequest request);

          public RootEntity<AuthResponse> authenticate(AuthRequest request);

          public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
