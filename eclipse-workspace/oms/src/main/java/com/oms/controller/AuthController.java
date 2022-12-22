package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oms.model.Login;
import com.oms.model.User;
import com.oms.service.Authenticator;

public class AuthController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		RequestDispatcher dispatcher = null;
		
		Authenticator authenticator = new Authenticator();
		
		Login result = authenticator.authenticate(username, password);
		
		if(result.isSuccess()) {
			if(result.getStatus() == 1) {
				req.setAttribute("message", "This user is currently disabled, please contact administrator");
				dispatcher = req.getRequestDispatcher("message.jsp");
			}
			else if(result.getStatus() == 0) {
				
				User user = new User(result.getUserId(), result.getRoleId(), result.getUsername(), result.getPassword());
				if(user.getRole_id() == 1) {
					dispatcher = req.getRequestDispatcher("pages/admin/dashboard.jsp");
				}else if(user.getRole_id() == 2) {
					dispatcher = req.getRequestDispatcher("pages/producer/dashboard.jsp");
				}else if(user.getRole_id() == 3) {
					dispatcher = req.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
				}else if(user.getRole_id() == 4) {
					dispatcher = req.getRequestDispatcher("pages/auditor/dashboard.jsp");
				}
				
				req.setAttribute("user", user);
				HttpSession session = req.getSession();
				
				session.setAttribute("user", user);
				Cookie usernameCookie = new Cookie("userC", user.getUsername());
				usernameCookie.setMaxAge(1000000);
				
				Cookie passwordCookie = new Cookie("passwordC", user.getPassword());
				passwordCookie.setMaxAge(1000000);
				
				Cookie roleCookie = new Cookie("roleC", String.valueOf(user.getRole_id()));
				passwordCookie.setMaxAge(1000000);
				
				
				resp.addCookie(usernameCookie);
				resp.addCookie(passwordCookie);
				resp.addCookie(roleCookie);
			}
			
		}else {
			req.setAttribute("message", "Wrong credentials. Please try again");
			dispatcher = req.getRequestDispatcher("message.jsp");
		}
		
		dispatcher.forward(req, resp);
	}
}
