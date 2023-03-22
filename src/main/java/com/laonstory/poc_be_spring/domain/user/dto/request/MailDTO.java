package com.laonstory.poc_be_spring.domain.user.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
public class MailDTO {
    private String subject;
    private final List<String> texts = new ArrayList<>();
    private String code;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void addText(String text) {
        texts.add(text);
    }

    public void setCode(String code) {
        this.code = code;
    }
}
