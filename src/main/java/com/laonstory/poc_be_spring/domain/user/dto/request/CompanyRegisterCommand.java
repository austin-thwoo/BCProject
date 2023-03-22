package com.laonstory.poc_be_spring.domain.user.dto.request;

import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.models.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRegisterCommand {

    private String name;
    private String owner;
    private String serialCode;

    private String companyPhone;

    @Embedded
    private Address address;

    private Long companyId;










}
