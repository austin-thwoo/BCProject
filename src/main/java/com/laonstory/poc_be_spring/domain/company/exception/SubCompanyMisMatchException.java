package com.laonstory.poc_be_spring.domain.company.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class SubCompanyMisMatchException extends BusinessException {
    public SubCompanyMisMatchException(String value) {
        super(value, ErrorCode.SUBCOMPANY_MIS_MATCH);
    }
}
