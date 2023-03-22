package com.laonstory.poc_be_spring.domain.standard.persistance;

import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StdNationJpaRepository extends JpaRepository<StdNation,Long> {
    boolean existsByName(String name);
}
