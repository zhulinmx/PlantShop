package com.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.UserQueryCondition;
import com.mapper.user.persistent.User;

public interface UserService {
	
	public User getUserById(int userId);

	public List<User> queryUser(UserQueryCondition confition);

	public void insertUser(User user);

	public User queryUserById(Integer userId);

	public void updateUserInfo(User userInfo);
	
	public void exportUserInfo(HttpServletRequest request, 
			HttpServletResponse response, List<User> list) throws Exception ;
}
