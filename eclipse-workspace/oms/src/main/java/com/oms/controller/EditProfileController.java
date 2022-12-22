package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oms.model.Users;
import com.oms.service.DBQueries;

public class EditProfileController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3926904108506146711L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		RequestDispatcher dispatcher = null;
		
		DBQueries dbQueries = new DBQueries();
		//Users user = dbQueries.getUserById(Integer.parseInt(id));
		Users user = dbQueries.getUserByUsername(username);
		
		request.setAttribute("user", user);
		dispatcher = request.getRequestDispatcher("pages/userProfile.jsp");
		
		dispatcher.forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");
		
		DBQueries dbQueries = new DBQueries();
		
		RequestDispatcher dispatcher = null;
		
		if(action.equals("changeEmail")) {
			String id = request.getParameter("userId");
			String email = request.getParameter("email");
//			String password = request.getParameter("password");
			
			int userId = Integer.parseInt(id);

//			if(dbQueries.isUserPasswordAuthenticated(userId, password)) {
			dbQueries.changeUserProfileEmail(userId, email);
				
			request.setAttribute("message", "Successfully updated your email");
			dispatcher = request.getRequestDispatcher("message.jsp");
//			} else {
//				request.setAttribute("message", "Wrong Password");
//				Users user = dbQueries.getUserById(userId);
//				request.setAttribute("user", user);
//				dispatcher = request.getRequestDispatcher("pages/userProfile.jsp");
//			}
			
		} else if(action.equals("changePassword")) {
			String id = request.getParameter("userId");
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			
			int userId = Integer.parseInt(id);
			
			if(dbQueries.isUserPasswordAuthenticated(userId, oldPassword)) {
				
				dbQueries.changeUserProfilePassword(userId, newPassword);
				
				
				HttpSession session = request.getSession();
				session.invalidate();
				
				Cookie usernameCookie = new Cookie("userC", "");
				usernameCookie.setMaxAge(0);
				Cookie passwordCookie = new Cookie("passwordC", "");
				passwordCookie.setMaxAge(0);
				Cookie roleCookie = new Cookie("roleC", "");
				roleCookie.setMaxAge(0);
				response.addCookie(usernameCookie);
				response.addCookie(passwordCookie);
				response.addCookie(roleCookie);
				request.setAttribute("message", "Change Password Success, please login again");
				dispatcher = request.getRequestDispatcher("message.jsp");
				
			} else {
				request.setAttribute("message", "Wrong password. Please try again");
				Users user = dbQueries.getUserById(userId);
				request.setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("message.jsp");
			}
		}
		
		dispatcher.forward(request, response);
	}
	
	

}
