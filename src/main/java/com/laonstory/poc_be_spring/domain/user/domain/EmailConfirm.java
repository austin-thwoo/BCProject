package com.laonstory.poc_be_spring.domain.user.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "T_EMAIL_CONFIRM")
public class EmailConfirm{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @OneToMany(mappedBy = "emailConfirm",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private final List<PassCode> passCode =new ArrayList<>();


    public static EmailConfirm create(String userEmail) {
        return EmailConfirm.builder().email(userEmail).build();
    }

    public void addPassCode(PassCode passCode) {
        this.passCode.add(passCode);
        passCode.addEmailConfirm(this);

    }
}
