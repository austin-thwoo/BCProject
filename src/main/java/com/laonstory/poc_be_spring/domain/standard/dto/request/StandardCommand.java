package com.laonstory.poc_be_spring.domain.standard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StandardCommand {
    private Long stdNationId;
    private Long stdCertifyId;
    private Long stdMachineId;
    private Long stdLineId;
    private String name;
}
