package com.service.message;

import java.util.List;

import com.domain.MessageInfo;
import com.domain.MessageQueryCondition;
import com.mapper.message.persistent.Message;

public interface MessageService {

	List<Message> queryMessage(MessageQueryCondition condition);

	Message queryMessageById(Integer messageId);

	void insertMessage(Message message);

	void updateParentMessage(int parentId);
	
	List<MessageInfo> queryMessgeInfo(int id);
	
	

}
