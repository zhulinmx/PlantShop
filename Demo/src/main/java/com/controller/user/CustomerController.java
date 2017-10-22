package com.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.UserQueryCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.user.persistent.User;
import com.service.user.UserService;
import com.util.DateUtils;
import com.util.IntegerUtils;

@Controller
@RequestMapping
public class CustomerController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/tosearchcustomer.do")
    public String toSearchCustomer(HttpServletRequest request,Model model) throws Exception{ 
		String flag = request.getParameter("flag");
		Integer page = new Integer(1);
		String userName = request.getParameter("userName");
		Integer status = IntegerUtils.StrToInteger(request.getParameter("status"));
    	UserQueryCondition condition = new UserQueryCondition();
    	if (flag != null) {
    		page = Integer.parseInt(request.getParameter("page"));
        	condition.setUsername(userName);
        	condition.setStatus(status);
    	}
    	condition.setRole(new Integer(1));
    	Page<User> userList = PageHelper.startPage(page,20);
    	userService.queryUser(condition);
    	model.addAttribute("userList", userList);
    	model.addAttribute("pages", userList.getPages());
    	model.addAttribute("userName", userName);
    	model.addAttribute("status", status);
        return "user/customer/searchCustomer";
    }
	
	@RequestMapping("/addCustomer.do")
    public String toAddCustomer(HttpServletRequest request,Model model){
		try {
    		User user = new User();
        	user.setEmail(request.getParameter("email"));
        	user.setPassword(request.getParameter("password1"));
        	user.setLoginname(request.getParameter("loginname"));
        	user.setRole(new Integer(1));
        	user.setStatus(new Integer(1));
        	user.setDep("会员");
        	user.setTellphone(request.getParameter("tellphone"));
        	user.setUsername(request.getParameter("username"));
        	user.setBirthday(DateUtils.parseyymmdd(request.getParameter("birthday")));
        	userService.insertUser(user);
		} catch (Exception e) {
			return "error";
		}
		return "redirect:tohome.do"; 
    }
	
	@RequestMapping("/detailCustomer.do")
    public String toDetailCustomer(HttpServletRequest request,Model model){
		Integer userId = Integer.parseInt(request.getParameter("id"));
    	User user = userService.queryUserById(userId);
    	String dateStr = DateUtils.unixDateToDate(user.getBirthday());
    	String dateStr1 = DateUtils.unixDateToDate(user.getJointime());
    	model.addAttribute("dateStr", dateStr);
    	model.addAttribute("dateStr1", dateStr1);
    	model.addAttribute("user", user);
        return "user/customer/detailCustomer";
    }
	
	@RequestMapping("/toEditCustomer.do")
    public String toEditCustomer(HttpServletRequest request,Model model){  
		try {
    		Integer userId = Integer.parseInt(request.getParameter("id"));
    		User user = userService.queryUserById(userId);
    		if(user.equals(null)){
    			return "addCustomer";
    		}
        	String dateStr = DateUtils.unixDateToDate(user.getBirthday());
        	String dateStr1 = DateUtils.unixDateToDate(user.getJointime());
        	model.addAttribute("dateStr", dateStr);
        	model.addAttribute("dateStr1", dateStr1);
    		model.addAttribute("user", user);
		} catch (Exception e) {
			return "error";
		}
        return "user/customer/editCustomer";
    }
	
	@RequestMapping("/editCustomer.do")
    public String editCustomer(HttpServletRequest request,Model model){  
		try {
    		Integer userId = Integer.parseInt(request.getParameter("userid"));
    		User user = userService.queryUserById(userId);
    		if(user.equals(null)){
    			return "addCustomer";
    		}
    		User userInfo = new User();
    		userInfo.setEmail(request.getParameter("email"));
    		userInfo.setPassword(request.getParameter("password"));
    		userInfo.setRemark(request.getParameter("remark"));
    		userInfo.setRole(new Integer(1));
    		userInfo.setStatus(Integer.parseInt(request.getParameter("status")));
    		userInfo.setTellphone(request.getParameter("tellphone"));
    		userInfo.setUsername(request.getParameter("username"));
    		userInfo.setBirthday(DateUtils.stringToDate(request.getParameter("birthday")));
    		userInfo.setUserid(userId);
    		userService.updateUserInfo(userInfo);
		} catch (Exception e) {
			return "error";
		}
        return "redirect:tosearchcustomer.do";
    }
	
	@RequestMapping("/exportCustomer.do")
    public void exportCustomer(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	try {
    		UserQueryCondition condition = new UserQueryCondition();
        	condition.setUsername(request.getParameter("userName"));
        	String status = request.getParameter("status");
        	if (status != null) {
        		condition.setStatus(Integer.parseInt(status));
    		}
        	condition.setRole(new Integer(2));
        	List<User> userList = userService.queryUser(condition);
        	userService.exportUserInfo(request, response, userList);
		} catch (Exception e) {
		}
    }

}
