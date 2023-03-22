package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.ExaminationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminationHistoryJpaRepository extends JpaRepository<ExaminationHistory, Long> {
}
