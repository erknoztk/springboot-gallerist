package com.erkanozturk.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRateItems {

          @JsonProperty("Tarih") // ıdışardan gelen json adı  ya da aynı isim
          private String date;
          
          @JsonProperty("TP_DK_USD_A_YTL")
          private String usd;
}
