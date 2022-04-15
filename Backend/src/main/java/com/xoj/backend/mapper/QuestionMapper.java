package com.xoj.backend.mapper;

import com.xoj.backend.entity.Question;
import com.xoj.backend.model.QuestionModel;
import com.xoj.backend.model.QuestionSearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    List<QuestionModel> selectQuestions(@Param("ids") List<Long> ids);

    QuestionModel selectQuestion(@Param("id")Long id);

    List<QuestionModel> selectShowQuestions();

    List<QuestionModel> selectAllQuestions();

    List<QuestionSearchModel> selectSearch(@Param("number") Long number,
                                           @Param("text") String text);
}