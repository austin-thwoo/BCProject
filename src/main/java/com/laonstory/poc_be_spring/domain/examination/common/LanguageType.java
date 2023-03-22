package com.laonstory.poc_be_spring.domain.examination.common;

public enum LanguageType {
    KOREAN("국문"),
    ENGLISH("영문"),
    GLOBAL("국문+영문");

    private final String value;

    LanguageType(String value) {
        this.value = value;
    }
}
