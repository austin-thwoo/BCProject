package com.laonstory.poc_be_spring.domain.standard.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class StdLineNotFoundException extends BusinessException {
    public StdLineNotFoundException(String value) {
        super(value, ErrorCode.CERTIFY_NOT_FOUND);
    }
}
