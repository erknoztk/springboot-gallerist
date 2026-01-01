package com.erkanozturk.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRateResponse {


          private Integer totalCount;

          private List<CurrencyRateItems> items;




//           {
//     "totalCount": 1,
//     "items": [
//         {
//             "Tarih": "31-12-2025",
//             "TP_DK_USD_A_YTL": "42.8623",
//             "UNIXTIME": {
//                 "$numberLong": "1767128400"
//             }
//         }
//     ]
// }
}
