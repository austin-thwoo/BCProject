package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Document;
import com.laonstory.poc_be_spring.domain.examination.domain.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentJpaRepository extends JpaRepository<Document, Long> {

    void deleteAllByExaminationIdIn(List<Long> examinationId);
}
