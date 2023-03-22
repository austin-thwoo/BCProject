package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class PassCodeTimeOverException extends BusinessException {

    public PassCodeTimeOverException(String message) {
        super(message, ErrorCode.PASSCODE_TIME_OVER);
    }
}
