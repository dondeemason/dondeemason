package com.oms.controller.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Orders;
import com.oms.service.OrdersDAO;

public class EditOrderStatusController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1646155069906581840L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String orderIdStr = request.getParameter("orderId");
		String remarks = request.getParameter("remarks");
		String orderStatusStr = request.getParameter("orderStatus");
		String payStatusStr = request.getParameter("paymentStatus");
		
		int orderId = Integer.parseInt(orderIdStr);
		int orderStatus = Integer.parseInt(orderStatusStr);
		int payStatus = Integer.parseInt(payStatusStr);
		
		Orders orders = new Orders();
		
		orders.setPaymentStatus(payStatus);
		orders.setOrderId(orderId);
		orders.setOrderStatus(orderStatus);
		orders.setRemarks(remarks);
		
		OrdersDAO ordersDAO = new OrdersDAO();
		ordersDAO.editOrderStatus(orders);
		
		
		RequestDispatcher dispatcher = null;

		request.setAttribute("message", "Successfully edit the order details");
		dispatcher = request.getRequestDispatcher("message.jsp");
		dispatcher.forward(request, response);
	}

}
