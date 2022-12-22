package com.oms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.oms.model.Orders;

public class OrdersDAO {
	
	final DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
	private int numberOfRow;
	Connection conn = null;
	Statement stmt = null; 
	ResultSet rs = null;
	
	
	public int addOrderTaker(Orders orders) {
		int orderId = 0;
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			// Fetch value of Sequence
			String idIdentifierQuery = "SELECT order_id_1_seq.nextval FROM DUAL";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(idIdentifierQuery);
			
			while(rs.next()) {
				orderId = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			
			String query = "INSERT INTO orders_1 (order_id, customer_fn, source_name, order_source, customer_ln, "
					+ "mobile_number, order_date, delivery_date, order_status, payment_status, discount, price, remarks) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement pstmt = conn.prepareStatement(query);	
			
			pstmt.setInt(1, orderId);
			pstmt.setString(2, orders.getCustomerFn());
			pstmt.setString(3, orders.getSourceName());
			pstmt.setString(4, orders.getOrderSource());
			pstmt.setString(5, orders.getCustomerLn());
			pstmt.setString(6, orders.getMobileNumber());

			// Date Format (yyyy-MM-dd HH:mm:ss) or 2022-12-13 10:44:1	
			// Insert Current Time
			pstmt.setTimestamp(7, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setTimestamp(8, java.sql.Timestamp.valueOf(orders.getDeliveryDateStr()));	
			pstmt.setInt(9, 1);			// Order Status Default 1 (Status Pending)
			pstmt.setInt(10, 1);		// Payment Status Default 1 (Not Paid)
			pstmt.setInt(11, orders.getDiscount());
			pstmt.setDouble(12, orders.getPrice());
			pstmt.setString(13, orders.getRemarks());
			
			Integer res = pstmt.executeUpdate();
		
//			System.out.println("Add New Orders Success!");
			
			conn.commit();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("Failed to add Orders");
		}
		
		return orderId;
	}
	
	public Orders getOrderTakerByOrderId(int id) {
		Orders orderTaker = new Orders();
		
		try {
			conn = DB.getConnection();
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM orders_1 WHERE order_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				orderTaker.setOrderId(rs.getInt("order_id"));
				orderTaker.setCustomerFn(rs.getString("customer_fn"));
				orderTaker.setSourceName(rs.getString("source_name"));
				orderTaker.setOrderSource(rs.getString("order_source"));
				orderTaker.setCustomerLn(rs.getString("customer_ln"));
				orderTaker.setMobileNumber(rs.getString("mobile_number"));
				
				// Get TimeStamp as String
				orderTaker.setOrderDateStr(rs.getString("order_date"));
				orderTaker.setDeliveryDateStr(rs.getString("delivery_date"));
				
				orderTaker.setOrderStatus(rs.getInt("order_status"));
				orderTaker.setPaymentStatus(rs.getInt("payment_status"));
				orderTaker.setDiscount(rs.getInt("discount"));
				orderTaker.setPrice(rs.getDouble("price"));
				orderTaker.setRemarks(rs.getString("remarks"));
				
				OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
				orderTaker.setOrderDetails(orderDetailsDAO.getAllOrderDetailsByOrderId(id));
			}
			
//			System.out.println("Successfully get Order Taker by orderId");
			
			rs.close();
			conn.close();
		} catch (Exception e) {
//			System.out.println("Failed to get Order Taker by orderId : " + e);
		}
		
		return orderTaker;
	}
	
	public List<Orders> getAllOrderTaker(int pageNum, int pageSize, String orderby, String sortby) {
		List<Orders> allOrderTaker = new ArrayList<>();
		
		try {
			conn = DB.getConnection();

			String query = "SELECT * FROM orders_1 WHERE order_status < 3 order by "+orderby+" "+sortby+" offset "+pageNum+" rows fetch next "+pageSize+" rows only";
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
			
			while(rs.next()) {
				Orders orderTaker = new Orders();
				
				orderTaker.setOrderId(rs.getInt("order_id"));
				
				orderTaker.setCustomerFn(rs.getString("customer_fn"));
				orderTaker.setSourceName(rs.getString("source_name"));
				orderTaker.setOrderSource(rs.getString("order_source"));
				orderTaker.setCustomerLn(rs.getString("customer_ln"));
				orderTaker.setMobileNumber(rs.getString("mobile_number"));
				
				// Pass TimeStamp as String
				orderTaker.setOrderDateStr(rs.getString("order_date"));
				orderTaker.setDeliveryDateStr(rs.getString("delivery_date"));
				orderTaker.setOrderStatus(rs.getInt("order_status"));
				orderTaker.setPaymentStatus(rs.getInt("payment_status"));
				orderTaker.setDiscount(rs.getInt("discount"));
				orderTaker.setPrice(rs.getDouble("price"));
				orderTaker.setRemarks(rs.getString("remarks"));

				int orderId = rs.getInt("order_id");
				
				orderTaker.setOrderDetails(orderDetailsDAO.getAllOrderDetailsByOrderId(orderId));
				
				int statusId = rs.getInt("order_status");
				orderTaker.setOrderStatusDesc(getOrderStatusDescription(statusId));
				
				int paymentId = rs.getInt("payment_status");
				orderTaker.setPaymentStatusDesc(getPaymentStatusDescription(paymentId));
				
				allOrderTaker.add(orderTaker);
			}
			
			
//			System.out.println("Successfully list all Order Taker");
			
			rs.close();
			rs = stmt.executeQuery("select count(*) totalpages from orders_1");
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println("Failed to list all Order Taker");
		}
		
		return allOrderTaker;
	}
	
	public int getNumberOfRow() { return numberOfRow; }
	
	public void editOrderStatus(Orders order) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE orders_1 SET order_status = ?, remarks = ?, payment_status = ? WHERE order_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, order.getOrderStatus());
			pstmt.setString(2, order.getRemarks());
			pstmt.setInt(3, order.getPaymentStatus());
			pstmt.setInt(4, order.getOrderId());

			pstmt.executeUpdate();
			
//			System.out.println("Edit Order Status Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println("Failed to edit Order Status");
		}
	}
	
	public void editPaymentStatus(Orders order) {
		
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);
			
			String query = "UPDATE orders_1 SET payment_status = ? WHERE order_id = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(query);	

			pstmt.setInt(1, order.getPaymentStatus());
			pstmt.setInt(2, order.getOrderId());

			Integer res = pstmt.executeUpdate();
			
//			System.out.println("Edit Payment Status Success!");

			conn.commit();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
//			System.out.println("Failed to edit Payment Status");
		}
	}
	
	public String getOrderStatusDescription(int statusId) {
		String status = "";
		
		switch(statusId) {
			
			case 1:
				status = "Pending";
				break;
			case 2:
				status = "Ready for Pick Up";
				break;
			case 3:
				status = "Completed";
				break;
			case 4:
				status = "Acknowledged";
				break;
			case 50:
				status = "Cancelled";
				break;
			case 90:
				status = "Rejected";
				break;

		}
		
		return status;
	}
	
	public String getPaymentStatusDescription(int paymentId) {
		String status = "";
		
		if(paymentId == 1) {
			status = "Not Paid";
		} else if(paymentId == 2) {
			status = "Paid";
		}
		
		return status;
	}
}
