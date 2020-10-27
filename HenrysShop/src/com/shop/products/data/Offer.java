package com.shop.products.data;

import java.sql.Date;
import java.time.LocalDate;

public class Offer {
	int		 id;
	String   offerName;
	ShopItem offerShopItem;
	ShopItem discountedShopItem;
	int 	 minQuantity;
	int      discountedQuantity;
	double	 discountPercent;
	LocalDate	 validFrom;
	LocalDate	 validTo;
	
	public Offer(int id, String   offerName, ShopItem offerShopItem, ShopItem discountedShopItem, int minQuantity, int discountedQuantity,
			double discountPercent, LocalDate validFrom, LocalDate validTo) {
		super();
		this.id = id;
		this.offerName = offerName;
		this.offerShopItem = offerShopItem;
		this.discountedShopItem = discountedShopItem;
		this.minQuantity = minQuantity;
		this.discountedQuantity = discountedQuantity;
		this.discountPercent = discountPercent;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}
	public int getId() {
		return id;
	}
	
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ShopItem getOfferShopItem() {
		return offerShopItem;
	}
	public void setOfferShopItem(ShopItem offerShopItem) {
		this.offerShopItem = offerShopItem;
	}
	public ShopItem getDiscountedShopItem() {
		return discountedShopItem;
	}
	public void setDiscountedShopItem(ShopItem discountedShopItem) {
		this.discountedShopItem = discountedShopItem;
	}
	
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	
	public int getDiscountedQuantity() {
		return discountedQuantity;
	}
	public void setDiscountedQuantity(int discountedQuantity) {
		this.discountedQuantity = discountedQuantity;
	}
	public double getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
	public LocalDate getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDate getValidTo() {
		return validTo;
	}
	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", offerName=" + offerName + ", offerShopItem=" + offerShopItem.getItemName()
				+ ", discountedShopItem=" + discountedShopItem.getItemName() + ", minQuantity=" + minQuantity
				+ ", discountedQuantity=" + discountedQuantity + ", discountPercent=" + discountPercent + ", validFrom="
				+ validFrom + ", validTo=" + validTo + "]\n";
	}
	
	
	
}
