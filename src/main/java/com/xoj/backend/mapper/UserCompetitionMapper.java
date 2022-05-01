package com.xoj.backend.mapper;

import com.xoj.backend.entity.UserCompetition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 1iin
 */
@Mapper
public interface UserCompetitionMapper extends BaseMapper<UserCompetition> {
    List<UserCompetition> selectUserCompetition();
}
