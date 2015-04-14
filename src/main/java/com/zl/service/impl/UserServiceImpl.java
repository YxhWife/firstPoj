package com.zl.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.dao.UserDao;
import com.zl.domain.User;
import com.zl.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getAllUsers() {
		return userDao.getUsers();
	}
	@Override
	public void insertUser() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "GengMou");
		map.put("password", "2222");
		userDao.inserUser(map);
	}
}
