package com.laonstory.poc_be_spring.domain.company.domain;


import com.laonstory.poc_be_spring.domain.company.domain.common.CompanyType;
import com.laonstory.poc_be_spring.domain.company.dto.request.SubCompanyCommand;
import com.laonstory.poc_be_spring.domain.models.Address;
import com.laonstory.poc_be_spring.domain.user.domain.User;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_SUB_COMPANY")
public class SubCompany extends BaseTimeEntity {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String owner;
  private String serialCode;
  private String companyPhone;
  @Embedded
  private Address address;

  @Enumerated(value = STRING)
  private CompanyType companyType;

  @ManyToOne(fetch =FetchType.LAZY)
  private Company company;


  public static SubCompany place(SubCompanyCommand subCompanyCommand, Company company) {
    SubCompany subCompany =new SubCompany();
    subCompany.name=subCompanyCommand.getName();
    subCompany.owner=subCompanyCommand.getOwner();
    subCompany.serialCode=subCompanyCommand.getSerialCode();
    subCompany.companyPhone=subCompanyCommand.getCompanyPhone();
    subCompany.address=subCompanyCommand.getAddress();
    subCompany.company=company;
    subCompany.companyType=subCompanyCommand.getCompanyType();
    return subCompany;
  }


  public void modify(SubCompanyCommand subCompanyCommand) {
    this.name=subCompanyCommand.getName();
    this.owner=subCompanyCommand.getOwner();
    this.serialCode=subCompanyCommand.getSerialCode();
    this.companyPhone=subCompanyCommand.getCompanyPhone();
    this.address= subCompanyCommand.getAddress();
    this.companyType=subCompanyCommand  .getCompanyType();

  }
}
