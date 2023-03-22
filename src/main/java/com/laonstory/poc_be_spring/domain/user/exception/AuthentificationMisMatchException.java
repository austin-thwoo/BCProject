package com.laonstory.poc_be_spring.domain.user.exception;

import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class AuthentificationMisMatchException extends BusinessException {

    public AuthentificationMisMatchException(String message) {
        super(message, ErrorCode.AUTHENTIFICATION_MIS_MATCH);
    }
}
