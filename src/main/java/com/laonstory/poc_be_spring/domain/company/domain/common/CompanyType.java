package com.laonstory.poc_be_spring.domain.company.domain.common;

public enum CompanyType {
    MANUFACTURER("제조사"),
    FACTORY("공장");

    private final String value;

    CompanyType(String value) {
        this.value = value;
    }
}
