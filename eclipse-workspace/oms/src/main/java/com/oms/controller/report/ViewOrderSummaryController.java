package com.oms.controller.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.OrderSummary;
import com.oms.service.ReportSummaryDAO;

public class ViewOrderSummaryController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4532199094975813931L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date = request.getParameter("date");
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;		
		
		ReportSummaryDAO summary = new ReportSummaryDAO();
		
		List<OrderSummary> orderSummary = summary.getOrderSummaryByDate(date, page, nOfRecord, orderby, sortby);
		
		int noOfRecords = summary.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
		
        request.setAttribute("date", date);
        request.setAttribute("orderSummaryList", orderSummary);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
		
		dispatcher = request.getRequestDispatcher("pages/admin/orderSummaryReport.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
	
}
