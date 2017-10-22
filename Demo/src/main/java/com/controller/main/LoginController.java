package com.controller.main;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.UserQueryCondition;
import com.mapper.user.persistent.User;
import com.service.user.UserService;

@Controller
@RequestMapping
public class LoginController {
	
	@Resource
	private UserService userService;
	
	 @RequestMapping(value="/toAdminlogin.do")
	public String toAdminLogin(HttpServletRequest request,Model model){
		 return "adminLogin";
	}
	
    @RequestMapping(value="/adminlogin.do")
    public String toLogin(HttpServletRequest request,Model model){
    	String loginName = request.getParameter("loginName");
    	String password = request.getParameter("password");
    	if (password == null || loginName == null) {
    		return "adminLogin";
		}
    	UserQueryCondition condition = new UserQueryCondition();
    	condition.setLoginname(loginName);
    	condition.setPassword(password);
    	condition.setStatus(new Integer(1));
    	condition.setFlag(new Integer(0));
    	List<User> user = userService.queryUser(condition);
    	if (CollectionUtils.isEmpty(user)) {
    		return "adminLogin";
		} else {
			request.getSession().setAttribute("user", user.get(0));
//			return "redirect:index.do?id="+user.get(0).getUserid();//redirect 重定向
			return "index";
		}
    	
    }
    
    @RequestMapping("/index.do")
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "index";  
    }
    
    @RequestMapping("/customerlogin.do")
    public String toCustomerLogin(HttpServletRequest request,Model model){
    	String flag = request.getParameter("flag");
    	if (flag == null) {
    		String loginName = request.getParameter("loginName");
        	String password = request.getParameter("password");
        	UserQueryCondition condition = new UserQueryCondition();
        	condition.setLoginname(loginName);
        	condition.setPassword(password);
        	condition.setRole(new Integer(1));
        	List<User> user = userService.queryUser(condition);
        	if (CollectionUtils.isEmpty(user)) {
        		return "customerLogin";
    		} else {
    			request.getSession().setAttribute("user", user.get(0));
    			return "redirect:tohome.do";//redirect 重定向
    		}
		} else {
			return "customerLogin";
		}
    }
    
    @RequestMapping("/doadminget.do")
    public String doAdminGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        HttpSession session = request.getSession(false);//防止创建Session  
        if(session == null){  
        	return "adminLogin"; 
        }  
        session.removeAttribute("user");  
        return "adminLogin"; 
    }
    
    @RequestMapping("/docustomerget.do")
    public String doCustomerGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        HttpSession session = request.getSession(false);//防止创建Session  
        if(session == null){  
        	return "customerLogin";
        }  
        session.removeAttribute("user");  
        return "customerLogin";
    }

}
