package com.erkanozturk.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erkanozturk.controller.IRestCurrencyRatesController;
import com.erkanozturk.controller.RestBaseController;
import com.erkanozturk.controller.RootEntity;
import com.erkanozturk.dto.CurrencyRateResponse;
import com.erkanozturk.services.ICurrecyRateService;

@RestController
@RequestMapping("/rest/api")
public class RestCurrencyRatesController  extends RestBaseController implements IRestCurrencyRatesController{

          @Autowired
          private ICurrecyRateService currecyRateService;

          @GetMapping("/currency-rates")
          @Override
          public RootEntity<CurrencyRateResponse> getCurrencyRates(
                    @RequestParam("startDate") String startDate, String endDate) {
                              
                    
                    return ok(currecyRateService.getCurrencyRate(startDate, endDate));
          }

}
