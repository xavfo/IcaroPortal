/*
 * Created Jan 15, 2007
 *	ProductForm.java
 */
package com.iportal.cart.ctrl.system.catalog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * Forma para almecenar informaci�n de productos relacionados
 * @author hernan
 * @version 1.0
 *
 */
public class ProductsRelatedForm extends BaseForm {
	
    private static final long serialVersionUID = -1603995458786363854L;

    private Long brandCode;
    
    private Long sellerCode;
    
    private Long categoryCode;
    
    private String categoryName;
    
	private Long parentCode;
	
	private Long productCode;
	
	private String productName;
	

	/**
	 * 
	 */
	public ProductsRelatedForm() {
		super();
		this.clear();
	}

	public void clear () {
        this.brandCode    = null;
        this.sellerCode   = null;
        this.categoryCode = null;
        this.categoryName = null;
		this.parentCode    = null;
		this.productCode   = null;
		this.productName   = null;		
	}

	/* (non-Javadoc)
	 * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.clear();
	}

	/**
	 * @return Returns the parentCode.
	 */
	public Long getParentCode() {
		return parentCode;
	}

	/**
	 * @return Returns the productCode.
	 */
	public Long getProductCode() {
		return productCode;
	}

	/**
	 * @return Returns the productName.
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param parentCode The parentCode to set.
	 */
	public void setParentCode(Long parentCode) {
		this.parentCode = parentCode;
	}

	/**
	 * @param productCode The productCode to set.
	 */
	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	/**
	 * @param productName The productName to set.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

    /**
     * @return the brandCode
     */
    public Long getBrandCode() {
        return brandCode;
    }

    /**
     * @param brandCode the brandCode to set
     */
    public void setBrandCode(Long brandCode) {
        this.brandCode = brandCode;
    }

    /**
     * @return the categoryCode
     */
    public Long getCategoryCode() {
        return categoryCode;
    }

    /**
     * @param categoryCode the categoryCode to set
     */
    public void setCategoryCode(Long categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the sellerCode
     */
    public Long getSellerCode() {
        return sellerCode;
    }

    /**
     * @param sellerCode the sellerCode to set
     */
    public void setSellerCode(Long sellerCode) {
        this.sellerCode = sellerCode;
    }

}
