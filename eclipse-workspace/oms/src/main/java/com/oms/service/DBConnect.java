package com.oms.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private String server;
	private String database;
	private String user;
	private String password;
	
	public DBConnect() {}
	
	public DBConnect(String server, String database, String user, String password) {
		super();
		this.server = server;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(getJDBC_DRIVER());
			String url = "jdbc:oracle:thin:@"+this.getServer()+":1521:"+this.getDatabase();
			conn = DriverManager.getConnection(url, this.getUser(), this.getPassword());
		}catch(Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}
	
	
}
