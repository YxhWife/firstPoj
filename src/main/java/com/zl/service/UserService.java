package com.zl.service;

import java.util.List;

import com.zl.domain.User;

public interface UserService {

	public List<User> getAllUsers();
	public void insertUser();
}
