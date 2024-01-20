package com.example.rateUniversityApplication.dao;

public class UserModel {

	public String username;
	public String password;
	
	public UserModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
