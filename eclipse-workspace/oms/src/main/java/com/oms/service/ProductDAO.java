package com.oms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oms.model.Product;


public class ProductDAO {
	
final DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
	
	Connection conn = null;
	Statement stmt = null; 
	ResultSet rs = null;

	private int numberOfRow;
	
	public void addNewProduct(Product product) {
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "INSERT INTO "
					+ "product_1 (product_id, product_name, product_description, product_picture, product_status, price) "
					+ "VALUES (product_id_1_seq.nextval, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			
			pstmt.setString(1, product.getProductName());
			pstmt.setString(2, product.getProductDescription());		
			pstmt.setString(3, product.getProductPicture());
			pstmt.setInt(4, product.getProductStatus());
			pstmt.setDouble(5, product.getPrice());
			
			Integer res = pstmt.executeUpdate();
			
			System.out.println("Add New Product Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to add Product");
		}
	}
	
	public List<Product> getAllProduct(int pageNum, int pageSize, String orderby, String sortby) {
		List<Product> allProductList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			
			String query = "select * from product_1 order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductDescription(rs.getString("product_description"));		
				product.setProductStatus(rs.getInt("product_status"));
				product.setPrice(rs.getDouble("price"));
				product.setProductPicture(rs.getString("product_picture"));
				
				int statusId = rs.getInt("product_status");
				
				if(statusId == 0) {
					product.setStatusDescription("Available");
				} else if(statusId == 1) {
					product.setStatusDescription("Disable");
				} else if(statusId == 2) {
					product.setStatusDescription("Removed");
				}
				
				allProductList.add(product);
			}
			
			rs.close();
			rs = stmt.executeQuery("select count(*) totalpages from product_1");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all products");
		}
		
		return allProductList;
	}
	
	public List<Product> getAllProductExceptRemoved(int pageNum, int pageSize, String orderby, String sortby) {
		List<Product> allProductList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			
			String query = "select * from product_1 where product_status <> 2 order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductDescription(rs.getString("product_description"));		
				product.setProductStatus(rs.getInt("product_status"));
				product.setPrice(rs.getDouble("price"));
				product.setProductPicture(rs.getString("product_picture"));
				
				int statusId = rs.getInt("product_status");
				
				if(statusId == 0) {
					product.setStatusDescription("Available");
				} else if(statusId == 1) {
					product.setStatusDescription("Disable");
				} else if(statusId == 2) {
					product.setStatusDescription("Removed");
				}
				
				allProductList.add(product);
			}
			
			rs.close();
			rs = stmt.executeQuery("select count(*) totalpages from product_1 where product_status <> 2");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all products");
		}
		
		return allProductList;
	}
	
	public Product getProductById(int id) {
		Product product = new Product();
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM product_1 WHERE product_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductDescription(rs.getString("product_description"));
				product.setProductStatus(rs.getInt("product_status"));
				product.setPrice(rs.getDouble("price"));
				product.setProductPicture(rs.getString("product_picture"));
			}
			
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Failed to get Product Id : " + e);
		}
		
		return product;
	}
	
	
	public void editProduct(Product product) {

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);

            String query = "UPDATE product_1 SET product_description = ?, product_picture = ?, product_status = ?, "
                    + "price = ? WHERE product_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, product.getProductDescription());
            pstmt.setString(2, product.getProductPicture());
            pstmt.setInt(3, product.getProductStatus());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getProductId());
            
            Integer res = pstmt.executeUpdate();

            System.out.println("Edit Product Success!");

            conn.commit();
            pstmt.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Failed to edit Product");
        }

    }
	
	
	public void editProduct(int productId, String productName, String productDescription, int productStatus, double price) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE product_1 SET product_description = ?, product_status = ?, "
                    + "price = ? WHERE product_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

//			pstmt.setString(1, productName);
//			pstmt.setInt(2, productId);
//			pstmt.setString(3, productDescription);	
//			pstmt.setInt(4, productStatus);
//			pstmt.setDouble(5, price);
//			pstmt.setInt(6, id);
			
			pstmt.setString(1, productDescription);
            pstmt.setInt(2, productStatus);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, productId);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Edit Product Success2!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to edit Product");
		}
		
	}

	public int getNumberOfRow() { return numberOfRow; }
	
}
