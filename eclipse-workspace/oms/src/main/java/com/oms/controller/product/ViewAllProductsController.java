package com.oms.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.model.Product;
import com.oms.service.ProductDAO;

public class ViewAllProductsController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8362473137962563090L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int offset = Integer.parseInt(request.getParameter("offset"));
		int nOfRecord = Integer.parseInt(request.getParameter("nOfRecord"));
		String orderby = request.getParameter("orderby");
		String sortby = request.getParameter("sortby");
		
		int page = ((offset - 1) * nOfRecord);
		
		RequestDispatcher dispatcher = null;
		
		ProductDAO productDAO = new ProductDAO();
		
		List<Product> products = productDAO.getAllProduct(page, nOfRecord, orderby, sortby);
		
		
		
		int noOfRecords = productDAO.getNumberOfRow();
        int totalPage = (int)Math.ceil(noOfRecords * 1.0 / nOfRecord);
        
        
        request.setAttribute("entry", nOfRecord);
        request.setAttribute("orderby", orderby);
        request.setAttribute("sortby", sortby);
        
        request.setAttribute("allProductList", products);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("currentPage", offset);
        
		dispatcher = request.getRequestDispatcher("pages/admin/productList.jsp");
		
		dispatcher.forward(request, response);
	}
	
	
}
