package com.xoj.backend.component;

import com.xoj.backend.entity.QuestionCompetition;
import com.xoj.backend.mapper.CompetitionMapper;
import com.xoj.backend.model.CompetitionModel;
import com.xoj.backend.service.QuestionCompetitionService;
import com.xoj.backend.service.QuestionService;
import com.xoj.backend.util.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 1iin
 */
@Component
@AllArgsConstructor
public class ScheduledTask {
    private final CompetitionMapper mapper;

    private final QuestionCompetitionService questionCompetitionService;

    private final QuestionService questionService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void scheduledTask() {
        CompetitionModel competitionModel = mapper.selectByEndTime(DateUtils.date2StringFloor(new Date()));
        List<QuestionCompetition> list = questionCompetitionService.selectQuestionsByCompetition(competitionModel.getId());
        for (QuestionCompetition questionCompetition : list) {
            questionService.show(questionCompetition.getQuestionId());
        }
    }
}
