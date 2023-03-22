package com.laonstory.poc_be_spring.domain.examination.common;

public enum PicMangeType {

    MAIN("정"),
    SUB("부");


    private final String value;

    PicMangeType(String value) {
        this.value = value;
    }
}
