package com.oms.controller.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.OrderDetails;
import com.oms.service.OrderDetailsDAO;


public class EditOrderDetailsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1011781878069363947L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String itemId = request.getParameter("itemId");
		
		int id = Integer.parseInt(itemId);
		
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		
		OrderDetails orderDetails = orderDetailsDAO.getOrderDetailsByItemId(id);
		
		RequestDispatcher dispatcher = null;
		
		request.setAttribute("orderDetails", orderDetails);
		dispatcher = request.getRequestDispatcher("pages/admin/.jsp");
		
		dispatcher.forward(request, response);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String itemIdStr = request.getParameter("itemId");
		String productIdStr = request.getParameter("productId");
		String quantityStr = request.getParameter("quantity");
		
		int itemId = Integer.parseInt(itemIdStr);
		int productId = Integer.parseInt(productIdStr);
		int quantity = Integer.parseInt(quantityStr);
		
		RequestDispatcher dispatcher = null;
		
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		
		if(quantity > 0) {
			
			OrderDetails orderDetails = new OrderDetails();
			
			orderDetails.setItemId(itemId);
			orderDetails.setProductId(productId);
			orderDetails.setQuantity(quantity);
			
			orderDetailsDAO.editOrderDetails(orderDetails);
			
			request.setAttribute("message", "Successfully edit Order Item Id : " + itemId);
			dispatcher = request.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
			
		} else {
			
			orderDetailsDAO.deleteOrderDetailsByItemId(itemId);
			
			request.setAttribute("message", "Successfully Delete Item Id : " + itemId);
			dispatcher = request.getRequestDispatcher("pages/ordertaker/dashboard.jsp");
		}
		
		
		
		dispatcher.forward(request, response);
	}
	
	

}
