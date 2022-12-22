package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		HttpSession session = req.getSession();
		session.invalidate();
		Cookie usernameCookie = new Cookie("userC", "");
		usernameCookie.setMaxAge(0);
		Cookie passwordCookie = new Cookie("passwordC", "");
		passwordCookie.setMaxAge(0);
		Cookie roleCookie = new Cookie("roleC", "");
		roleCookie.setMaxAge(0);
		resp.addCookie(roleCookie);
		resp.addCookie(usernameCookie);
		resp.addCookie(passwordCookie);
		req.setAttribute("message", "Please login");
		dispatcher = req.getRequestDispatcher("pages/login.jsp");
		dispatcher.forward(req, resp);
	}
}
