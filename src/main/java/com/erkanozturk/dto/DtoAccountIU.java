package com.erkanozturk.dto;

import java.math.BigDecimal;

import com.erkanozturk.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter   
public class DtoAccountIU {

          @NotNull
          private String accountNo;

          @NotNull
          private String iban;
         
          @NotNull
          private BigDecimal amount; // parasal işlemler BigDecimal ile tutulur

          @NotNull
          private CurrencyType currencyType;
}
