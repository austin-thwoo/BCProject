package com.laonstory.poc_be_spring.domain.user.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class NickNameDuplicatedException extends BusinessException {
    public NickNameDuplicatedException(String value) {
        super(value, ErrorCode.DUPLICATED_NICKNAME);
    }
}
