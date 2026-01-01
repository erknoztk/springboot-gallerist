package com.erkanozturk.services.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erkanozturk.dto.AuthRequest;
import com.erkanozturk.dto.AuthResponse;
import com.erkanozturk.dto.DtoUser;
import com.erkanozturk.dto.RefreshTokenRequest;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.jwt.JWTService;
import com.erkanozturk.model.RefreshToken;
import com.erkanozturk.model.User;
import com.erkanozturk.repository.RefreshTokenRepository;
import com.erkanozturk.repository.UserRepository;
import com.erkanozturk.services.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

          @Autowired
          private UserRepository userRepository;

          @Autowired
          private BCryptPasswordEncoder passwordEncoder;

          @Autowired
          private AuthenticationProvider authenticationProvider;

          @Autowired
          private JWTService jwtService;

          @Autowired
          private RefreshTokenRepository refreshTokenRepository;

          private User createUser(AuthRequest request){

                    User user = new User();
                    user.setCreateTime(new Date());
                    user.setUsername(request.getUsername());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));

                    return user;
          }

          private RefreshToken createRefreshToken(User user){
                    
                    RefreshToken refreshToken = new RefreshToken();
                    refreshToken.setCreateTime(new Date());
                    refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
                    refreshToken.setRefreshToken(UUID.randomUUID().toString());
                    refreshToken.setUser(user);

                    return refreshToken;
          }

          @Override
          public DtoUser register(AuthRequest request) {
                    
                    User saveUser = userRepository.save(createUser(request));
                    DtoUser dtoUser = new DtoUser();
                    
                    BeanUtils.copyProperties(saveUser, dtoUser);

                    return dtoUser;
          }

          @Override
          public AuthResponse authenticate(AuthRequest request) {
                   
                    try {     
                              UsernamePasswordAuthenticationToken  authenticationToken =  
                                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
                              authenticationProvider.authenticate(authenticationToken);

                              Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
                              String accessToken = jwtService.generateToken(optionalUser.get()); 
                              RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get())) ;

                              return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());

                    } catch (Exception e) {
                             throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
                    }
                    
          }

          public boolean isValidRefreshToken(Date expiredDate){

                    return new Date().before(expiredDate);
          }

          @Override
          public AuthResponse refreshToken(RefreshTokenRequest request) {
                    
                    Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
                    if(optionalRefreshToken.isEmpty()){
                              throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
                    }
                    if(!isValidRefreshToken(optionalRefreshToken.get().getExpiredDate())){

                              throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
                    }

                    User user = optionalRefreshToken.get().getUser();
                    String accessToken = jwtService.generateToken(user);
                    RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

                    return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
          }

}
