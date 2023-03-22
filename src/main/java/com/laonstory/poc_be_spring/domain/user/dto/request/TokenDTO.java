package com.laonstory.poc_be_spring.domain.user.dto.request;


import com.laonstory.poc_be_spring.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private User user;
}
