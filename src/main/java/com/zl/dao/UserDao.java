package com.zl.dao;

import java.util.List;
import java.util.Map;

import com.zl.domain.User;

public interface UserDao {
	
	public List<User> getUsers(); 
	public void inserUser(Map<String, Object> map);

}
