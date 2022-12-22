package com.oms.controller.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.service.OrderDetailsDAO;


public class DeleteOrderDetailsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8023772097640971683L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String itemId = request.getParameter("itemId");
		
		int id = Integer.parseInt(itemId);
		
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		
		orderDetailsDAO.deleteOrderDetailsByItemId(id);
		
		RequestDispatcher dispatcher = null;
		
		request.setAttribute("message", "Successfully Delete Item Id : " + id);
		dispatcher = request.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
		
		dispatcher.forward(request, response);
	}
	
	

}
