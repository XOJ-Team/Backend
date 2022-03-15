package com.xoj.backend.mapper;

import com.xoj.backend.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

   // List<Question> selectQuestions(List<Long> questionIds);
}