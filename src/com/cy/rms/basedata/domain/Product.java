package com.cy.rms.basedata.domain;

public class Product {

	private int id;
	private String name;
	private float priceIn;
	private float priceOut;
	private int stock;
	private int sell;
	private Category category;
	private Factory factory;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(float priceIn) {
		this.priceIn = priceIn;
	}
	public float getPriceOut() {
		return priceOut;
	}
	public void setPriceOut(float priceOut) {
		this.priceOut = priceOut;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSell() {
		return sell;
	}
	public void setSell(int sell) {
		this.sell = sell;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}


	
}
