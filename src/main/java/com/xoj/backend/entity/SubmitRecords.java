package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRecords {
    @Id
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "question_name")
    private String questionName;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "create_time")
    private Date createTime;

    private String lang;

    private Integer result;

    @Column(name = "time_cost")
    private Integer timeCost;

    @Column(name = "memory_cost")
    private Double memoryCost;

    private String comments;

    private String codes;

    @Column(name = "competition_id")
    private Long competitionId;
}