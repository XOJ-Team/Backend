package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author 1iin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCompetition {
    @Id
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "competition_id")
    private Long competitionId;

    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "score")
    private Integer score;

    @Column(name = "penalty")
    private Integer penalty;

    @Column(name = "wrong")
    private Integer wrong;
}
