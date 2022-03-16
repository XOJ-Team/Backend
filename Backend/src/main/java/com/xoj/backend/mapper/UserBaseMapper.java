package com.xoj.backend.mapper;

import com.xoj.backend.entity.UserBase;
import com.xoj.backend.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 1iin
 */
@Mapper
public interface UserBaseMapper extends BaseMapper<UserBase> {

    UserModel selectUser(@Param("user_id") String userId);

    UserModel selectUserDeleted(@Param("user_id") String userId);
}
