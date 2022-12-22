package com.oms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oms.model.OrderDetails;



public class OrderDetailsDAO {
	
	final DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
	
	Connection conn = null;
	Statement stmt = null; 
	ResultSet rs = null;

	public void addOrderDetails(int orderId, List<Integer> productIdList, List<Integer> quantityList) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			int size = productIdList.size();
			
			for (int i = 0; i < size; i++) {
				
				String query = "INSERT INTO orderdetails_1 (item_id, order_id, product_id, quantity) "
						+ "VALUES (item_id_1_seq.nextval, ?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(query);	
				
				pstmt.setInt(1, orderId);
				pstmt.setInt(2, productIdList.get(i));
				pstmt.setInt(3, quantityList.get(i));
				
				Integer res = pstmt.executeUpdate();
				
				pstmt.close();
			}
	
			System.out.println("Add New Order Details Success!");

			conn.commit();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to add Order Details");
		}	
	}

	
	public List<OrderDetails> getAllOrderDetails() {
		ArrayList<OrderDetails> allOrderDetailsList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			
			String query = "SELECT od.*, p.product_name, p.price FROM OrderDetails_1 od, product_1 p "
					+ "WHERE od.product_id = p.product_id "
					+ "ORDER BY od.item_id";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				OrderDetails orderDetails = new OrderDetails();
				
				orderDetails.setItemId(rs.getInt("item_id"));
				orderDetails.setOrderId(rs.getInt("order_id"));
				orderDetails.setProductId(rs.getInt("product_id"));
				orderDetails.setQuantity(rs.getInt("quantity"));
				orderDetails.setProductName(rs.getString("product_name"));
				orderDetails.setProductPrice(rs.getString("price"));

				allOrderDetailsList.add(orderDetails);
			}
			
			System.out.println("Successfully list all order details");
			
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to list all order details");
		}
		
		return allOrderDetailsList;
	}
	

	public List<OrderDetails> getAllOrderDetailsByOrderId(int id) {
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			
			String query = "SELECT od.*, p.product_name, p.price "
					+ "FROM OrderDetails_1 od, product_1 p "
					+ "WHERE od.product_id = p.product_id AND od.order_id = ? "
					+ "ORDER BY od.item_id";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderDetails orderDetails = new OrderDetails();
				
				orderDetails.setItemId(rs.getInt("item_id"));
				orderDetails.setOrderId(rs.getInt("order_id"));
				orderDetails.setProductId(rs.getInt("product_id"));
				orderDetails.setQuantity(rs.getInt("quantity"));
				orderDetails.setProductName(rs.getString("product_name"));
				orderDetails.setProductPrice(rs.getString("price"));
				
				orderDetailsList.add(orderDetails);
			}
			
			
			
			rs.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to get OrderDetails by Order Id : " + e);
		}
		
		return orderDetailsList;
	}
	

	public OrderDetails getOrderDetailsByItemId(int id) {
		OrderDetails orderDetails = new OrderDetails();
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT od.*, p.product_name, p.price "
					+ "FROM orderdetails_1 od, product_1 p "
					+ "WHERE od.product_id = p.product_id AND od.item_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {			
				orderDetails.setItemId(rs.getInt("item_id"));
				orderDetails.setOrderId(rs.getInt("order_id"));
				orderDetails.setProductId(rs.getInt("product_id"));
				orderDetails.setQuantity(rs.getInt("quantity"));
				orderDetails.setProductName(rs.getString("product_name"));
				orderDetails.setProductPrice(rs.getString("price"));

			}
			
			System.out.println("Successfully get OrderDetails by Item id : " + id);
			
			stmt.close();
			rs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("Failed to get OrderDetails by Item Id : " + e);
		}
		
		return orderDetails;
	}

	public void editOrderDetails(OrderDetails orderDetails) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE orderdetails_1 SET product_id = ?, quantity = ? WHERE item_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, orderDetails.getProductId());
			pstmt.setInt(2, orderDetails.getQuantity());
			pstmt.setInt(3, orderDetails.getItemId());

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Edit Order Details Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to edit Order Details");
		}
	}
	
	
	public void deleteOrderDetailsByItemId(int id) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "DELETE FROM orderdetails_1 WHERE item_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, id);

			Integer res = pstmt.executeUpdate();
			
			System.out.println("Successfully Delete Item ID : " + id);

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to delete Item ID : " + id);
		}
		
	}
}
