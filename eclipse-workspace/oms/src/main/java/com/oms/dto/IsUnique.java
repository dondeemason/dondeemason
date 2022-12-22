package com.oms.dto;

public class IsUnique {
	private boolean isUnique;
	private String message;
	
	public IsUnique() {
		
	}
	
	public IsUnique(boolean isUnique, String message) {
		super();
		this.isUnique = isUnique;
		this.message = message;
	}
	public boolean isUnique() {
		return isUnique;
	}
	public void setUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
