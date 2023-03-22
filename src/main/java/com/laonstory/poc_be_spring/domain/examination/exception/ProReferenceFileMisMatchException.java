package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class ProReferenceFileMisMatchException extends BusinessException {
    public ProReferenceFileMisMatchException(String value) {
        super(value, ErrorCode.REFERENCEFILE_MIS_MATCH);
    }
}
