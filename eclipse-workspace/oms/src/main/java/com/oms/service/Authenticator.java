package com.oms.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.oms.model.Login;

public class Authenticator {
	
	public Login authenticate(String username, String password) {
		
		DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null;
		
		int userIdDB = -1;
		int role_id = -1;
		String usernameDB = "";
        String passwordDB = "";
        int status = -1;
        
        Login loginBean = new Login();
        boolean isExist = false;
        
        try {
        	conn = DB.getConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM users_1";
			
			rs = stmt.executeQuery(query);

			while(rs.next()) {
				
				usernameDB = rs.getString("username");
				passwordDB = rs.getString("password");
				
				if(username.equals(usernameDB)) {
					if(password.equals(passwordDB)) {
						usernameDB = rs.getString("username");
						passwordDB = rs.getString("password");
						userIdDB = rs.getInt("user_id");
						role_id = rs.getInt("role_id");
						status = rs.getInt("user_status");
						isExist = true;
						break;
					}
						
				}
			
			}

			loginBean.setUserId(userIdDB);
			loginBean.setUsername(usernameDB);
			loginBean.setPassword(passwordDB);
			loginBean.setRoleId(role_id);
			loginBean.setStatus(status);
			loginBean.setSuccess(isExist);
			
        }catch(Exception e) {
        	System.out.println("error: "+e);
        }
        
        return loginBean;
	}
}
