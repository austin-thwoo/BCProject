package com.laonstory.poc_be_spring.domain.examination.common;

public enum ExaminationType {
    EXAMINATION("시험"),
    CERTIFICATION("인증"),
    EXAMCERTIFY("시험인증");

    private final String value;

    ExaminationType(String value) {
        this.value = value;
    }
}
