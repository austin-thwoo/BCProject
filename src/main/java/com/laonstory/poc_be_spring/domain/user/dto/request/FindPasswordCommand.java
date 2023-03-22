package com.laonstory.poc_be_spring.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindPasswordCommand {
    private String userName;
    private String name;
    private String userPhone;
    private String code;
}
