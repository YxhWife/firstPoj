package com.zl.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zl.dao.UserDao;
import com.zl.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSessionTemplate  sqlSessionTemplate;
	
	public List<User> getUsers(){
		List<User> userList = sqlSessionTemplate.selectList("queryAllUsers");
		return userList;
	}

	@Override
	public void inserUser(Map<String, Object> map) {
		sqlSessionTemplate.insert("insertUser", map);
	}

}
