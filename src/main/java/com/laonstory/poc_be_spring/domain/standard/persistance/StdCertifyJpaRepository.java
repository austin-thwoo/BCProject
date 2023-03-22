package com.laonstory.poc_be_spring.domain.standard.persistance;

import com.laonstory.poc_be_spring.domain.standard.domain.StdCertify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StdCertifyJpaRepository extends JpaRepository<StdCertify,Long> {
    boolean existsByNameAndStdNationId(String name,Long nationId);
}
