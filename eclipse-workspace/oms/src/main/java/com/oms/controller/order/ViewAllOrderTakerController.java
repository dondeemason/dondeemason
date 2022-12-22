package com.oms.controller.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.OrderDetails;
import com.oms.model.Orders;
import com.oms.service.OrderDetailsDAO;
import com.oms.service.OrdersDAO;

public class ViewAllOrderTakerController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6706357509737625847L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;
		
		OrdersDAO ordersDAO = new OrdersDAO();
		//OrderDetailsDAO orderDetails = new OrderDetailsDAO();
		// print the time it takes to list all orders
//		final long startTime = System.currentTimeMillis();
//		System.out.println("Start time " + startTime * 0.001);
		List<Orders> orderlist = ordersDAO.getAllOrderTaker(page, nOfRecord, orderby, sortby);
//		request.setAttribute("allOrderTaker", orderlist);
		
//		final long endTime = System.currentTimeMillis();
//		System.out.println("Total execution time: " + ((endTime * 0.001) - (startTime * 0.001)));
		
//		request.setAttribute("entry", "TODO");
//        request.setAttribute("orderby", "TODO");
//        request.setAttribute("sortby", "TODO");
		int noOfRecords = ordersDAO.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
        
        
        request.setAttribute("entry", nOfRecord);
        request.setAttribute("orderby", orderby);
        request.setAttribute("sortby", sortby);
        
        request.setAttribute("allOrderTaker", orderlist);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
        
		dispatcher = request.getRequestDispatcher("pages/ordertaker/orderList.jsp");
		
		dispatcher.forward(request, response);
		
	}
}
