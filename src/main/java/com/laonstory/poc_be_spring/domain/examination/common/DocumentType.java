package com.laonstory.poc_be_spring.domain.examination.common;

public enum DocumentType {
    PUBLIC("공인성적서"),
    NOMAL("일반성적서");

    private final String value;

    DocumentType(String value) {
        this.value = value;
    }
}
