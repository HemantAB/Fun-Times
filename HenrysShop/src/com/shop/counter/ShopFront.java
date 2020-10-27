package com.shop.counter;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.shop.products.data.ShopItem;
import com.shop.products.data.service.BasketService;
import com.shop.products.data.service.OfferService;
import com.shop.products.data.service.ShopItemService;

public class ShopFront {

	
	private static ShopItemService shopItemService = new ShopItemService();
	private static BasketService basketService = new BasketService();
	private static OfferService offerService = new OfferService();
	private static 	boolean repeat = true;
	final static DecimalFormat df2 = new DecimalFormat("#.##");
	

	
	private static void startCheckIn() throws Exception{
		System.out.println("Enter shop item id as -100 if you want to exit checkout");
		
		Scanner shopItemObj = new Scanner(System.in);
		System.out.println("Enter shop item id");
		int shopItemId = shopItemObj.nextInt();
		if (shopItemId == -100) {
			repeat = false;
			throw new Exception();
		}
		ShopItem shopItem = shopItemService.find(shopItemId);
		if (shopItem == null) {
			System.out.println("You have added invalid shop item id");
			throw new Exception();
		} else 
		System.out.println("You have added "+shopItem.toString());
		
		System.out.println("Enter Quantity");
		int quantity = shopItemObj.nextInt();
		System.out.println("Item Quantity is  "+quantity);
		
		basketService.checkIn(shopItem, offerService, quantity);
		
		
	}

	public static void main(String[] args) {
		
		
		
		
		shopItemService.initialiseData();
		ArrayList<ShopItem> shopItemList = shopItemService.getShopItemList();		
		offerService.initialiseData();
		
		
		System.out.println("Shop Items are" +shopItemService.toString());
		System.out.println("Offers are" +offerService.toString());
		
		try {
			basketService.resetBasket();
			while(repeat) {
			try {	
				startCheckIn();
				
			} catch(Exception e) {
				System.out.println( basketService.toString());
				System.out.println("Total Basket Price is " + df2.format(basketService.getBasketTotal()));
			} finally {
				
			}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exiting Checkout");

			e.printStackTrace();
		}
		
		
		

		
	}

}
