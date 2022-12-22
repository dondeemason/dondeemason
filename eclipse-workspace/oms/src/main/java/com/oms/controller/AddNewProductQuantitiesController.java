package com.oms.controller;

import java.io.IOException;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oms.dto.IsUnique;
import com.oms.model.Product;
import com.oms.service.DBQueries;
import com.oms.service.ProductDAO;

public class AddNewProductQuantitiesController extends HttpServlet{

	private static final long serialVersionUID = -6248311439566301491L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
		//Sample
		String[] productName = request.getParameterValues("jsonProductName[]");
		String[] addedQty = request.getParameterValues("jsonQuantity[]");
		String username = request.getParameter("username");

		RequestDispatcher dispatcher = null;
		DBQueries dbquery = new DBQueries();

		dbquery.addProduction(productName, addedQty, username);

		request.setAttribute("message", "Successfully added quantity");
		dispatcher = request.getRequestDispatcher("message.jsp");
		dispatcher.forward(request, response);
	}
}