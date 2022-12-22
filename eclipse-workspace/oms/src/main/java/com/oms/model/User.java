package com.oms.model;

public class User {
	private int userId;
	private int role_id;
	private String username;
	private String password;
	
	public User() {}

	public User(int userId, int role_id, String username, String password) {
		super();
		this.userId = userId;
		this.role_id = role_id;
		this.username = username;
		this.password = password;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
