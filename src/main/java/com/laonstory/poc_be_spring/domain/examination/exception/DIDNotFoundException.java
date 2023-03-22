package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class DIDNotFoundException extends BusinessException {
    public DIDNotFoundException(String value) {
        super(value, ErrorCode.DID_NOT_FOUND);
    }
}
