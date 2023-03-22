package com.laonstory.poc_be_spring.domain.company.persistance;

import com.laonstory.poc_be_spring.domain.company.domain.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {

    Optional<Company> findBySerialCode(String serialCode);

}
