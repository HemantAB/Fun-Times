package com.shop.products.data.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import com.shop.products.data.Basket;
import com.shop.products.data.Offer;
import com.shop.products.data.ShopItem;

public class OfferService {
	
	private class ApplicableOffer {
		int offerId;
		ShopItem discountedShopItem;
		int discountAvaliableQuantity;
		double discountPercent;
		boolean offerApplied;
		public int getOfferId() {
			return offerId;
		}
		public void setOfferId(int offerId) {
			this.offerId = offerId;
		}
		public ShopItem getDiscountedShopItem() {
			return discountedShopItem;
		}
		public void setDiscountedShopItem(ShopItem discountedShopItem) {
			this.discountedShopItem = discountedShopItem;
		}
		public int getDiscountAvaliableQuantity() {
			return discountAvaliableQuantity;
		}
		public void setDiscountAvaliableQuantity(int discountAvaliableQuantity) {
			this.discountAvaliableQuantity = discountAvaliableQuantity;
		}
		public double getDiscountPercent() {
			return discountPercent;
		}
		public void setDiscountPercent(double discountPercent) {
			this.discountPercent = discountPercent;
		}
		public boolean isOfferApplied() {
			return offerApplied;
		}
		public void setOfferApplied(boolean offerApplied) {
			this.offerApplied = offerApplied;
		}
		public ApplicableOffer(int offerId, ShopItem discountedShopItem, int discountAvaliableQuantity,
				double discountPercent, boolean offerApplied) {
			super();
			this.offerId = offerId;
			this.discountedShopItem = discountedShopItem;
			this.discountAvaliableQuantity = discountAvaliableQuantity;
			this.discountPercent = discountPercent;
			this.offerApplied = offerApplied;
		}
		
		
		
	}
	
	
	static ArrayList<Offer> offerList = new ArrayList<Offer>();
	
	static ArrayList<ApplicableOffer> applicableOffer = new ArrayList<ApplicableOffer>();

	public boolean initialiseData() {
		ShopItemService shopItemService = new ShopItemService();
		ArrayList<ShopItem> shopItemList = shopItemService.getShopItemList();
		
		ShopItem shopItemOffer = shopItemService.find(1);
		ShopItem shopItemDiscounted = shopItemService.find(2);
		LocalDate offerFrom = LocalDate.now().minusDays(1);
		
		LocalDate offerEndDate = LocalDate.now().minusDays(1).plusDays(7);
		Offer offer = new Offer(1,"Buy 2 tins of soup and get a loaf of bread half price",shopItemOffer,shopItemDiscounted,2,1,0.5D,offerFrom,offerEndDate);
		
		offerList.add(offer);
		
		shopItemOffer = shopItemService.find(4);
		offerFrom = LocalDate.now().plusDays(3);
		
		offerEndDate = offerFrom.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
		offer = new Offer(2,"Apples have a 10% discount",shopItemOffer,shopItemOffer,1,1,0.9D,offerFrom,offerEndDate);
		offerList.add(offer);
				
		return true;		
	}
	
	
	
	
	public static ArrayList<Offer> getOfferList() {
		return offerList;
	}

	public static void setOfferList(ArrayList<Offer> offerList) {
		OfferService.offerList = offerList;
	}

	public int addOffer(Offer offer) {
		offerList.add(offer);
		return 1;		
	}
	
	public Offer find(int offerId) {
		for (Offer offer:offerList) {
			if (offerId == offer.getId()) {
				return offer;
			}
		}
		return null;
	}

	public void clearData() {
		offerList = new ArrayList<Offer>();
	}
	
	
	public int applyOffersToBasket(BasketService basketService) {
		LocalDate localDate = basketService.getDateOfPurchase();
		ArrayList<Basket> basketList = basketService.getBasketList();
		double basketTotal = 0.00D;
				
		List<ApplicableOffer> applicableOfferList = new ArrayList<ApplicableOffer>();
		for (Offer offer: offerList) {
			if (localDate.isAfter(offer.getValidFrom()) && localDate.isBefore(offer.getValidTo())) {
				for (Basket basket:basketList) {
					
					if (basket.getShopItem() == offer.getOfferShopItem()) {
							if (basket.getQuantity() >= offer.getMinQuantity()) {
								int offerMultiples = basket.getQuantity()/offer.getMinQuantity();
								ApplicableOffer applicableOffer = new ApplicableOffer(
										offer.getId(),
										offer.getDiscountedShopItem(),
										offerMultiples*offer.getDiscountedQuantity(),
										offer.getDiscountPercent(),
										false								
										);
								applicableOfferList.add(applicableOffer);								
							}
					}
					
					
				}
					
					
				}

			}
		
		for (ApplicableOffer applicableOffer:applicableOfferList) {
			Basket basket = new Basket();
			for (int  i =0; i < basketList.size();i++) {
				basket = basketList.get(i);
				if (basket.getShopItem().getId() == applicableOffer.getDiscountedShopItem().getId()) {
					if (basket.getQuantity()>=applicableOffer.getDiscountAvaliableQuantity()) {
						
						basket.setBasketItemCost(applicableOffer.getDiscountAvaliableQuantity()*basket.getShopItem().getPrice()*applicableOffer.getDiscountPercent() +
								((basket.getQuantity()-applicableOffer.getDiscountAvaliableQuantity())*basket.getShopItem().getPrice())  
								);
						//System.out.println("Offer "+ find(applicableOffer.getOfferId()).getOfferName() +" is applied Item cost is "+basket.getBasketItemCost() );
					} else {

						basket.setBasketItemCost(applicableOffer.getDiscountAvaliableQuantity()*basket.getShopItem().getPrice()*applicableOffer.getDiscountPercent()   
								);
						
					}
					
					
				}
				basketTotal += basket.getBasketItemCost();
				basketList.set(i, basket);
				//System.out.println("Offer Service Applying offers current Basket Total is "+basketTotal );
				
			}
			
			
			
		}		
		basketService.setBasketList(basketList);
		System.out.println("Offer Service current Basket Total is "+basketTotal );
		basketService.setBasketTotal(basketTotal);
		
		System.out.println("Items in basket arr" + basketService.toString());
		return 0;		
	}




	@Override
	public String toString() {
				
		return "Offers are  [ " +offerList.toString()+"]";

	}

	
	
}
