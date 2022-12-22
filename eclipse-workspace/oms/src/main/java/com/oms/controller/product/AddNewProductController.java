package com.oms.controller.product;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oms.model.Product;
import com.oms.service.ProductDAO;

public class AddNewProductController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6248311439566301491L;
	
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		dispatcher = req.getRequestDispatcher("pages/admin/addProduct.jsp");
		dispatcher.forward(req, resp);
	}




	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub;
		//Sample
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productPicture = request.getParameter("productPicture");
		String productStatus = request.getParameter("productStatus");
		String price = request.getParameter("price");
		
		System.out.println("prduct stat: "+productStatus);
		
		int status = Integer.parseInt(productStatus);
		double productPrice = Double.parseDouble(price);
		
		Product product = new Product();
		
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductStatus(status);
		product.setPrice(productPrice);
		product.setProductPicture(productPicture);
		
		ProductDAO productDAO = new ProductDAO();
		productDAO.addNewProduct(product);

		RequestDispatcher dispatcher = null;
		
//		request.setAttribute("allProductList", productDAO.getAllProduct());
		request.setAttribute("message", "Successfully added a product");
		
		dispatcher = request.getRequestDispatcher("message.jsp");
		
		dispatcher.forward(request, response);
	}
}
