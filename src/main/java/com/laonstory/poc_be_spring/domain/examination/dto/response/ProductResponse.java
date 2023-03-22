package com.laonstory.poc_be_spring.domain.examination.dto.response;


import com.laonstory.poc_be_spring.domain.company.domain.Company;
import com.laonstory.poc_be_spring.domain.examination.domain.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductResponse {

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


    public ProductResponse(Product pro) {
        this.proName = pro.getProName();
        this.proAgent = pro.isProAgent();
        this.proExpectedDate = pro.getProExpectedDate() !=null? pro.getProExpectedDate():null;
        this.proNew = pro.isProNew();
        this.proChildName = pro.getProChildName() != null ? pro.getProChildName() : null;
        this.proModelName = pro.getProModelName();
        this.proModelType = pro.getProModelType() !=null ?pro.getProModelType():null;
        this.proCompletedDate = pro.getProCompletedDate() != null ? pro.getProCompletedDate() : null;
        this.proChildDiffer = pro.getProChildDiffer() != null ? pro.getProChildDiffer() : null;
        this.proSellNation = pro.getProSellNation();
        this.proStandard = pro.getProStandard();

    }
}
