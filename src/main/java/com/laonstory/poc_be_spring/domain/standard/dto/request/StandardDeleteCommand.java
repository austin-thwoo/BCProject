package com.laonstory.poc_be_spring.domain.standard.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StandardDeleteCommand {
    private List<Long> id;
}
