package com.shop.products.data;

public class Basket {
	ShopItem shopItem;
	int quantity;
	double basketItemCost;
	public ShopItem getShopItem() {
		return shopItem;
	}
	public void setShopItem(ShopItem shopItem) {
		this.shopItem = shopItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getBasketItemCost() {
		return basketItemCost;
	}
	public void setBasketItemCost(double basketItemCost) {
		this.basketItemCost = basketItemCost;
	}
	@Override
	public String toString() {
		return "Basket [shopItem=" + shopItem + ", quantity=" + quantity + ", basketItemCost=" + basketItemCost + "]\n";
	}
	
	
	
	
}
