package cn.com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.dao.UserMapper;
import cn.com.model.User;
import cn.com.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired 
	private UserMapper userMapper;
	public User getUserById(int Id){
		return userMapper.getUserById(Id);
	}
}
