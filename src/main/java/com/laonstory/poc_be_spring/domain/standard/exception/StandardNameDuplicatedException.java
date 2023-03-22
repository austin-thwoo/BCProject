package com.laonstory.poc_be_spring.domain.standard.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class StandardNameDuplicatedException extends BusinessException {
    public StandardNameDuplicatedException(String value) {
        super(value, ErrorCode.DUPLICATED_STANDARD_NAME);
    }
}
