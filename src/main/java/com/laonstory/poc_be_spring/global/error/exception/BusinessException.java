package com.laonstory.poc_be_spring.global.error.exception;

import com.laonstory.poc_be_spring.global.error.model.ErrorCode;

import java.util.List;

public class BusinessException extends RuntimeException {


    private ErrorCode errorCode;

    public BusinessException() {

        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        this.errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public BusinessException(String message, ErrorCode errorCode) {


        super(message);
        this.errorCode = errorCode;

    }


//    public BusinessException(List<String> messages, ErrorCode errorCode) {//
//        for (String messsage : messages) {
//            super(messsage);
//            this.errorCode = errorCode;
//        }

//    }


    public BusinessException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {

        return errorCode;

    }

}
