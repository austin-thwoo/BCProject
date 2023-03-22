package com.laonstory.poc_be_spring.domain.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LoginCommand {

    @ApiModelProperty(position =1, notes = "The id")
    private String userName;
    @ApiModelProperty(position =2, notes  = "The password")
    private String password;


}
