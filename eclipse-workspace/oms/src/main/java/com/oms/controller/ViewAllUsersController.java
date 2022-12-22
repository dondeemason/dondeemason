package com.oms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Users;
import com.oms.service.DBQueries;

public class ViewAllUsersController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8274164198091691327L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String itemId = request.getParameter("itemId");
//		String productId = request.getParameter("productId");
//		String quantity = request.getParameter("itemId");
		
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;
		
		DBQueries allUsersList = new DBQueries();
		
		//int offset, int nOfRecord, String orderby, String sortby
		
//		if()
		List<Users> userList = allUsersList.getAllUsers(page, nOfRecord, orderby, sortby);
		
		
		int noOfRecords = allUsersList.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
        
        
        request.setAttribute("entry", nOfRecord);
        request.setAttribute("orderby", orderby);
        request.setAttribute("sortby", sortby);
        
        request.setAttribute("usersList", userList);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
		
		dispatcher = request.getRequestDispatcher("pages/admin/userList.jsp");
		
		dispatcher.forward(request, response);
	}
	
	

}
