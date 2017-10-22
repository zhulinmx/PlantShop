package com.controller.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.MessageInfo;
import com.domain.MessageQueryCondition;
import com.domain.PlantInfo;
import com.domain.PlantInfoConditon;
import com.domain.PropagateQueryCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.message.persistent.Message;
import com.mapper.plant.persistent.PlantCategory;
import com.mapper.propagate.persistent.Propagate;
import com.mapper.user.persistent.User;
import com.service.message.MessageService;
import com.service.plant.PlantService;
import com.service.propagate.PropagateService;
import com.service.user.UserService;
import com.util.IntegerUtils;

@Controller
@RequestMapping
public class HomeController {
	
	@Resource
	private PlantService plantService;
	
	@Resource
	private PropagateService propagateService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MessageService messageService;
	
	
	@RequestMapping("/tohome.do")
    public String toHome(HttpServletRequest request,Model model){  
		Integer page = new Integer(1);
		PlantInfoConditon plantCondition = new PlantInfoConditon();
		Page<PlantInfo> plantList = PageHelper.startPage(page,40);
		plantService.queryPlanInfoByCondition(plantCondition);
		PropagateQueryCondition condition = new PropagateQueryCondition();
		Page<Propagate> propagateList = PageHelper.startPage(page,10);
		condition.setStatus(new Integer(1));
		propagateService.queryPropagate(condition);
		Page<PlantCategory> categories = PageHelper.startPage(page,10);
		plantService.queryPlantCategory();
		model.addAttribute("plantList", plantList);
		model.addAttribute("propagateList", propagateList);
		model.addAttribute("categories", categories);
		return "home";  
    }
	
	
	@RequestMapping("/searchPlanDetail.do")
    public String searchPlanDetail(HttpServletRequest request,Model model){ 
		Integer page = new Integer(1);
		PlantInfoConditon plantCondition = new PlantInfoConditon();
		plantCondition.setKey(request.getParameter("key"));
		plantCondition.setPlantId(IntegerUtils.StrToInteger(request.getParameter("id")));
		Page<PlantInfo> plantList = PageHelper.startPage(page,5);
		plantService.queryPlanInfoByCondition(plantCondition);
		model.addAttribute("plantList", plantList);
		return "searchPlanDetail";
    }
	
	@RequestMapping("/searchPlan.do")
    public String searchPlan(HttpServletRequest request,Model model){ 
		Integer page = new Integer(1);
		PlantInfoConditon plantCondition = new PlantInfoConditon();
		plantCondition.setCategoryId(request.getParameter("cid"));
		plantCondition.setKey(request.getParameter("key"));
		Page<PlantInfo> plantList = PageHelper.startPage(page,20);
		plantService.queryPlanInfoByCondition(plantCondition);
		model.addAttribute("plantList", plantList);
		return "searchPlan";
    }
	
	@RequestMapping("/toaddmessage.do")
    public String toAddMessage(HttpServletRequest request,Model model){  
        return "message";
    }
	
	@RequestMapping("/toDetailMessage.do")
	public String toDetailMessage(HttpServletRequest request,Model model) {
		Integer messageId = IntegerUtils.StrToInteger(request.getParameter("id"));
		List<MessageInfo> infos = messageService.queryMessgeInfo(messageId);
		model.addAttribute("infos", infos);
		model.addAttribute("id", messageId);
		return "detailmessage";
	}
	
	@RequestMapping("/tomessage.do")
    public String toMessage(HttpServletRequest request,Model model){
		MessageQueryCondition condition = new MessageQueryCondition();
		condition.setFlag(new Integer(1));
		List<Message> infos = messageService.queryMessage(condition);
		model.addAttribute("infos", infos);
        return "message";
    }
	
	@RequestMapping("/toredister.do")
    public String toRedister(HttpServletRequest request,Model model){  
		return "user/customer/addCustomer";
    }
	
	@RequestMapping("/redister.do")
	public String redister(HttpServletRequest request,Model model){
		try {
    		User user = new User();
        	user.setDep(request.getParameter("dep"));
        	user.setEmail(request.getParameter("email"));
        	user.setPassword(request.getParameter("password"));
        	user.setRemark(request.getParameter("remark"));
        	user.setRole(new Integer(1));
        	user.setStatus(Integer.parseInt(request.getParameter("status")));
        	user.setTellphone(request.getParameter("tellphone"));
        	user.setUsername(request.getParameter("userName"));
        	userService.insertUser(user);
        	return "home";
		} catch (Exception e) {
			return "error";
		}
	}
	
	@RequestMapping("/topropagatedetail.do")
	public String topropagatedetail(HttpServletRequest request,Model model) {
		PropagateQueryCondition condition = new PropagateQueryCondition();
		condition.setId(request.getParameter("id"));
		List<Propagate> list = propagateService.queryPropagate(condition);
		model.addAttribute("list", list);
		return "searchPropagateDetail";
	}
	
	@RequestMapping("/res.do")
    public String toResponseMessage(HttpServletRequest request,Model model){
		try {
			if (request.getParameter("userid")==null || request.getParameter("userid") =="") {
				return "errorlogin";
			}
			Integer id = IntegerUtils.StrToInteger(request.getParameter("mid"));
			Message message = new Message();
			message.setIp(request.getParameter("ip"));
			message.setParentid(Integer.parseInt(request.getParameter("parentid")));
			message.setStatus(new Integer("1"));
			message.setTitle(request.getParameter("title"));
			message.setMessage(request.getParameter("message1"));
			message.setUserid(Integer.parseInt(request.getParameter("userid")));
			messageService.insertMessage(message);
        	return "redirect:toDetailMessage.do?id="+id; 
		} catch (Exception e) {
			return "error";
		}
    }
	
	@RequestMapping("/toaddmess.do")
	public String toAddMess(HttpServletRequest request,Model model) {
		return "addMessage";
	}
	
	@RequestMapping("/propagate.do")
	public String propagate(HttpServletRequest request,Model model) {
		Integer page = new Integer(1);
		PropagateQueryCondition condition = new PropagateQueryCondition();
		condition.setStatus(new Integer(1));
		Page<Propagate> propagateList = PageHelper.startPage(page,30);
		propagateService.queryPropagate(condition);
		model.addAttribute("propagateList", propagateList);
		return "propagate";
	}
}
