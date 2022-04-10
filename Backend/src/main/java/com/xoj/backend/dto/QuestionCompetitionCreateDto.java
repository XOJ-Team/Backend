package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCompetitionCreateDto {
    @NotNull
    private Long questionId;

    @NotNull
    private Long competitionId;

    private Byte score;
}
