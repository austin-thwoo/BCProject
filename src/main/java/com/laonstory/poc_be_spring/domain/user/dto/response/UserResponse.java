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
public class UserResponse {
    private UUID uuid;
    private String userName;
    private Long companyId;
    private String companyName;
    private String part;
    private String position;
    private String name;
    private String userPhone;
    private String userEmail;
    private String didAddress;
    private String authentification;
    private UserType userType;
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
    private RoleResponse role;

    public UserResponse(User user){
        this.uuid=user.getId();
        this.userName=user.getUsername();
        this.companyId=user.getCompany().getId();
        this.companyName=user.getCompany().getName();
        this.part= user.getPart();
        this.position=user.getPosition();
        this.authentification= user.getAuthentification();
        this.name=user.getName();
        this.userPhone=user.getUserPhone();
        this.userEmail= user.getUserEmail();
        this.didAddress=user.getDidAddress();
        this.userType=user.getUserType();
        this.createdDate=user.getCreatedDate();
        this.deletedDate=user.getDeletedDate();
        this.role=new RoleResponse(user.getRoles());
    }
}
