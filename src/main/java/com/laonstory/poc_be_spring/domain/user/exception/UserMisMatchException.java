package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;
public class UserMisMatchException extends BusinessException {

    public UserMisMatchException(String message) {
        super(message, ErrorCode.USER_MIS_MATCH);
    }
}
