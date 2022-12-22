package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oms.model.User;


public class CheckSessionController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//back to landing page
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			req.setAttribute("user", user);
			if(user.getRole_id() == 1) {
				dispatcher = req.getRequestDispatcher("pages/admin/dashboard.jsp");
			}else if(user.getRole_id() == 2) {
				dispatcher = req.getRequestDispatcher("pages/producer/dashboard.jsp");
			}else if(user.getRole_id() == 3) {
				dispatcher = req.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
			}else if(user.getRole_id() == 4) {
				dispatcher = req.getRequestDispatcher("pages/auditor/dashboard.jsp");
			}
		}
		else {
			dispatcher = req.getRequestDispatcher("pages/login.jsp");
		}
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		HttpSession session = req.getSession();
		
		User user = (User)session.getAttribute("user");
		
		if(user != null) {
			req.setAttribute("user", user);
			if(user.getRole_id() == 1) {
				dispatcher = req.getRequestDispatcher("pages/admin/dashboard.jsp");
			}else if(user.getRole_id() == 2) {
				dispatcher = req.getRequestDispatcher("pages/producer/dashboard.jsp");
			}else if(user.getRole_id() == 3) {
				dispatcher = req.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
			}else if(user.getRole_id() == 4) {
				dispatcher = req.getRequestDispatcher("pages/auditor/dashboard.jsp");
			}
			dispatcher.forward(req, resp);
		}else {	
			Cookie[] cookies = req.getCookies();
			user = new User();
			if(cookies != null) {
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("userC") && !cookie.getValue().equals(null)) {
						user.setUsername(cookie.getValue());
					}
				}
				
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("passwordC") && !cookie.getValue().equals(null)) {
						user.setPassword(cookie.getValue());
					}
				}
				
				for(Cookie cookie : cookies) {
					if(cookie.getName().equals("roleC") && !cookie.getValue().equals(null)) {
						user.setRole_id(Integer.valueOf(cookie.getValue()));
					}
				}
				
				if(user.getUsername() == null && user.getPassword() == null) {
					dispatcher = req.getRequestDispatcher("pages/ordertaker/dashboard.jsp"); // pede magtesting ng pages dito
					dispatcher.forward(req, resp);
				}
				
				else {
					req.setAttribute("user", user);
					if(user.getRole_id() == 1) {
						dispatcher = req.getRequestDispatcher("pages/admin/dashboard.jsp");
						session.setAttribute("user", user);
					}else if(user.getRole_id() == 2) {
						dispatcher = req.getRequestDispatcher("pages/producer/dashboard.jsp");
						session.setAttribute("user", user);
					}else if(user.getRole_id() == 3) {
						dispatcher = req.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
						session.setAttribute("user", user);
					}else if(user.getRole_id() == 4) {
						dispatcher = req.getRequestDispatcher("pages/auditor/dashboard.jsp");
						session.setAttribute("user", user);
					}else {
						Cookie usernameCookie = new Cookie("userC", "");
						usernameCookie.setMaxAge(0);
						Cookie passwordCookie = new Cookie("passwordC", "");
						passwordCookie.setMaxAge(0);
//						Cookie roleCookie = new Cookie("roleC", "");
//						roleCookie.setMaxAge(0);
//						resp.addCookie(roleCookie);
						resp.addCookie(usernameCookie);
						resp.addCookie(passwordCookie);
						dispatcher = req.getRequestDispatcher("pages/login.jsp");
					}
					dispatcher.forward(req, resp);
				}
			}
		}
	}

	

}
