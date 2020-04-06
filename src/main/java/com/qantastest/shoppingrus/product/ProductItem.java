package com.qantastest.shoppingrus.product;

import java.math.BigDecimal;

public class ProductItem {
	
	private final String sku;
	private final String name;
	private final BigDecimal price;
	
	public ProductItem(String sku, String name, BigDecimal price) {
		super();
		this.sku = sku;
		this.name = name;
		this.price = price;
	}
	
	public String getSku() {
		return sku;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}

}
