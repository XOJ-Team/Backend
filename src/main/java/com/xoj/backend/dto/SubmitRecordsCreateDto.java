package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRecordsCreateDto {
    @NotNull
    private Long questionId;

    @NotEmpty
    private String questionName;

    @NotEmpty
    private String lang;

    @NotNull
    private Integer result;

    @NotNull
    private Integer timeCost;

    @NotNull
    private Double memoryCost;

    @NotEmpty
    private String codes;

    private Long competitionId;
}
