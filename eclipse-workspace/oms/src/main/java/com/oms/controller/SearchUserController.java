package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.service.DBQueries;

public class SearchUserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2083875238765022358L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		RequestDispatcher dispatcher = null;
		
		DBQueries allUsersList = new DBQueries();
		
		request.setAttribute("usersList", allUsersList.searchUserByUsername(username));
		dispatcher = request.getRequestDispatcher("pages/admin/userList.jsp");
		
		dispatcher.forward(request, response);
		
		
	}

	
	
}
