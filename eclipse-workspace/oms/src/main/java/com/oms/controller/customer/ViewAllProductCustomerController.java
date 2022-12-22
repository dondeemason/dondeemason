package com.oms.controller.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Product;
import com.oms.service.ProductDAO;

public class ViewAllProductCustomerController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;
		
		ProductDAO productDAO = new ProductDAO();
		
		List<Product> products = productDAO.getAllProductExceptRemoved(page, nOfRecord, orderby, sortby);
		
		
		
		int noOfRecords = productDAO.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
        
        request.setAttribute("allProductList", products);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
        
		dispatcher = request.getRequestDispatcher("pages/customer/customerProductPage.jsp");
		
		
		dispatcher.forward(request, response);
	}
}
