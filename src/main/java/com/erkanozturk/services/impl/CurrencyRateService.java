package com.erkanozturk.services.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.erkanozturk.dto.CurrencyRateResponse;
import com.erkanozturk.exception.BaseException;
import com.erkanozturk.exception.ErrorMessage;
import com.erkanozturk.exception.MessageType;
import com.erkanozturk.services.ICurrecyRateService;

@Service
public class CurrencyRateService  implements ICurrecyRateService{

          @Override
          public CurrencyRateResponse getCurrencyRate(String startDate, String endDate) {

                 // https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=31-12-2025&endDate=31-12-2025&type=json

                 String rootURL  = "https://evds2.tcmb.gov.tr/service/evds/";
                 String series = "TP.DK.USD.A.YTL";
                 String type = "json";

                 String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;
                    
                 HttpHeaders httpHeaders = new HttpHeaders();
                 httpHeaders.set("key", "RFRSjvvtE2");

                 HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

                 RestTemplate restTemplate = new RestTemplate(); // dış servislere istek atılabilir

                  // istek atmak için
                 try {
                    ResponseEntity<CurrencyRateResponse> response =
                    restTemplate.exchange(
                    endpoint,                      // URL
                    HttpMethod.GET,                // HTTP metodu
                    httpEntity,                    // Header / Auth / Body (GET'te genelde body yok)
                    new ParameterizedTypeReference<CurrencyRateResponse>() {} // geri dönülecek tip
                     );

                     if(response.getStatusCode().is2xxSuccessful()){
                              return response.getBody(); // currencyRate object i geri döndürür
                     }
                     
                 } catch (Exception e) {
                    throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
                 }

                 return null;
           
          }
 
}
