package com.erkanozturk.handler;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.erkanozturk.exception.BaseException;

@ControllerAdvice
public class GlobalExceptionHandler {

          @ExceptionHandler(value = {BaseException.class}) // uygulamamın attığı hatalar
          public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request){
                    return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
          }

          @ExceptionHandler(value = {MethodArgumentNotValidException.class}) // spring validation exception
          public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
                    
                    Map<String, List<String>> map = new HashMap<>(); // firstName alana 6 hata gibi
                    for (ObjectError objError : ex.getBindingResult().getAllErrors()) {
                              String fieldName = ((FieldError)objError).getField();
                              // hangisi olduğunu buluyor
                              if(map.containsKey(fieldName)){ // o  alan için birden fazla hata

                                        map.put(fieldName, addValue(map.get(fieldName), objError.getDefaultMessage()));
                              }else {
                                        map.put(fieldName, addValue(new ArrayList<>(), objError.getDefaultMessage()));
                              }
                    }

                    // map i doldurucak 
                    return ResponseEntity.badRequest().body(createApiError(map, request));
          }

          private List<String> addValue(List<String> list, String newValue){

                    list.add(newValue);
                    return list;
          }

          public String getHostName(){
                    
                    try {
                              return Inet4Address.getLocalHost().getHostName();
                    } catch (UnknownHostException e) {
                              // TODO: handle exception
                    }
                    return " ";
          }         

          public <E> ApiError<E> createApiError(E message, WebRequest request){

                    ApiError<E> apiError = new ApiError<>();
                    apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

                    Exception<E> exception = new Exception<>();
                    exception.setPath(request.getDescription(false).substring(0));
                    exception.setCreateTime(new Date());
                    exception.setHostName(getHostName());
                    exception.setMessage(message);

                    apiError.setException(exception);
                    return apiError;
          }
}
