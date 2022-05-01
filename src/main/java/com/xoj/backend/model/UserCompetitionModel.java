package com.xoj.backend.model;

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
public class UserCompetitionModel {
    private Long id;

    private Long userId;

    private Long competitionId;

    private Integer score;

    private Integer penalty;
}
