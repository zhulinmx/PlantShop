package com.controller.message;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.MessageQueryCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.message.persistent.Message;
import com.service.message.MessageService;
import com.util.DateUtils;
import com.util.IntegerUtils;

@Controller
@RequestMapping
public class MessageController {
	
	@Resource
	private MessageService service;
	
	@RequestMapping("/searchMessage.do")
    public String tosearchMessage(HttpServletRequest request,Model model) throws Exception{
		String flag = request.getParameter("flag");
		Integer page = new Integer(1);
		Integer status = IntegerUtils.StrToInteger(request.getParameter("status"));
		String title = request.getParameter("title");
		MessageQueryCondition condition = new MessageQueryCondition();
		if (flag != null) {
			page = Integer.parseInt(request.getParameter("page"));
			condition.setTitle(title);
        	condition.setStatus(status);
		}
		condition.setFlag(new Integer(1));
		Page<Message> messageList = PageHelper.startPage(page,5);
		service.queryMessage(condition);
		model.addAttribute("messageList", messageList);
		model.addAttribute("pages", messageList.getPages());
		model.addAttribute("title", title);
    	model.addAttribute("status", status);
        return "message/searchMessage"; 
    }
    
    @RequestMapping("/detailMessage.do")
    public String toDetailMessage(HttpServletRequest request,Model model){ 
		Integer messageId = IntegerUtils.StrToInteger((request.getParameter("id")));
		if (messageId == null) {
			return "message/searchMessage";
		}
		Message message = service.queryMessageById(messageId);
		MessageQueryCondition condition = new MessageQueryCondition();
		condition.setParentid(messageId);
		List<Message> message2 = service.queryMessage(condition);
		Message mess = new Message();
		if (message2.size() > 0) {
			mess = message2.get(0);
		}
		String dateStr = null;
		if (message.getCeratetime() != null) {
			dateStr = DateUtils.unixTimestampToDate2(message.getCeratetime().getTime());
		}
		String dateStr2 = null;
		if (mess.getCeratetime() != null) {
			dateStr2 = DateUtils.unixTimestampToDate2(mess.getCeratetime().getTime());
		}
		model.addAttribute("dateStr", dateStr);
		model.addAttribute("dateStr2", dateStr2);
		model.addAttribute("message", message);
		model.addAttribute("mess", mess);
        return "message/detailMessage";
    }
    
    @RequestMapping("/responseMessage.do")
    public String toResponseMessage(HttpServletRequest request,Model model){
    	String flag = request.getParameter("flag");
    	if (flag == null) {
    		try {
    			Message message = new Message();
    			message.setIp(request.getParameter("ip"));
    			message.setParentid(Integer.parseInt(request.getParameter("parentid")));
    			message.setStatus(new Integer("1"));
    			message.setTitle(request.getParameter("title"));
    			message.setMessage(request.getParameter("message"));
    			if (request.getParameter("userid") == null || request.getParameter("userid") == "") {
    				return "errorlogin";
				}
    			message.setUserid(Integer.parseInt(request.getParameter("userid")));
            	service.insertMessage(message);
            	service.updateParentMessage(Integer.parseInt(request.getParameter("parentid")));
            	 return "redirect:searchMessage.do"; 
    		} catch (Exception e) {
    			return "error";
    		}
    	}
    	Integer messageId = Integer.parseInt(request.getParameter("id"));
		Message mess = service.queryMessageById(messageId);
    	model.addAttribute("mess", mess);
        return "message/responseMessage";
    }
    
    @RequestMapping("/addMessage.do")
    public String toAddMessage(HttpServletRequest request,Model model){
		Message message = new Message();
		message.setStatus(new Integer(0));
		message.setTitle(request.getParameter("title"));
		message.setMessage(request.getParameter("message"));
		message.setUserid(IntegerUtils.StrToInteger(request.getParameter("userid")));
    	service.insertMessage(message);
    	return "redirect:tomessage.do"; 
    }

}
