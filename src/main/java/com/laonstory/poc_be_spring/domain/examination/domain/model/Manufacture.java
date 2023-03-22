package com.laonstory.poc_be_spring.domain.examination.domain.model;


import com.laonstory.poc_be_spring.domain.company.domain.Company;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Embeddable
public class    Manufacture {


    private String mftManagerName;
    private String mftManagerEmail;
    private String mftManagerPhone;



}
