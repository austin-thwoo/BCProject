package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class PassCodeMisMatchException extends BusinessException {

    public PassCodeMisMatchException(String message) {
        super(message, ErrorCode.PASSCODE_MIS_MATCH);
    }
}
