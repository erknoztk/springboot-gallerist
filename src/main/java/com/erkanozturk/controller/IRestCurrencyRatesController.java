package com.erkanozturk.controller;

import com.erkanozturk.dto.CurrencyRateResponse;

public interface IRestCurrencyRatesController {

          public RootEntity<CurrencyRateResponse> getCurrencyRates(String startDate, String endDate);
}
