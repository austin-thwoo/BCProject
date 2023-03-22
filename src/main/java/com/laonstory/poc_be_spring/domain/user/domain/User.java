package com.laonstory.poc_be_spring.domain.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import com.laonstory.poc_be_spring.domain.user.dto.request.UserRegisterCommand;
import com.laonstory.poc_be_spring.domain.user.domain.common.UserType;
import com.laonstory.poc_be_spring.domain.user.exception.DeletedUserException;
import com.laonstory.poc_be_spring.global.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;




@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_USER")
public class User extends BaseTimeEntity implements UserDetails {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;
  private String userName;
  private String password;
  private String name;
  private String userPhone;
  private String userEmail;
  private String authentification;

  @ManyToOne(fetch = FetchType.LAZY)
  private Company company;
  private String part;
  private String position;
  private String didAddress;


  @Enumerated(EnumType.STRING)
  private UserType userType;


  @ElementCollection(fetch = FetchType.EAGER)
  @JoinTable(name = "T_USER_ROLES")
  @Builder.Default
  private final List<String> roles = new ArrayList<>();

  public static User create(UserRegisterCommand registerCommand, Company company) {
    return User.builder()
            .userName(registerCommand.getUserName())
            .password(registerCommand.getPassword())
            .name(registerCommand.getName())
            .userPhone(registerCommand.getUserPhone())
            .userEmail(registerCommand.getUserEmail())
            .company(company)
            .part(registerCommand.getPart())
            .position(registerCommand.getPosition())
            .authentification(registerCommand.getAuthentification())
            .didAddress(registerCommand.getDidAddress())
            .userType(registerCommand.getUserType())
            .build();
  }

  public void update(UserRegisterCommand command, Company company) {
   this.name=command.getName();
   this.userPhone=command.getUserPhone();
   this.userEmail= command.getUserEmail();
//   this.authentification=command.getAuthentification();

   this.company=company;
   this.part=command.getPart();
   this.position=command.getPosition();
   this.userType=command.getUserType();
   this.userType=command.getUserType();
   this.setModifiedDate();
  }
  public void update2(UserRegisterCommand command, Company company) {
    this.name=command.getName();
    this.userPhone=command.getUserPhone();
    this.userEmail= command.getUserEmail();
//   this.authentification=command.getAuthentification();
    this.password= command.getPassword();
    this.company=company;
    this.part=command.getPart();
    this.position=command.getPosition();
    this.userType=command.getUserType();
    this.setModifiedDate();
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }


  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void addRole(String s) {
    this.roles.add(s);
  }

  public void invalid() {
    if (this.getDeletedDate()!=null){
     throw new DeletedUserException(this.userName);
    }
  }

    public void changePassword(String encodedPassword) {
      this.password = encodedPassword;

  }

    public void addDid(String didAddress) {

    this.didAddress=didAddress;
    }

    public void modifyEmail(String userEmail) {
    this.userEmail=userEmail;
    }

    public void updateAuthentication(String code) {
    this.authentification=code;
    }


}
