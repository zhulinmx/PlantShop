package com.service.message.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.domain.MessageInfo;
import com.domain.MessageQueryCondition;
import com.mapper.message.MessageMapper;
import com.mapper.message.persistent.Message;
import com.mapper.user.persistent.User;
import com.service.message.MessageService;
import com.service.user.UserService;
import com.util.DateUtils;

@Repository
public class MessageServiceImpl implements MessageService {

	@Resource 
	private MessageMapper messageMapper;
	
	@Resource
	private UserService userService;
	
	@Override
	public List<Message> queryMessage(MessageQueryCondition condition) {
		List<Message> messageList = this.messageMapper.queryUserByCondition(condition);
		return messageList;
	}

	@Override
	public Message queryMessageById(Integer messageId) {
		return this.messageMapper.selectByPrimaryKey(messageId);
	}

	@Override
	public void insertMessage(Message message) {
		this.messageMapper.insert(message);
	}

	@Override
	public void updateParentMessage(int parentId) {
		Message message = new Message();  
		message.setId(parentId);
		message.setStatus(new Integer(1));
		this.messageMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	public List<MessageInfo> queryMessgeInfo(int id) {
		List<Message> messageList = this.messageMapper.queryMessageInfo(id);
		List<MessageInfo> infos = new ArrayList<>();
		for (Message message : messageList) {
			MessageInfo info = new MessageInfo();
			User user = new User();
			if (message.getUserid()!=null) {
				user = userService.queryUserById(message.getUserid());
			}
			info.setId(message.getId());
			info.setCeratetime(DateUtils.dateToString(message.getCeratetime()));
			info.setMessage(message.getMessage());
			info.setParentid(message.getParentid());
			info.setUserName(user!=null?user.getLoginname():null);
			infos.add(info);
		}
		return infos;
	}
	
}
