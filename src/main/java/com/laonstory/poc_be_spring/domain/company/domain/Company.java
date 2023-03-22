package com.laonstory.poc_be_spring.domain.company.domain;


import com.laonstory.poc_be_spring.domain.models.Address;
import com.laonstory.poc_be_spring.domain.user.dto.request.CompanyRegisterCommand;
import com.laonstory.poc_be_spring.global.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_COMPANY")
public class Company extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String owner;
  private String serialCode;
  private String companyPhone;
  @Embedded
  private Address address;



//  public static Company place(CompanyRegisterCommand companyRegisterCommand) {
//    return Company.builder()
//            .name(companyRegisterCommand.getName())
//            .owner(companyRegisterCommand.getOwner())
//            .serialCode(companyRegisterCommand.getSerialCode())
//            .companyPhone(companyRegisterCommand.getCompanyPhone())
//            .address(companyRegisterCommand.getAddress())
//            .build();
//
//  }

  public static Company place(CompanyRegisterCommand companyRegisterCommand) {
    Company company =new Company();
    company.name= companyRegisterCommand.getName();
    company.owner= companyRegisterCommand.getOwner();
    company.serialCode=companyRegisterCommand.getSerialCode();
    company.companyPhone=companyRegisterCommand.getCompanyPhone();
    company.address=companyRegisterCommand.getAddress();
    return company;
  }

    public void updateInfo(CompanyRegisterCommand registerCommand) {
    this.name=registerCommand.getName();
    this.owner=registerCommand.getOwner();
    this.serialCode=registerCommand.getSerialCode();
    this.companyPhone= registerCommand.getCompanyPhone();
    this.address=registerCommand.getAddress();
    this.setModifiedDate();
    }
}
