package com.laonstory.poc_be_spring.domain.user.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CodeCommand {
    private String code;
    private String userEmail;
}
