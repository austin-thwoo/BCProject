package com.laonstory.poc_be_spring.domain.user.dto.response;

import com.laonstory.poc_be_spring.domain.user.dto.request.TokenDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TokenResponse {

    private String token;
    private UserResponse user;
    private RoleResponse role;
    public TokenResponse(TokenDTO dto) {
        this.token = dto.getToken();
        this.user = new UserResponse(dto.getUser());
        this.role = new RoleResponse(dto.getUser().getRoles());


    }

}
