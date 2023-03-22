package com.laonstory.poc_be_spring.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="T_PASS_CODE")
public class PassCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private EmailConfirm emailConfirm;

    private LocalDateTime createDate;


    public static PassCode create(String code) {
        return PassCode.builder().code(code).createDate(LocalDateTime.now()).build();
    }


    public void addEmailConfirm(EmailConfirm emailConfirm) {
        this.emailConfirm=emailConfirm;
    }
}
