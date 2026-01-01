package com.erkanozturk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase { // response


         private String city;
         
         private String district;

         private String neighborhood;

         private String street;
}

