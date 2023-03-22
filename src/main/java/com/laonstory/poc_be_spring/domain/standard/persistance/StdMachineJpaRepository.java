package com.laonstory.poc_be_spring.domain.standard.persistance;

import com.laonstory.poc_be_spring.domain.standard.domain.StdMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StdMachineJpaRepository extends JpaRepository<StdMachine, Long> {
    boolean existsByNameAndStdCertifyId(String name,Long stdCertifyId);
}
