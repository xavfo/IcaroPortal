/*
 * Created Jan 15, 2007
 *	PaymentTypeForm.java
 */
package com.iportal.cart.ctrl.system.cart;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * Forma para recoger datos de tipos de pago.
 * @author hernan
 * @version 1.0
 *
 */
public class PaymentTypeForm extends BaseForm {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1049712528204351948L;

	private String name;
	
	private Boolean enabled;
	
	private Boolean restricted;
	
	private String externalCode;
	
	private String logo;
	
	private Integer enabledOption;
	
	private Integer restrictedOption;
	
	private Float discount;

	/**
	 * 
	 */
	public PaymentTypeForm() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.name  = null;
		this.logo  = null;
		this.externalCode = null;
		this.discount = null;
		this.enabledOption = null;
		this.enabled  = null;
		this.restricted = null;
		this.restrictedOption = null;
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
	 * @return Returns the discount.
	 */
	public Float getDiscount() {
		return discount;
	}

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return Returns the enabledOption.
	 */
	public Integer getEnabledOption() {
		return enabledOption;
	}

	/**
	 * @return Returns the externalCode.
	 */
	public String getExternalCode() {
		return externalCode;
	}

	/**
	 * @return Returns the logo.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the restricted.
	 */
	public Boolean getRestricted() {
		return restricted;
	}

	/**
	 * @return Returns the restrictedOption.
	 */
	public Integer getRestrictedOption() {
		return restrictedOption;
	}

	/**
	 * @param discount The discount to set.
	 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @param enabledOption The enabledOption to set.
	 */
	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	/**
	 * @param externalCode The externalCode to set.
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	/**
	 * @param logo The logo to set.
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param restricted The restricted to set.
	 */
	public void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}

	/**
	 * @param restrictedOption The restrictedOption to set.
	 */
	public void setRestrictedOption(Integer restrictedOption) {
		this.restrictedOption = restrictedOption;
	}
	
	

	


	
	


}
