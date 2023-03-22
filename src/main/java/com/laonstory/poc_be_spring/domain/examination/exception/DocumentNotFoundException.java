package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class DocumentNotFoundException extends BusinessException {
    public DocumentNotFoundException(String value) {
        super(value, ErrorCode.DOCUMENT_NOT_FOUND);
    }
}
