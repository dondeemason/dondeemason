package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.dto.IsUnique;
import com.oms.model.Users;
import com.oms.service.DBQueries;

public class EditUserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 605612124108213850L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("userId");
		
		RequestDispatcher dispatcher = null;
		
		DBQueries dbQueries = new DBQueries();
		Users users = dbQueries.getUserById(Integer.parseInt(id));
		
		request.setAttribute("user", users);
		dispatcher = request.getRequestDispatcher("pages/admin/editUser.jsp");
		
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("userId");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		RequestDispatcher dispatcher = null;
		
		int userId = Integer.parseInt(id);
		int roleId = Integer.parseInt(role);
		
		DBQueries dbQueries = new DBQueries();
		
//		IsUnique result = dbQueries.isEmailUnique(email);
//		if(result.isUnique()) {
		dbQueries.editUser(userId, username, email, roleId);
		request.setAttribute("message", "Successfully edited the user");
//		}
//		else {
//			request.setAttribute("message", result.getMessage());
//		}
		
//		request.setAttribute("usersList", dbQueries.getAllUsers());
//		request.setAttribute("message", "Add New User Success");
		dispatcher = request.getRequestDispatcher("message.jsp");
		dispatcher.forward(request, response);
	}

}
