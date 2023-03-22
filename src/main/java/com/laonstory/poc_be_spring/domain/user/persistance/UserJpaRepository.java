package com.laonstory.poc_be_spring.domain.user.persistance;

import com.laonstory.poc_be_spring.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID valueOf);
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String userEmail);
}
