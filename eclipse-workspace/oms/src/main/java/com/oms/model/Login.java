package com.oms.model;

public class Login {
	private int userId;
	private String username;
	private String password;
	private int roleId;
	private int status;
	private boolean isSuccess;
	
	public Login() {
		
	}
	
	


	public Login(int userId, String username, String password, int roleId, int status, boolean isSuccess) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.status = status;
		this.isSuccess = isSuccess;
	}

	

	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}




	public boolean isSuccess() {
		return isSuccess;
	}




	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
	
	
}
