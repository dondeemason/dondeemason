package com.oms.controller.product;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oms.model.Product;
import com.oms.service.ProductDAO;

@MultipartConfig(maxFileSize = 16177215) // Max 16MB
public class EditProductController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1969919256365772157L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("productId");
		
		int productId = Integer.parseInt(id);
		
		RequestDispatcher dispatcher = null;
		
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getProductById(productId);
		
		request.setAttribute("product", product);
		
		dispatcher = request.getRequestDispatcher("pages/admin/editProduct.jsp");
		
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//int oldProductId =	Integer.parseInt(request.getParameter("oldProductId"));
		String ProductId = request.getParameter("productId");
		//String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		String productPicture = request.getParameter("productPicture");
		String productStatus = request.getParameter("productStatus");
		String price = request.getParameter("price");
		
		int pId = Integer.parseInt(ProductId);
		int status = Integer.parseInt(productStatus);
		double productPrice = Double.parseDouble(price);

		RequestDispatcher dispatcher = null;
		
		Product product = new Product();

		product.setProductId(pId);
//			product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductStatus(status);
		product.setPrice(productPrice);
		product.setProductPicture(productPicture);
		
		ProductDAO productDAO = new ProductDAO();

		productDAO.editProduct(product);

//		request.setAttribute("allProductList", productDAO.getAllProduct());
		request.setAttribute("message", "Successfully edited the product");
		dispatcher = request.getRequestDispatcher("message.jsp");
		
		dispatcher.forward(request, response);
		
	}

	
}
