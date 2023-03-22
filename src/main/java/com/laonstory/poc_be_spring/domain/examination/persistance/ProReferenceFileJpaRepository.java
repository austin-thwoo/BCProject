package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.ProReferencefile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProReferenceFileJpaRepository extends JpaRepository<ProReferencefile, Long> {
}
