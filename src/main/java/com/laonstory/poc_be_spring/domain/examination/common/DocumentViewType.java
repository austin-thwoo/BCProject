package com.laonstory.poc_be_spring.domain.examination.common;

public enum DocumentViewType {
    BEFOREVIEW("검토전"),
    AFTERVIEW("고객검토");

    private final String value;

    DocumentViewType(String value) {
        this.value = value;
    }
}
