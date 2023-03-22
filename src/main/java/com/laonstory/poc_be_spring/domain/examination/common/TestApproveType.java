package com.laonstory.poc_be_spring.domain.examination.common;

public enum TestApproveType {

    ALLOW("승인"),
    RENEGE("반려");


    private final String value;

    TestApproveType(String value) {
        this.value = value;
    }
}
