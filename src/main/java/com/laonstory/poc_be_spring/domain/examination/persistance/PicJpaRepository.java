package com.laonstory.poc_be_spring.domain.examination.persistance;

import com.laonstory.poc_be_spring.domain.examination.domain.Pic;
import com.laonstory.poc_be_spring.domain.examination.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicJpaRepository extends JpaRepository<Pic, Long> {
    void deleteAllByIdIn(List<Long> ids);
}
