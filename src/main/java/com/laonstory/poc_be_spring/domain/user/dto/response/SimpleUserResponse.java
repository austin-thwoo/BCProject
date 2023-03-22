package com.laonstory.poc_be_spring.domain.user.dto.response;

import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.domain.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserResponse {
    private UUID uuid;
    private String userName;
    private String name;
    private Long companyId;
    private String companyName;
    private String position;
    private UserType userType;
    private String didAddress;


    public SimpleUserResponse(User user) {
        this.uuid = user.getId();
        this.userName = user.getUsername();
        this.companyId = user.getCompany().getId();
        this.companyName = user.getCompany().getName();
        this.name = user.getName();
        this.position = user.getPosition();
        this.userType = user.getUserType();
        this.didAddress = user.getDidAddress();


    }

}
