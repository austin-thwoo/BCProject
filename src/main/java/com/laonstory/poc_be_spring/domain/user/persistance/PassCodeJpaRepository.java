package com.laonstory.poc_be_spring.domain.user.persistance;

import com.laonstory.poc_be_spring.domain.user.domain.PassCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassCodeJpaRepository extends JpaRepository<PassCode, Long> {

}
