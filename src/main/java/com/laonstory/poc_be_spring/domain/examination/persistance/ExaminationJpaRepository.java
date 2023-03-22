package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationJpaRepository extends JpaRepository<Examination, Long> {
}
