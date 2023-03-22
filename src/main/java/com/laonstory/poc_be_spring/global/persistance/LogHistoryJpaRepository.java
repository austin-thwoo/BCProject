package com.laonstory.poc_be_spring.global.persistance;

import com.laonstory.poc_be_spring.global.domain.LogHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogHistoryJpaRepository extends JpaRepository<LogHistory,Long> {

}
