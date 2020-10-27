package com.shop.products.data.service;

import java.util.ArrayList;

import com.shop.products.data.Basket;
import com.shop.products.data.ShopItem;

public class ShopItemService {

	static ArrayList<ShopItem> shopItemList = new ArrayList<ShopItem>();
	
	public boolean initialiseData(){
		
		shopItemList.add(new ShopItem(1,"soup",0.65,"tin"));
		shopItemList.add(new ShopItem(2,"bread",0.80,"loaf"));
		shopItemList.add(new ShopItem(3,"milk",1.30,"bottle"));
		shopItemList.add(new ShopItem(4,"apples",0.1,"single"));
		
		
		return true;
		
	}

	public static ArrayList<ShopItem> getShopItemList() {
		return shopItemList;
	}

	public static void setShopItemList(ArrayList<ShopItem> shopItemList) {
		ShopItemService.shopItemList = shopItemList;
	}
	
	public ShopItem find(int shopItemId) {
		for (ShopItem shopItem:shopItemList) {
			if (shopItemId == shopItem.getId()) {
				return shopItem;
			}
		}
		return null;
	}
	
	public void clearData() {
		shopItemList = new ArrayList<ShopItem>();
	}
	
	
	
	@Override
	public String toString() {
				
		return "Offers are  [ " +shopItemList.toString()+"]";

	}
	
	
	
	

}
