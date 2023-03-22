package com.laonstory.poc_be_spring.domain.company.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class AlreadySubCompanyException extends BusinessException {
    public AlreadySubCompanyException(String value) { super(value, ErrorCode.ALREADY_SUBCOMPANY);
    }
}
