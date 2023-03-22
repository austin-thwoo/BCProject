package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class InfoMisMatchException extends BusinessException {

    public InfoMisMatchException(String message) {
        super(message, ErrorCode.INFO_MIS_MATCH);
    }
}
