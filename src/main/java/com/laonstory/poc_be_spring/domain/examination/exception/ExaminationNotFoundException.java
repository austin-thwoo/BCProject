package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class ExaminationNotFoundException extends BusinessException {
    public ExaminationNotFoundException(String value) {
        super(value, ErrorCode.EXAMINATION_NOT_FOUND);
    }
}
