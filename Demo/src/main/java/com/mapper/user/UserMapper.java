package com.mapper.user;

import java.util.List;

import com.domain.UserQueryCondition;
import com.mapper.user.persistent.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> queryUserByCondition(UserQueryCondition condition);
}