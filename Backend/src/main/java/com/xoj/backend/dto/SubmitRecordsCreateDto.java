package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmitRecordsCreateDto {
    private Long questionId;

    private String questionName;

    private Long userId;

    private String lang;

    private Integer result;

    private Integer timeCost;

    private Double memoryCost;

    private String codes;
}
