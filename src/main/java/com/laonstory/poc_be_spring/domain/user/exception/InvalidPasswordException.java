package com.laonstory.poc_be_spring.domain.user.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException(String value) { super(value, ErrorCode.NOT_MATCH_PASSWORD);
    }
}
