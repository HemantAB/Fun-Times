package com.products.data.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.shop.products.data.ShopItem;
import com.shop.products.data.service.BasketService;
import com.shop.products.data.service.OfferService;
import com.shop.products.data.service.ShopItemService;

class BasketServiceTest {

	private static ShopItemService shopItemService = new ShopItemService();
	private static BasketService basketService = new BasketService();
	private static OfferService offerService = new OfferService();
	
	@BeforeEach
	void setUp() throws Exception {
		shopItemService.clearData();
		offerService.clearData();
		shopItemService.initialiseData();
		offerService.initialiseData();
		
	}


	@Test
	void testAddSoupAndBreadToBasket() {
		basketService.resetBasket();
		ShopItem shopItemSoup = shopItemService.find(1);
		basketService.checkIn(shopItemSoup, offerService, 3);
		ShopItem shopItemBread = shopItemService.find(2);
		basketService.checkIn(shopItemBread, offerService, 2);
		//System.out.println("test total price is"+basketService.getBasketTotal());
		assertEquals(basketService.getBasketTotal(),3.15,"Test passed");
		
		
	}

	@Test
	void testAddMilkAndApplesToBasket() {
		basketService.resetBasket();
		ShopItem shopItemApples = shopItemService.find(4);
		basketService.checkIn(shopItemApples, offerService, 6);
		ShopItem shopItemMilk = shopItemService.find(3);
		basketService.checkIn(shopItemMilk, offerService, 1);
		//System.out.println("test total price is"+basketService.getBasketTotal());
		assertEquals(basketService.getBasketTotal(),1.9,"Test passed");
		
		
	}
	
	@Test
	void testAddMilkAndApplesToBasketAfterFiveDays() {
		basketService.resetBasket();
		basketService.setDateOfPurchase(LocalDate.now().plusDays(5));
		ShopItem shopItemApples = shopItemService.find(4);
		basketService.checkIn(shopItemApples, offerService, 6);
		ShopItem shopItemMilk = shopItemService.find(3);
		basketService.checkIn(shopItemMilk, offerService, 1);
		//System.out.println("test total price is"+basketService.getBasketTotal());
		assertEquals(basketService.getBasketTotal(),1.84,"Test passed");				
	}
	
	@Test
	void testAddSoupAndApplesAndBreadToBasketAfterFiveDays() {
		basketService.resetBasket();
		basketService.setDateOfPurchase(LocalDate.now().plusDays(5));
		ShopItem shopItemApples = shopItemService.find(4);
		basketService.checkIn(shopItemApples, offerService, 3);
		ShopItem shopItemBread = shopItemService.find(2);
		basketService.checkIn(shopItemBread, offerService, 1);
		ShopItem shopItemSoup = shopItemService.find(1);
		basketService.checkIn(shopItemSoup, offerService, 2);	
		//System.out.println("test total price is"+basketService.getBasketTotal());
		assertEquals(basketService.getBasketTotal(),1.97,"Test passed");				
	}
	
	
	
	
}
