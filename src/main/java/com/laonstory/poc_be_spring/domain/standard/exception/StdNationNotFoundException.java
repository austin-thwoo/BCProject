package com.laonstory.poc_be_spring.domain.standard.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class StdNationNotFoundException extends BusinessException {
    public StdNationNotFoundException(String value) {
        super(value, ErrorCode.NATION_NOT_FOUND);
    }
}
