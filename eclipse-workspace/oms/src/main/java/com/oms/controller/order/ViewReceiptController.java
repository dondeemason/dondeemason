package com.oms.controller.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.service.OrdersDAO;

public class ViewReceiptController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6187502810571641528L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderIdStr = request.getParameter("orderId");
		
		int orderId = Integer.parseInt(orderIdStr);
		
		RequestDispatcher dispatcher = null;
		
		OrdersDAO ordersDAO = new OrdersDAO();
		
		request.setAttribute("orderTaker", ordersDAO.getOrderTakerByOrderId(orderId));
		
		dispatcher = request.getRequestDispatcher("pages/CustomerReceipt.jsp");
		
		dispatcher.forward(request, response);
	}
	
	

}
