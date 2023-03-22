package com.laonstory.poc_be_spring.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DateDTO {
    private LocalDateTime startedDate;
    private LocalDateTime endedDate;

    public void setStartedDate(LocalDate startDate){
        this.startedDate=startDate.atStartOfDay();
    }
    public void setEndedDate(LocalDate startDate){
        this.endedDate=startDate.atStartOfDay().plusDays(1);
    }

    public void setNull() {
        startedDate=null;
        endedDate=null;
    }
}
