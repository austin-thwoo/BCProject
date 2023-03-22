package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Examination;
import com.laonstory.poc_be_spring.domain.examination.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestJpaRepository extends JpaRepository<Test, Long> {
    void deleteAllByIdIn(List<Long> testIds);
}
