package com.mapper.message;

import java.util.List;

import com.domain.MessageQueryCondition;
import com.mapper.message.persistent.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

	List<Message> queryUserByCondition(MessageQueryCondition condition);

	List<Message> queryMessageInfo(int id);
}