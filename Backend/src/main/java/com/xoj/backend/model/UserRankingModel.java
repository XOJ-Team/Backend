package com.xoj.backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRankingModel {
    private long userId;

    private String name;

    private Integer score;

    private Integer ranking;

    private Integer solvedNumber;

    private String intro;
}
