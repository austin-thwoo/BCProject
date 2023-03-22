package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementJpaRepository extends JpaRepository<Requirement, Long> {
}
