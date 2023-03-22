package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class DeletedUserException extends BusinessException {

    public DeletedUserException(String message) {
        super(message, ErrorCode.USER_DELETED);
    }
}
