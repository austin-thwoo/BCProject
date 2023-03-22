package com.laonstory.poc_be_spring.domain.user.persistance;

import com.laonstory.poc_be_spring.domain.user.domain.EmailConfirm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailConfirmJpaRepository extends JpaRepository<EmailConfirm,Long> {
    EmailConfirm findByEmail(String email);
}
