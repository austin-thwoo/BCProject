package com.laonstory.poc_be_spring.global.application;


import com.laonstory.poc_be_spring.global.dto.DateDTO;
import com.laonstory.poc_be_spring.global.error.exception.DateWrongException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.laonstory.poc_be_spring.global.utils.DateTimeUtil.convertToLocalDateByString;

@RequiredArgsConstructor
@Service
public class CustomDateService{

    public DateDTO dateConvert(String startDate, String endDate, DateDTO dateDTO) {
        if (startDate != null && endDate!=null) {
            dateDTO=convertToLocalDateByString(startDate,endDate,dateDTO);
            dateConfirm(dateDTO);
            return dateDTO;
        }
        return dateDTO;
    }

    private void dateConfirm(DateDTO dateDTO) {
        if (dateDTO.getStartedDate().isAfter(dateDTO.getEndedDate())){
            throw new DateWrongException(dateDTO.toString());
        }
    }


}
