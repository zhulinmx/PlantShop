package com.service.user.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.domain.UserQueryCondition;
import com.mapper.user.UserMapper;
import com.mapper.user.persistent.User;
import com.service.user.UserService;
import com.util.CSVUtils;
import com.util.DateUtils;

@Repository
public class UserServiceImpl implements UserService {
	
	@Resource 
	private UserMapper userMapper;
	
	@Override  
    public User getUserById(int userId) {  
        return this.userMapper.selectByPrimaryKey(userId);
    }

	@Override
	public List<User> queryUser(UserQueryCondition condition) {
		List<User> userList = this.userMapper.queryUserByCondition(condition);
		return userList;
	}

	@Override
	public void insertUser(User user) {
		this.userMapper.insert(user);
	}

	@Override
	public User queryUserById(Integer userId) {
		User user = this.userMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public void updateUserInfo(User userInfo) {
		this.userMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public void exportUserInfo(HttpServletRequest request, HttpServletResponse response, List<User> list) throws Exception {
		//表头
		String sTitle = "用户Id,用户名,登录名,加入时间,联系方式,邮箱,出生年月,备注,状态";
		//保存csv文件名字
		String fName = "User";
		//每列对应属性
		String mapKey = "userid,loginname,username,jointime,tellphone,email,birthday,remark,status";
		List<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		try {
			for (User user : list) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("userid", String.valueOf(user.getUserid()));
				map.put("loginname", user.getLoginname());
				map.put("username", user.getUsername());
				map.put("jointime", DateUtils.dateToString(user.getJointime()));
				map.put("tellphone", user.getTellphone());
				map.put("email", user.getEmail());
				map.put("birthday", DateUtils.dateToString(user.getBirthday()));
				map.put("remark", user.getRemark());
				map.put("status", "0".equals(user.getStatus())?"无效":"有效");
				userList.add(map);
			}
			OutputStream os = response.getOutputStream();
			CSVUtils.responseSetProperties(fName, response);
			CSVUtils.doExport(userList, sTitle, mapKey, os);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}  
	  

}
