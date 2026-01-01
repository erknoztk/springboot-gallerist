package com.erkanozturk.utils;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtils {

          public static String getCurrentDate(Date date){

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    return simpleDateFormat.format(date);
          }
}
