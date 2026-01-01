package com.erkanozturk.services;

import com.erkanozturk.dto.CurrencyRateResponse;

public interface ICurrecyRateService {

          public CurrencyRateResponse getCurrencyRate(String startDate, String endDate);
}
