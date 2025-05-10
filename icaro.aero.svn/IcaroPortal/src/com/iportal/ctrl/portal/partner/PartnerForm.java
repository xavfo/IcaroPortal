/*
 * Created Jan 15, 2007
 *	ProductForm.java
 */
package com.iportal.ctrl.portal.partner;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.Globals;
import com.yage.struts.action.BaseForm;

/**
 * @author Fernando T
 * 
 *
 */
public class PartnerForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 483209370855832778L;


	private String name;
	
	private String ruc;
	
	private String description;
	
	private String address;	
	
	private String phone1;
	
	private String phone2;
	
	private String fax;
	
	private String mobil;
	
	private String email;
	
	private String url;
	
	private String logo;
	
	private String image;
	
	private String profit;
	
	private Boolean corporative;
	
	private Boolean promoter;
	
	private Boolean enabled;
	
	private Boolean month;
	
	private Long categoryCode;
	
	private Integer enabledOption;
	
	private Long cityCode;
	



	/**
	 * 
	 */
	public PartnerForm() {
		super();
		this.clear();
	}
	
	public void clear () {
		this.name  = null;
		this.ruc  = null;
		this.description = null;	
		this.address   = null;	
		this.phone1  = null;
		this.phone2  = null;
		this.fax  = null;
		this.mobil  = null;
		this.url  = null;		
		this.logo  = null;
		this.image = null;
		this.profit = null;
		this.corporative = Globals.FALSE;
		this.promoter = Globals.FALSE;
		this.month = Globals.FALSE;
		this.enabled  = Globals.TRUE;
		this.email  = null;
		this.categoryCode = null;
		this.cityCode = null;
		
	}

	/* (non-Javadoc)
	 * @see com.yage.struts.action.BaseForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.clear();
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}



	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Returns the enabled.
	 */
	public Boolean getEnabled() {
		return enabled;
	}



	/**
	 * @return Returns the fax.
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @return Returns the image.
	 */
	public String getImage() {
		return image;
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
	 * @return Returns the phone1.
	 */
	public String getPhone1() {
		return phone1;
	}

	/**
	 * @return Returns the phone2.
	 */
	public String getPhone2() {
		return phone2;
	}

	/**
	 * @return Returns the ruc.
	 */
	public String getRuc() {
		return ruc;
	}

	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @param fax The fax to set.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @param image The image to set.
	 */
	public void setImage(String image) {
		this.image = image;
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
	 * @param phone1 The phone1 to set.
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * @param phone2 The phone2 to set.
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * @param ruc The ruc to set.
	 */
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}



	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}


	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}



	public Boolean getCorporative() {
		return corporative;
	}

	public Boolean getPromoter() {
		return promoter;
	}

	public Boolean getMonth() {
		return month;
	}

	public void setMonth(Boolean month) {
		this.month = month;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCorporative(Boolean corporative) {
		this.corporative = corporative;
	}

	public void setPromoter(Boolean promoter) {
		this.promoter = promoter;
	}

	public Integer getEnabledOption() {
		return enabledOption;
	}

	public void setEnabledOption(Integer enabledOption) {
		this.enabledOption = enabledOption;
	}

	public Long getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Long categoryCode) {
		this.categoryCode = categoryCode;
	}
	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

}
