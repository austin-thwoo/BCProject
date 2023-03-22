package com.laonstory.poc_be_spring.global.error.exception;


import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class DateWrongException extends BusinessException {
    public DateWrongException(String value) {
        super(value, ErrorCode.DATE_WRONG);
    }
}
