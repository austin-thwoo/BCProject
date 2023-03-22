package com.laonstory.poc_be_spring.domain.standard.exception;


import com.laonstory.poc_be_spring.global.error.exception.BusinessException;
import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

public class StdMachineNotFoundException extends BusinessException {
    public StdMachineNotFoundException(String value) {
        super(value, ErrorCode.MACHINE_NOT_FOUND);
    }
}
