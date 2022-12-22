package com.oms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.oms.model.OrderDetailsSummary;
import com.oms.model.OrderSummary;

public class ReportSummaryDAO {
	
	final DBConnect DB = new DBConnect("training-db.cosujmachgm3.ap-southeast-1.rds.amazonaws.com","ORCL","TRNG","cpi12345");
	
	Connection conn = null;
	Statement stmt = null; 
	ResultSet rs = null;
	
	private int numberOfRow;
	
	private int statusPending;
	private int statusPickup;
	private int statusAcknowledge;
	private int statusComplete;
	private int statusCancelled;
	private int statusRejected;
	private int statusPaid;
	private int statusNotPaid;
	
	public List<OrderSummary> getOrderSummaryByDate(String date, int pageNum, int pageSize, String orderby, String sortby) {
		List<OrderSummary> orderSummary = new ArrayList<>();

		try {
			conn = DB.getConnection();
			
			String query = "SELECT od.product_id, SUM(quantity) total_quantity, (SUM(quantity) * MAX(p.price)) total_price, "
					+ "	   MIN(o.delivery_date) delivery_date, payment_status, p.product_name "
					+ "FROM orderdetails_1 od, product_1 p, orders_1 o "
					+ "WHERE od.product_id = p.product_id "
					+ "    AND od.order_id = o.order_id "
					+ "    AND o.order_status IN (1,2,3,4) "
					+ "    AND TO_CHAR(o.delivery_date,'yyyy-MM-dd') LIKE ? "
					+ "GROUP BY od.product_id, o.payment_status, p.product_name "
					+ "ORDER BY " + orderby + " " + sortby + " "
					+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			
			PreparedStatement pstmt = conn.prepareStatement(query);	
			pstmt.setString(1,"%" + date + "%");
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, pageSize);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderSummary summary = new OrderSummary();
				
				summary.setProductId(rs.getInt("product_id"));
				summary.setProductName(rs.getString("product_name"));
				summary.setTotalQuantity(rs.getInt("total_quantity"));
				summary.setTotalPrice(rs.getDouble("total_price"));
				summary.setDeliveryDate(rs.getString("delivery_date"));
				summary.setPaymentStatus(rs.getInt("payment_status"));
				
				OrdersDAO orders = new OrdersDAO();
				String paymentDescription = orders.getPaymentStatusDescription(rs.getInt("payment_status"));
				
				summary.setPaymentStatusDesc(paymentDescription);
				
				orderSummary.add(summary);
			}
			
			System.out.println("Successfully get Order Summary for " + date);
			
			query = "SELECT COUNT(COUNT(od.product_id)) totalpages FROM orderdetails_1 od, orders_1 o "
					+ "WHERE od.order_id = o.order_id AND TO_CHAR(o.delivery_date,'yyyy-MM-dd') LIKE ? "
					+ "GROUP BY od.product_id, o.payment_status";
			
			pstmt = conn.prepareStatement(query);	
			pstmt.setString(1,"%" + date + "%");
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				this.numberOfRow = rs.getInt("totalpages");
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to get Order Summary for" + date);
		}
		

		return orderSummary;
	}
	
