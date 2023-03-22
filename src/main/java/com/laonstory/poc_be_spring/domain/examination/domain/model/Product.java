package com.laonstory.poc_be_spring.domain.examination.domain.model;


import lombok.Getter;

import javax.persistence.Embeddable;

import java.time.LocalDateTime;

@Getter
@Embeddable
public class Product {

    private String proName;
    private boolean proAgent;
    private LocalDateTime proExpectedDate;
    private boolean proNew;
    private String proChildName;
    private String proModelName;
    private String proModelType;
    private LocalDateTime proCompletedDate;
    private String proChildDiffer;
    private String proSellNation;
    private String proStandard;

}
