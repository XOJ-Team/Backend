package com.xoj.backend.mapper;

import com.xoj.backend.entity.SubmitRecords;
import com.xoj.backend.model.SubmitRecordsModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface SubmitRecordsMapper extends BaseMapper<SubmitRecords>{

    List<SubmitRecordsModel> selectQuestionRecords(@Param("questionId") Long questionId, 
                                                   @Param("id") Long id);

    List<SubmitRecordsModel> selectUserRecords(@Param("id") Long id);

    List<Long> selectQuestionIds(@Param("userId") Long userId);

    SubmitRecordsModel selectOneRecord(Long id);
}