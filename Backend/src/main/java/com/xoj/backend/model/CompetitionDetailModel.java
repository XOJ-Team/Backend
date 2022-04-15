package com.xoj.backend.model;

import com.xoj.backend.entity.QuestionCompetition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDetailModel {
    CompetitionModel competitionModel;

    List<QuestionCompetition> links;

}
