package com.oms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oms.dto.IsUnique;
import com.oms.model.User;
import com.oms.service.DBQueries;

public class AddNewUserController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4955832600776481538L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null) {
			req.setAttribute("user", user);
			dispatcher = req.getRequestDispatcher("pages/admin/addNewUser.jsp");
		}
		else {
			dispatcher = req.getRequestDispatcher("pages/login.jsp");
		}
		dispatcher.forward(req, resp);
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		RequestDispatcher dispatcher = null;
		
		DBQueries dbQueries = new DBQueries();
		
		// object has boolean true or false and string message
		IsUnique isUnique = dbQueries.isUsernameUnique(username, email);
		
		if(isUnique.isUnique()) {
			dbQueries.addNewUser(username, email, password, Integer.parseInt(role));

			//request.setAttribute("usersList", dbQueries.getAllUsers());
			
			request.setAttribute("message", "Successfully added a user");
			dispatcher = request.getRequestDispatcher("message.jsp");
		} else {
			request.setAttribute("message", isUnique.getMessage());
			dispatcher = request.getRequestDispatcher("message.jsp");
		}
		
		dispatcher.forward(request, response);
	}

}
