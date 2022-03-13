package com.xoj.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmitRecords {
    @Id
    private Long id;

    private Long questionId;

    private String questionName;

    private Long userId;

    private Date createTime;

    private String lang;

    private Integer result;

    private Integer timeCost;

    private Double memoryCost;

    private String comments;
}