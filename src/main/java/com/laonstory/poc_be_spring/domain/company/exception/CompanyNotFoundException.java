package com.laonstory.poc_be_spring.domain.company.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class CompanyNotFoundException extends BusinessException {
    public CompanyNotFoundException(String value) {
        super(value, ErrorCode.COMPANY_NOT_FOUND);
    }
}
