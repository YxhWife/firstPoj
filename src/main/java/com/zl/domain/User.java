package com.zl.domain;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1123331L;

	private String userName;
	private String password;
	private int id;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString(){
		return "userName=" + this.getUserName() + "password=" + this.getPassword();
	}
}
