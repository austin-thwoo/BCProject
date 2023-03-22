package com.laonstory.poc_be_spring.domain.standard.persistance;

import com.laonstory.poc_be_spring.domain.standard.domain.StdLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StdLineJpaRepository extends JpaRepository<StdLine,Long> {
    boolean existsByNameAndStdMachineId(String name,Long stdMachineId);
}
