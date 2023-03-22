package com.laonstory.poc_be_spring.domain.standard.dto.response;

import com.laonstory.poc_be_spring.domain.standard.domain.StdMachine;
import com.laonstory.poc_be_spring.domain.standard.domain.StdNation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StdMachineResponse {
    private Long stdMachineId;
    private String stdMachineName;


    public StdMachineResponse(StdMachine stdMachine) {
        this.stdMachineId = stdMachine.getId();
        this.stdMachineName = stdMachine.getName();

    }

}
