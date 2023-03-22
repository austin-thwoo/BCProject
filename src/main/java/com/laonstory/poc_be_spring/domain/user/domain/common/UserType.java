package com.laonstory.poc_be_spring.domain.user.domain.common;

public enum UserType {
    ADMIN("관리자"),
    MANUFACTURER("제조사"),
    AGENT("중개인");

    private final String value;

    UserType(String value) {
        this.value = value;
    }
}
