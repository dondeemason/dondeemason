package com.oms.model;

import java.text.SimpleDateFormat;

public class ExpectedOrders {
	private String productName;
	private String deliveryDate;
	private int quantities;
	SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	/**
	 * @return the quantities
	 */
	public int getQuantities() {
		return quantities;
	}
	/**
	 * @param quantities the quantities to set
	 */
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}


}