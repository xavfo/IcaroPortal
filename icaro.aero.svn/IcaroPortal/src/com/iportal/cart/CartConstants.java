/*
 * Copyright (c) Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.cart;

import com.yage.Globals;


/**
 * Application constants
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class CartConstants {

    public static final String CART_FLOW_ITEMS = "cart_flow_steps";

    public static final String CART_FLOW_STEP = "cart_flow_step";

    public static final String AIRPORT_HASH_KEY = "iportalAirportsHashMap";
    
    public static final String PAX_TYPE_KEY = "icaroPaxTypeHashMap";
    
    public static final String CART = "cart";

    public static Long CART_RESERVATION_SUCCESS_STATUS = new Long(1);
    
    public static Long CART_PENDING_PAYMENT_STATUS = new Long(2);

    public static Long CART_PAYMENT_SUCCESS_STATUS = new Long(3);
    
    public static Long CART_PAYMENT_FAILED_STATUS = new Long(4);

    public static Long CART_FLOW_SEARCH = new Long(1);

    public static Long CART_FLOW_SELECTION  = new Long(2);

    public static Long CART_FLOW_LOGIN  = new Long(3);

    public static Long CART_FLOW_PASSENGER   = new Long(4);

    public static Long CART_FLOW_SUMMARY   = new Long(5);

    public static Long CART_FLOW_RESULT    = new Long(6);
    
    public static Long CART_FLOW_PAYMENT    = new Long(7);

    public static final String CART_AGENT = "cart_agent";
    
    public static final String CUSTOMER = "customer";
    
    

//    public static final String CUSTOMER_ADDRESS = "customer_address";


    /**
     * External provider User Role (only for multi seller shopping carts)
     */
    public static Long SYS_ROLE_SELLER = new Long(3);

    
    public static String CART_CATALOG_SHOW_ROOM = "cart_catalog_showRoomProducts";
    
    public static String CART_CATALOG_ALL_CATEGORIES = "allCategoriesList";
    
    public static String CART_CATALOG_ALL_BRANDS = "allBrandsList";
    
    public static String CART_CATALOG_ALL_SELLERS = "allSellersList";
    
    
    /**
	 * 
	 */
	public static Integer CART_MAX_DAYS_DELIVERY = new Integer(15);
	
	public static Float CART_VOLUMETRIC_WEIGHT_FACTOR = new Float(6000);
	
	public static Integer CART_MAX_ITEMS = new Integer (1000);
	
    public static Integer CATALOGUE_CATEGORIES_FEATURED = new Integer(3);
    public static Integer CATALOGUE_PRODUCTS_FEATURED = new Integer(3);
    
    public static String SEQ_BRAND = "seq_brand";
	    
    public static String SEQ_BANNER = "seq_banner";
    
	public static String SEQ_PRODUCT = "seq_product";
	
	public static String SEQ_SELLER = "seq_seller";
	
	public static Integer DEFAULT_PAGE_SIZE = Globals.DEFAULT_PAGE_SIZE;
	
	public static Integer DEFAULT_PAGE_NUMBER = Globals.DEFAULT_PAGE_NUMBER;


    /**
     * @return the cART_MAX_DAYS_DELIVERY
     */
    public Integer getCART_MAX_DAYS_DELIVERY() {
        return CART_MAX_DAYS_DELIVERY;
    }

    /**
     * @param cart_max_days_delivery the cART_MAX_DAYS_DELIVERY to set
     */
    public void setCART_MAX_DAYS_DELIVERY(Integer cart_max_days_delivery) {
        CART_MAX_DAYS_DELIVERY = cart_max_days_delivery;
    }

    /**
     * @return the cART_MAX_ITEMS
     */
    public Integer getCART_MAX_ITEMS() {
        return CART_MAX_ITEMS;
    }

    /**
     * @param cart_max_items the cART_MAX_ITEMS to set
     */
    public void setCART_MAX_ITEMS(Integer cart_max_items) {
        CART_MAX_ITEMS = cart_max_items;
    }

    /**
     * @return the cART_VOLUMETRIC_WEIGHT_FACTOR
     */
    public Float getCART_VOLUMETRIC_WEIGHT_FACTOR() {
        return CART_VOLUMETRIC_WEIGHT_FACTOR;
    }

    /**
     * @param cart_volumetric_weight_factor the cART_VOLUMETRIC_WEIGHT_FACTOR to set
     */
    public void setCART_VOLUMETRIC_WEIGHT_FACTOR(
            Float cart_volumetric_weight_factor) {
        CART_VOLUMETRIC_WEIGHT_FACTOR = cart_volumetric_weight_factor;
    }

    /**
     * @return the cATALOGUE_CATEGORIES_FEATURED
     */
    public Integer getCATALOGUE_CATEGORIES_FEATURED() {
        return CATALOGUE_CATEGORIES_FEATURED;
    }

    /**
     * @param catalogue_categories_featured the cATALOGUE_CATEGORIES_FEATURED to set
     */
    public void setCATALOGUE_CATEGORIES_FEATURED(
            Integer catalogue_categories_featured) {
        CATALOGUE_CATEGORIES_FEATURED = catalogue_categories_featured;
    }

    /**
     * @return the cATALOGUE_PRODUCTS_FEATURED
     */
    public Integer getCATALOGUE_PRODUCTS_FEATURED() {
        return CATALOGUE_PRODUCTS_FEATURED;
    }

    /**
     * @param catalogue_products_featured the cATALOGUE_PRODUCTS_FEATURED to set
     */
    public void setCATALOGUE_PRODUCTS_FEATURED(
            Integer catalogue_products_featured) {
        CATALOGUE_PRODUCTS_FEATURED = catalogue_products_featured;
    }

    /**
     * @return the sEQ_BANNER
     */
    public String getSEQ_BANNER() {
        return SEQ_BANNER;
    }

    /**
     * @param seq_banner the sEQ_BANNER to set
     */
    public void setSEQ_BANNER(String seq_banner) {
        SEQ_BANNER = seq_banner;
    }

    /**
     * @return the sEQ_BRAND
     */
    public String getSEQ_BRAND() {
        return SEQ_BRAND;
    }

    /**
     * @param seq_brand the sEQ_BRAND to set
     */
    public void setSEQ_BRAND(String seq_brand) {
        SEQ_BRAND = seq_brand;
    }

    /**
     * @return the sEQ_PRODUCT
     */
    public String getSEQ_PRODUCT() {
        return SEQ_PRODUCT;
    }

    /**
     * @param seq_product the sEQ_PRODUCT to set
     */
    public void setSEQ_PRODUCT(String seq_product) {
        SEQ_PRODUCT = seq_product;
    }

    /**
     * @return the sEQ_SELLER
     */
    public String getSEQ_SELLER() {
        return SEQ_SELLER;
    }

    /**
     * @param seq_seller the sEQ_SELLER to set
     */
    public void setSEQ_SELLER(String seq_seller) {
        SEQ_SELLER = seq_seller;
    }
    
    

	public String getCART_CATALOG_ALL_BRANDS() {
		return CART_CATALOG_ALL_BRANDS;
	}

    public String getCART_CATALOG_ALL_CATEGORIES() {
		return CART_CATALOG_ALL_CATEGORIES;
	}
	
	public String getCART_CATALOG_ALL_SELLERS() {
		return CART_CATALOG_ALL_SELLERS;
	}

	/**
	 * @return Returns the cART_CATALOG_SHOW_ROOM.
	 */
	public String getCART_CATALOG_SHOW_ROOM() {
		return CART_CATALOG_SHOW_ROOM;
	}

	public void setCART_CATALOG_ALL_BRANDS(String cart_catalog_all_brands) {
		CART_CATALOG_ALL_BRANDS = cart_catalog_all_brands;
	}

	public void setCART_CATALOG_ALL_CATEGORIES(
			String cart_catalog_all_categories) {
		CART_CATALOG_ALL_CATEGORIES = cart_catalog_all_categories;
	}

	public void setCART_CATALOG_ALL_SELLERS(String cart_catalog_all_sellers) {
		CART_CATALOG_ALL_SELLERS = cart_catalog_all_sellers;
	}

	/**
	 * @param cart_catalog_show_room The cART_CATALOG_SHOW_ROOM to set.
	 */
	public void setCART_CATALOG_SHOW_ROOM(String cart_catalog_show_room) {
		CART_CATALOG_SHOW_ROOM = cart_catalog_show_room;
	}

	public Long getCART_FLOW_PASSENGER() {
		return CART_FLOW_PASSENGER;
	}

	public Long getCART_FLOW_LOGIN() {
		return CART_FLOW_LOGIN;
	}

	public Long getCART_FLOW_RESULT() {
		return CART_FLOW_RESULT;
	}

	public Long getCART_FLOW_SEARCH() {
		return CART_FLOW_SEARCH;
	}

	public Long getCART_FLOW_SELECTION() {
		return CART_FLOW_SELECTION;
	}

	public Long getCART_FLOW_SUMMARY() {
		return CART_FLOW_SUMMARY;
	}

	public void setCART_FLOW_PASSENGER(Long cart_flow_passenger) {
		CART_FLOW_PASSENGER = cart_flow_passenger;
	}

	public void setCART_FLOW_PASSENGER(String cart_flow_passenger) {
		CART_FLOW_PASSENGER = Long.valueOf(cart_flow_passenger);
	}

	public void setCART_FLOW_LOGIN(Long cart_flow_price) {
		CART_FLOW_LOGIN = cart_flow_price;
	}

	public void setCART_FLOW_PRICE(String cart_flow_price) {
		CART_FLOW_LOGIN = Long.valueOf(cart_flow_price);
	}

	public void setCART_FLOW_RESULT(Long cart_flow_result) {
		CART_FLOW_RESULT = cart_flow_result;
	}

	public void setCART_FLOW_RESULT(String cart_flow_result) {
		CART_FLOW_RESULT = Long.valueOf(cart_flow_result);
	}

	public void setCART_FLOW_SEARCH(Long cart_flow_search) {
		CART_FLOW_SEARCH = cart_flow_search;
	}

	public void setCART_FLOW_SEARCH(String cart_flow_search) {
		CART_FLOW_SEARCH = Long.valueOf(cart_flow_search);
	}

	public void setCART_FLOW_SELECTION(Long cart_flow_selection) {
		CART_FLOW_SELECTION = cart_flow_selection;
	}

	public void setCART_FLOW_SELECTION(String cart_flow_selection) {
		CART_FLOW_SELECTION = Long.valueOf(cart_flow_selection);
	}

	public void setCART_FLOW_SUMMARY(Long cart_flow_summary) {
		CART_FLOW_SUMMARY = cart_flow_summary;
	}
	
	public void setCART_FLOW_SUMMARY(String cart_flow_summary) {
		CART_FLOW_SUMMARY = Long.valueOf(cart_flow_summary);
	}

	/**
	 * @return the cART_FLOW_PAYMENT
	 */
	public Long getCART_FLOW_PAYMENT() {
		return CART_FLOW_PAYMENT;
	}

	/**
	 * @return the cART_PAYMENT_FAILED_STATUS
	 */
	public Long getCART_PAYMENT_FAILED_STATUS() {
		return CART_PAYMENT_FAILED_STATUS;
	}

	/**
	 * @return the cART_PAYMENT_SUCCESS_STATUS
	 */
	public Long getCART_PAYMENT_SUCCESS_STATUS() {
		return CART_PAYMENT_SUCCESS_STATUS;
	}

	/**
	 * @return the cART_PENDING_PAYMENT_STATUS
	 */
	public Long getCART_PENDING_PAYMENT_STATUS() {
		return CART_PENDING_PAYMENT_STATUS;
	}

	/**
	 * @return the cART_RESERVATION_SUCCESS_STATUS
	 */
	public Long getCART_RESERVATION_SUCCESS_STATUS() {
		return CART_RESERVATION_SUCCESS_STATUS;
	}

	/**
	 * @param cart_flow_payment the cART_FLOW_PAYMENT to set
	 */
	public void setCART_FLOW_PAYMENT(Long cart_flow_payment) {
		CART_FLOW_PAYMENT = cart_flow_payment;
	}

	/**
	 * @param cart_flow_payment the cART_FLOW_PAYMENT to set
	 */
	public void setCART_FLOW_PAYMENT(String cart_flow_payment) {
		CART_FLOW_PAYMENT = Long.valueOf(cart_flow_payment);
	}


	/**
	 * @param cart_payment_failed_status the cART_PAYMENT_FAILED_STATUS to set
	 */
	public void setCART_PAYMENT_FAILED_STATUS(Long cart_payment_failed_status) {
		CART_PAYMENT_FAILED_STATUS = cart_payment_failed_status;
	}

	public void setCART_PAYMENT_FAILED_STATUS(String cart_payment_failed_status) {
		CART_PAYMENT_FAILED_STATUS = Long.valueOf(cart_payment_failed_status);
	}

	/**
	 * @param cart_payment_success_status the cART_PAYMENT_SUCCESS_STATUS to set
	 */
	public void setCART_PAYMENT_SUCCESS_STATUS(
			Long cart_payment_success_status) {
		CART_PAYMENT_SUCCESS_STATUS = cart_payment_success_status;
	}

	public void setCART_PAYMENT_SUCCESS_STATUS(
			String cart_payment_success_status) {
		CART_PAYMENT_SUCCESS_STATUS = Long.valueOf(cart_payment_success_status);
	}

	/**
	 * @param cart_pending_payment_status the cART_PENDING_PAYMENT_STATUS to set
	 */
	public void setCART_PENDING_PAYMENT_STATUS(
			Long cart_pending_payment_status) {
		CART_PENDING_PAYMENT_STATUS = cart_pending_payment_status;
	}

	public void setCART_PENDING_PAYMENT_STATUS(
			String cart_pending_payment_status) {
		CART_PENDING_PAYMENT_STATUS = Long.valueOf(cart_pending_payment_status);
	}

	/**
	 * @param cart_reservation_success_status the cART_RESERVATION_SUCCESS_STATUS to set
	 */
	public void setCART_RESERVATION_SUCCESS_STATUS(
			Long cart_reservation_success_status) {
		CART_RESERVATION_SUCCESS_STATUS = cart_reservation_success_status;
	}
	
	public void setCART_RESERVATION_SUCCESS_STATUS(
			String cart_reservation_success_status) {
		CART_RESERVATION_SUCCESS_STATUS = Long.valueOf(cart_reservation_success_status);
	}
	

	
	
	
	
	
	
	
	
	
    
    
	
	
}
