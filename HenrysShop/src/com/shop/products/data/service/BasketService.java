package com.shop.products.data.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.shop.products.data.Basket;
import com.shop.products.data.ShopItem;


public class BasketService {
	
	private static ArrayList<Basket> basketList = new ArrayList<Basket>(); 
	private static double basketTotal = 0.00D;
	private static OfferService offerService = new OfferService();
	private LocalDate dateOfPurchase;
	final static DecimalFormat df2 = new DecimalFormat("#.##");
	
	public BasketService(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public BasketService() {
		this.dateOfPurchase = LocalDate.now();
	}

	public LocalDate getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(LocalDate dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public static ArrayList<Basket> getBasketList() {
		return basketList;
	}

	public static void setBasketList(ArrayList<Basket> basketList) {
		BasketService.basketList = basketList;
	}

	public static double getBasketTotal() {
		double basketTotal1 = 0.00D;
		for (Basket basket:basketList) {
			basketTotal1 += basket.getBasketItemCost();
		}
		return basketTotal1;
	}

	public static void setBasketTotal(double basketTotal) {
		basketTotal = basketTotal;
	}

	public int addToBasket(ShopItem shopItem, int quantity){
		
		
		
		// Merge basket items
		int i = 0;
		boolean itemMerged = false;
		basketTotal = 0;
		for (Basket basket1: basketList ) {
			
			if (basket1.getShopItem().getId() == shopItem.getId()) {
				basket1.setQuantity(basket1.getQuantity()+quantity);
				basket1.setBasketItemCost(basket1.getShopItem().getPrice()*quantity);
				basketList.set(i, basket1);
				
				itemMerged = true;
				
			}
			basketTotal += basket1.getBasketItemCost();
			i++;
		}
		if (!itemMerged) {
			Basket basket = new Basket();
			basket.setShopItem(shopItem);
			basket.setQuantity(quantity);
			basket.setBasketItemCost(basket.getShopItem().getPrice()*quantity);
			
			basketList.add(basket);
			basketTotal += basket.getBasketItemCost();
		}
		return 0;		
	}
	
	public void resetBasket() {
		setBasketList(new ArrayList<Basket>());
		setBasketTotal(0.00D);
	}
	
	public boolean checkIn(ShopItem shopItem, OfferService offerService, int quantity) {
		
		this.offerService = offerService; 
		addToBasket(shopItem, quantity);
		offerService.applyOffersToBasket(this);
		System.out.println("total Basket Price is "+ df2.format(getBasketTotal()));
		
		return true;
		
		
	}
	@Override
	public String toString() {
		
		return "Basket Items are  [ " +basketList.toString()+"]";

	}
	
}
