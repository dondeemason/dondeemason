package com.oms.controller.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Orders;
import com.oms.service.OrderDetailsDAO;
import com.oms.service.OrdersDAO;

public class AddNewOrderController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1079687129315797014L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = null;

		System.out.println("Show order taker form");
		
		dispatcher = request.getRequestDispatcher("pages/OrderTaker.jsp");
		
		dispatcher.forward(request, response);
		
	}




	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * 		Orders Parameter
		 * */
		
		String customerFn = request.getParameter("customerFn");
		String sourceName = request.getParameter("sourceName");
		String orderSource = request.getParameter("orderSource");
		String customerLn = request.getParameter("customerLn");
		String mobileNumberStr = request.getParameter("mobileNumber");
			
		// Date Format (yyyy-MM-dd HH:mm:ss) or 2022-12-13 10:44:1
		//String orderDateStr = request.getParameter("orderDate");
		String deliveryDateStr = request.getParameter("deliveryDate");
		
		String discountStr = request.getParameter("discount");
		String priceStr = request.getParameter("price");
		String remarks = request.getParameter("remarks");
		
		String mobileNumber = mobileNumberStr;
		int discount = Integer.parseInt(discountStr);
		double price = Double.parseDouble(priceStr);	
		
		Orders orders = new Orders();
		
		orders.setCustomerFn(customerFn);
		orders.setSourceName(sourceName);
		orders.setOrderSource(orderSource);
		orders.setCustomerLn(customerLn);
		orders.setMobileNumber(mobileNumber);
		//orders.setOrderDateStr(orderDateStr);
		orders.setDeliveryDateStr(deliveryDateStr);
		orders.setDiscount(discount);
		orders.setPrice(price);
		orders.setRemarks(remarks);
		
		OrdersDAO ordersDAO = new OrdersDAO();

		int orderId = ordersDAO.addOrderTaker(orders);
		
		/*
		 * 		Order Details Parameter
		 * */
		
		String[] productId = request.getParameterValues("productIdList");
		String[] quantity = request.getParameterValues("quantityList");
		
		List<Integer> productIdList = new ArrayList<>();	
		List<Integer> quantityList = new ArrayList<>();
		
		for (int i = 0; i < productId.length; i++) {
			
			productIdList.add(Integer.parseInt(productId[i]));
			quantityList.add(Integer.parseInt(quantity[i]));
		}
		
		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		
		orderDetailsDAO.addOrderDetails(orderId, productIdList, quantityList);

		RequestDispatcher dispatcher = null;
		
		Orders orderTaker = ordersDAO.getOrderTakerByOrderId(orderId);
		
		request.setAttribute("orderTaker", orderTaker);
		dispatcher = request.getRequestDispatcher("pages/CustomerReceipt.jsp");
				
		dispatcher.forward(request, response);
	}
}
