package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.service.DBQueries;

public class DisableUserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5478720125712869814L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("userId");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		int role = Integer.parseInt(request.getParameter("role"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		int userId = Integer.parseInt(id);
		
		RequestDispatcher dispatcher = null;
		
		DBQueries dbQueries = new DBQueries();
		
		if(dbQueries.isCredentialAuthenticated(username, email, role)) {
			
			if(status == 0) {
				dbQueries.disableUser(userId);
				request.setAttribute("message", "Successfully disabled the user");
				//request.setAttribute("usersList", dbQueries.getAllUsers());
				dispatcher = request.getRequestDispatcher("message.jsp");
			}
			else {
				dbQueries.enableUser(userId);
				//request.setAttribute("usersList", dbQueries.getAllUsers());
				request.setAttribute("message", "Successfully enabled the user");
				dispatcher = request.getRequestDispatcher("message.jsp");
			}
	
			
//			request.setAttribute("message", "Disable User Success");
			
		} else {
			request.setAttribute("message", "Wrong credentials. Please don't change user's details when disabling the user");
			dispatcher = request.getRequestDispatcher("message.jsp");
		}
		
		dispatcher.forward(request, response);	
	}
	
	
}
