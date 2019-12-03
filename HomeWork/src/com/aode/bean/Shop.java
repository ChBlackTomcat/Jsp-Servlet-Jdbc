package com.aode.bean;

public class Shop {
	private int id;
	private String shopName;
	private double prvice;
	private String time;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String bookName) {
		this.shopName = bookName;
	}
	
	public double getPrvice() {
		return prvice;
	}
	public void setPrvice(double prvice) {
		this.prvice = prvice;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Shop(String shopName, double prvice, String time) {
		super();
		this.shopName = shopName;
		this.prvice = prvice;
		this.time = time;
	}
	public Shop() {
		super();
	}
	public Shop(int id, String shopName, double prvice, String time) {
		super();
		this.id = id;
		this.shopName = shopName;
		this.prvice = prvice;
		this.time = time;
	}
	
	
}
