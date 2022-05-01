package com.xoj.backend.mapper;

import com.xoj.backend.entity.QuestionCompetition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 1iin
 */
@Mapper
public interface QuestionCompetitionMapper extends BaseMapper<QuestionCompetition> {
    Long selectCompetitionId(@Param("questionId") Long questionId);
}
