package com.shop.products.data;

public class ShopItem {
	Integer id;
	String 	ItemName;
	Double	price;
	String	unit;
	
	
	public ShopItem(Integer id, String itemName, double price, String unit) {
		super();
		this.id = id;
		ItemName = itemName;
		this.price = price;
		this.unit = unit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "ShopItem [id=" + id + ", ItemName=" + ItemName + ", price=" + price + ", unit=" + unit + "]\n";
	}

	
	
	
	
}
