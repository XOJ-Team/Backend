package com.xoj.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCompetitionModifyDto {
    private Long id;

    private Long questionId;

    private Long competitionId;

    private Byte score;
}
