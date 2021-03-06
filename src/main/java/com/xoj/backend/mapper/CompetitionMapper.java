package com.xoj.backend.mapper;

import com.xoj.backend.entity.Competition;
import com.xoj.backend.model.CompetitionModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 1iin
 */
@Mapper
public interface CompetitionMapper extends BaseMapper<Competition> {
    CompetitionModel selectCompetition(@Param("id") Long id);

    List<CompetitionModel> selectCompetitions();

    CompetitionModel selectByStartTime(@Param("start_time") String startTime);

    List<CompetitionModel> selectByEndTime(@Param("end_time") String endTime);
}