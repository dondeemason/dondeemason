package com.oms.controller.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Orders;
import com.oms.service.OrdersDAO;

public class EditPaymentStatusController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2481519225108528814L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String orderIdStr = request.getParameter("orderId");
		String paymentStatusStr = request.getParameter("paymentStatus");
		
		int orderId = Integer.parseInt(orderIdStr);
		int paymentStatus = Integer.parseInt(paymentStatusStr);
		
		Orders order = new Orders();
		order.setOrderId(orderId);
		order.setPaymentStatus(paymentStatus);
		
		OrdersDAO ordersDAO = new OrdersDAO();
		ordersDAO.editPaymentStatus(order);
		
		RequestDispatcher dispatcher = null;

		request.setAttribute("orderTaker", ordersDAO.getOrderTakerByOrderId(orderId));
		dispatcher = request.getRequestDispatcher("pages/CustomerReceipt.jsp");
		dispatcher.forward(request, response);
		
	}
	
	

}
