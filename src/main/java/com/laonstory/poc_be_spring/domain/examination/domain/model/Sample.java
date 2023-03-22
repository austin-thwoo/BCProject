package com.laonstory.poc_be_spring.domain.examination.domain.model;


import lombok.Getter;


import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable

public class Sample {

    private String smpName;
    private LocalDateTime smpInDate;
    private LocalDateTime smpOutDate;
    private Long smpAmount;
    private Boolean smpComeIn;
}