//	public List<OrderDetailsSummary> getOrderDetailsSummary(String date, int pageNum, int pageSize, String orderby, String sortby) {
//		List<OrderDetailsSummary> summaryList = new ArrayList<>();
//		
//		conn = DB.getConnection();
//		
//		try {
//			String query = "SELECT order_id, customer_fn || ' ' || customer_ln full_name, mobile_number, delivery_date, "
//					+ "payment_status, price, remarks "
//					+ "FROM orders_1 "
//					+ "WHERE TO_CHAR(delivery_date,'yyyy-MM-dd') LIKE ? "
//					+ "AND order_status IN (?,?,?,?,?,?) "
//					+ "AND payment_status IN (?,?)";
//
//			PreparedStatement pstmt = conn.prepareStatement(query);	
//			pstmt.setString(1,"%" + date + "%");
//			
//			// Order Status IN clause
//			pstmt.setInt(2, statusPending);
//			pstmt.setInt(3, statusPickup);
//			pstmt.setInt(4, statusAcknowledge);
//			pstmt.setInt(5, statusComplete);
//			pstmt.setInt(6, statusRejected);
//			pstmt.setInt(7, statusCancelled);
//			// Payment Status IN clause
//			pstmt.setInt(8, statusPaid);
//			pstmt.setInt(9, statusNotPaid);
//
//			/*
//			// Order Status IN clause
//			pstmt.setInt(2, order.getStatusPending());
//			pstmt.setInt(3, order.getStatusPickup());
//			pstmt.setInt(4, order.getStatusAcknowledge());
//			pstmt.setInt(5, order.getStatusComplete());
//			pstmt.setInt(6, order.getStatusRejected());
//			pstmt.setInt(7, order.getStatusCancelled());
//			// Payment Status IN clause
//			pstmt.setInt(8, order.getStatusPaid());
//			pstmt.setInt(9, order.getStatusNotPaid());
//			*/
//			ResultSet rs = pstmt.executeQuery();
//			
//			System.out.println("Successfully get Customer Summary for " + date);
//			
//			while(rs.next()) {
//				OrderDetailsSummary detailsSummary = new OrderDetailsSummary();
//				
//				detailsSummary.setOrderId(rs.getInt("order_id"));
//				detailsSummary.setFullname(rs.getString("full_name"));
//				detailsSummary.setMobileNumber(rs.getInt("mobile_number"));
//				detailsSummary.setDeliveryDate(rs.getString("delivery_date"));
//				/*
//				OrderDetailsDAO ordersDetailsDAO = new OrderDetailsDAO();
//				detailsSummary.setOrderDetails(ordersDetailsDAO.getAllOrderDetailsByOrderId(rs.getInt("order_id")));
//				*/
//				OrdersDAO ordersDAO = new OrdersDAO();
//				String paymentDescription = ordersDAO.getPaymentStatusDescription(rs.getInt("payment_status"));
//				detailsSummary.setPaymentStatusDesc(paymentDescription);
//
//				detailsSummary.setPrice(rs.getDouble("price"));
//				detailsSummary.setRemarks(rs.getString("remarks"));
//				
//				summaryList.add(detailsSummary);
//			}
//
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.out.println("Failed to get Customer Report for" + date);
//		}
//		return summaryList;
//	}

	public int getNumberOfRow() {
		return numberOfRow;
	}

	public int getStatusPending() {
		return statusPending;
	}

	public void setStatusPending(int statusPending) {
		this.statusPending = statusPending;
	}

	public int getStatusPickup() {
		return statusPickup;
	}

	public void setStatusPickup(int statusPickup) {
		this.statusPickup = statusPickup;
	}

	public int getStatusAcknowledge() {
		return statusAcknowledge;
	}

	public void setStatusAcknowledge(int statusAcknowledge) {
		this.statusAcknowledge = statusAcknowledge;
	}

	public int getStatusComplete() {
		return statusComplete;
	}

	public void setStatusComplete(int statusComplete) {
		this.statusComplete = statusComplete;
	}

	public int getStatusCancelled() {
		return statusCancelled;
	}

	public void setStatusCancelled(int statusCancelled) {
		this.statusCancelled = statusCancelled;
	}

	public int getStatusRejected() {
		return statusRejected;
	}

	public void setStatusRejected(int statusRejected) {
		this.statusRejected = statusRejected;
	}

	public int getStatusPaid() {
		return statusPaid;
	}

	public void setStatusPaid(int statusPaid) {
		this.statusPaid = statusPaid;
	}

	public int getStatusNotPaid() {
		return statusNotPaid;
	}

	public void setStatusNotPaid(int statusNotPaid) {
		this.statusNotPaid = statusNotPaid;
	}

	public void setNumberOfRow(int numberOfRow) {
		this.numberOfRow = numberOfRow;
	}
	
	
	
}
