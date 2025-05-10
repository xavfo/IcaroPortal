/**
 * 
 */
package com.iportal.cart.ctrl.catalog;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.yage.struts.action.BaseForm;

/** 
 * Forma de busqueda de productos
 * @author hernan
 * @version 1.0
 *
 */
public class SearchProductForm extends BaseForm {

	private static final long serialVersionUID = -4557893874960903952L;

	private String listForward;
	
	private Long categoryCode;
	
	private Long brandCode;
	
	private Long sellerCode;
	
	private Boolean onSale;
	
	private Long[] compareCodes;
	
	
	public SearchProductForm() {
		super();
		this.clear();
	}

	public void clear () {
		this.brandCode    = null;
		this.sellerCode   = null;
		this.categoryCode = null;
		this.compareCodes = null;
		this.listForward  = null;
		this.onSale       = null;
	}

	/* (non-Javadoc)
	 * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.clear();
		super.reset(mapping, request);
		this.setPageSize(Constants.PAGE_SIZE);
		this.setPageNumber(Constants.PAGE_NUMBER);
		this.listForward = null;

	}

	public Long getBrandCode() {
		return brandCode;
	}

	public Long getCategoryCode() {
		return categoryCode;
	}

	public Long[] getCompareCodes() {
		return compareCodes;
	}

	public String getListForward() {
		return listForward;
	}

	public Boolean getOnSale() {
		return onSale;
	}

	public Long getSellerCode() {
		return sellerCode;
	}

	public void setBrandCode(Long brandCode) {
		this.brandCode = brandCode;
	}

	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCompareCodes(Long[] compareCodes) {
		this.compareCodes = compareCodes;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public void setListForward(String listForward) {
		this.listForward = listForward;
	}

	public void setSellerCode(Long sellerCode) {
		this.sellerCode = sellerCode;
	}

}
