package com.laonstory.poc_be_spring.domain.user.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String value) {
        super(value, ErrorCode.USER_NOT_FOUND);
    }
}
