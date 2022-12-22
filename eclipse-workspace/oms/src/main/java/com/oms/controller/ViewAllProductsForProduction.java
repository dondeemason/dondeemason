package com.oms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.showProductForProduction;
import com.oms.service.DBQueries;



public class ViewAllProductsForProduction extends HttpServlet{
	private static final long serialVersionUID = 8374164198091691327L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		String query = request.getParameter("query");
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;
		DBQueries allOrderList = new DBQueries();
		
		List<showProductForProduction> viewProducts = allOrderList.showAllProduct(query, page, nOfRecord, orderby, sortby);
		
		int noOfRecords = allOrderList.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
        
        
        request.setAttribute("entry", nOfRecord);
        request.setAttribute("orderby", orderby);
        request.setAttribute("sortby", sortby);
        
        request.setAttribute("viewProducts", viewProducts);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
		dispatcher = request.getRequestDispatcher("pages/producer/produceProduct.jsp");

		dispatcher.forward(request, response);
	}
}
