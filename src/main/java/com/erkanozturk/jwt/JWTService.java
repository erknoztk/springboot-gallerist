package com.erkanozturk.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

          public static final String SECRET_KEY = "P2FOC0/6Ry+DDrI850VZ1caM6VLtPqH35kCrixFdsp0=";

          // token oluşturma
          public String generateToken(UserDetails userDetails){

                    return  Jwts.builder()
                    .setSubject(userDetails.getUsername()) // Claims::getSubject
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                    .signWith(getKey(), SignatureAlgorithm.HS256) // key ile beraber token oluştur
                    .compact();
                    
          }
                    // token çözme
          public <T> T exportToken(String token, Function<Claims, T> claimsFunc) {

                    Claims claims = getClaims(token);
                    return claimsFunc.apply(claims); // çözer

          }

          public Claims getClaims(String token){

                    Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getKey()) // bu tokenı çöz
                    .build()
                    .parseClaimsJws(token).getBody(); // tokendaki claimslara ulaş

                    return claims;
          }

          public String getUsernameByToken(String token) {

                    return exportToken(token, Claims::getSubject);
          }
          public boolean isTokenValid(String token) {
                                                            // token oluşturuken alanlar
                    Date expiredDate = exportToken(token, Claims::getExpiration);
                    return new Date().before(expiredDate);
          }

          // key oluşturma 
          public Key getKey(){
                    byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
                    return Keys.hmacShaKeyFor(bytes);
          }
}
