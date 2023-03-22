package com.laonstory.poc_be_spring.domain.company.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class DeletedSubCompanyException extends BusinessException {

    public DeletedSubCompanyException(String message) {
        super(message, ErrorCode.SUBCOMPANY_DELETED);
    }
}
