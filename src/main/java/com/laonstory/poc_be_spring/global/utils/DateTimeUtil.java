package com.laonstory.poc_be_spring.global.utils;

import com.laonstory.poc_be_spring.global.dto.DateDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {


    public static DateDTO convertToLocalDateByString(String startDate, String endDate,DateDTO dateDTO){

        dateDTO.setStartedDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dateDTO.setEndedDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return dateDTO;
    }

}
