package com.laonstory.poc_be_spring.domain.company.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class CompanySerialCodeDuplicatedException extends BusinessException {
    public CompanySerialCodeDuplicatedException(String value) {
        super(value, ErrorCode.DUPLICATED_SERIALCODE);
    }
}
