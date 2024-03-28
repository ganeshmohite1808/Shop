package edu.jsp.model;

public class Product {
	private int id;
	private String productName;
	private double price;
	private int quantity;
	private boolean availabilty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isAvailabilty() {
		return availabilty;
	}
	public void setAvailabilty(boolean availabilty) {
		this.availabilty = availabilty;
	}
	
	
}
