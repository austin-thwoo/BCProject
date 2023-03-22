package com.laonstory.poc_be_spring.domain.company.persistance;

import com.laonstory.poc_be_spring.domain.company.domain.SubCompany;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCompanyJpaRepository extends JpaRepository<SubCompany, Long> {

    Optional<SubCompany> findBySerialCode(String serialCode);

}
