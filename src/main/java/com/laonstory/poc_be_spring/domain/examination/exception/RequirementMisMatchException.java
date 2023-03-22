package com.laonstory.poc_be_spring.domain.examination.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class RequirementMisMatchException extends BusinessException {
    public RequirementMisMatchException(String value) {
        super(value, ErrorCode.REQUIREMENT_MIS_MATCH);
    }
}
