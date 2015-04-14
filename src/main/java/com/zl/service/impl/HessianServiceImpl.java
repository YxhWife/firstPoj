package com.zl.service.impl;

import org.springframework.stereotype.Service;

import com.zl.domain.User;
import com.zl.service.HessianService;

@Service
public class HessianServiceImpl implements HessianService{

	public String sayHi(String name) {
		return "Hi, " + name;
	}

//	public User getUser(String userName,String password) {
//		return new User(userName, password);
//	}

}
