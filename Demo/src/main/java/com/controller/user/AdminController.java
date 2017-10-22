package com.controller.user;

import java.text.ParseException;
import java.util.Date;
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
public class AdminController {
	
	@Resource
	private UserService userService;
    
    @RequestMapping("/searchAdmin.do")
    public String toAdmin(HttpServletRequest request,Model model) throws Exception{
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
    	Page<User> userList = PageHelper.startPage(page,20);
    	condition.setRole(new Integer(0));
    	userService.queryUser(condition);
    	model.addAttribute("userList", userList);
    	model.addAttribute("pages", userList.getPages());
    	model.addAttribute("userName", userName);
    	model.addAttribute("status", status);
        return "user/admin/searchAdmin";
    }
    
    @RequestMapping("/toAddAdmin.do")
    public String toAddAdmin(HttpServletRequest request,Model model){
    	return "user/admin/addAdmin"; //跳转到addAdmin.jsp
    }
    
    @RequestMapping("/addAdmin.do")
    public String addAdmin(HttpServletRequest request,Model model){
    	try {
    		User user = new User();
        	user.setDep(request.getParameter("dep"));
        	user.setEmail(request.getParameter("email"));
        	user.setPassword(request.getParameter("password"));
        	user.setLoginname(request.getParameter("loginname"));
        	user.setRemark(request.getParameter("remark"));
        	user.setRole(new Integer(0));
        	user.setStatus(IntegerUtils.StrToInteger(request.getParameter("status")));
        	user.setTellphone(request.getParameter("tellphone"));
        	user.setJointime(DateUtils.parseyymmdd(request.getParameter("jointime")));
        	user.setBirthday(DateUtils.parseyymmdd(request.getParameter("birthday")));
        	user.setUsername(request.getParameter("username"));
        	userService.insertUser(user);
    		} catch (Exception e) {
    			return "error";
    		}
        return "redirect:searchAdmin.do"; 
    }
    
    @RequestMapping("/toDetailAdmin.do")
    public String toDetailAdmin(HttpServletRequest request,Model model) throws ParseException{
		Integer userId = Integer.parseInt(request.getParameter("id"));
    	User user = userService.queryUserById(userId);
    	String dateStr = null;
    	Date birthDay = user.getBirthday();
    	if (birthDay!=null) {
    		dateStr = DateUtils.unixTimestampToDate(birthDay.getTime());
		}
    	model.addAttribute("user", user);
    	model.addAttribute("dateStr", dateStr);
        return "user/admin/detailAdmin"; 
    }
    
    @RequestMapping("/toEditAdmin.do")
    public String toEditAdmin(HttpServletRequest request,Model model) throws Exception{
    	Integer userId = Integer.parseInt(request.getParameter("id"));
    	User user = userService.queryUserById(userId);
    	String dateStr = null;
    	Date birthDay = user.getBirthday();
    	if (birthDay!=null) {
    		dateStr = DateUtils.unixTimestampToDate(birthDay.getTime());
    	}
    	model.addAttribute("dateStr", dateStr);
    	if(user.equals(null)){
			return "addAdmin";
		}
    	model.addAttribute("user", user);
    	return "user/admin/editAdmin";
	}
    
    @RequestMapping("/editAdmin.do")
    public String editAdmin(HttpServletRequest request,Model model) throws Exception{
    	Integer userId = Integer.parseInt(request.getParameter("userid"));
    	User user = userService.queryUserById(userId);
    	try {
    		if(user.equals(null)){
    			return "addAdmin";
    		}
    		User userInfo = new User();
    		userInfo.setDep(request.getParameter("dep"));
    		userInfo.setEmail(request.getParameter("email"));
    		userInfo.setPassword(request.getParameter("password"));
    		userInfo.setRemark(request.getParameter("remark"));
    		userInfo.setStatus(Integer.parseInt(request.getParameter("status")));
    		userInfo.setTellphone(request.getParameter("tellphone"));
    		userInfo.setUsername(request.getParameter("username"));
    		userInfo.setBirthday(DateUtils.parseyymmdd(request.getParameter("birthday")));
    		userInfo.setUserid(userId);
        	userService.updateUserInfo(userInfo);
		} catch (Exception e) {
			return "error";
		}
    	return "redirect:searchAdmin.do"; 
    }
    
    @RequestMapping("/exportAdmin.do")
    public void exportAdmin(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	try {
    		UserQueryCondition condition = new UserQueryCondition();
        	condition.setUsername(request.getParameter("userName"));
        	String status = request.getParameter("status");
        	if (status != null) {
        		condition.setStatus(Integer.parseInt(status));
    		}
        	List<User> userList = userService.queryUser(condition);
        	userService.exportUserInfo(request, response, userList);
		} catch (Exception e) {
		}
    }
    
}
