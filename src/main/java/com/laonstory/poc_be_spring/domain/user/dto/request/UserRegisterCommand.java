package com.laonstory.poc_be_spring.domain.user.dto.request;

import com.laonstory.poc_be_spring.domain.user.domain.common.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserRegisterCommand {
    private  String userName;
    private  String password;
    private  Long companyId;
    private  String part;
    private  String position;
    private  String name;
    private  String userPhone;
    private  String userEmail;
    private  String didAddress;
    private  UserType userType;
    private List<Long> companyIds;
    private String authentification;



    public void setEncodedPassword(String encodedPassword) {
        this.password= encodedPassword;
    }
}
