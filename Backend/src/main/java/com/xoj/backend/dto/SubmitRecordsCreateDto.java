package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRecordsCreateDto {
    @NotNull
    private Long questionId;

    private String questionName;

    @NotNull
    private Long userId;

    private String lang;

    @NotNull
    private Integer result;

    private Integer timeCost;

    private Double memoryCost;

    private String codes;
}
