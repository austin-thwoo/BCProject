package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class ExaminationMisMatchException extends BusinessException {
    public ExaminationMisMatchException(String value) {
        super(value, ErrorCode.EXAMINATION_MIS_MATCH);
    }
}
