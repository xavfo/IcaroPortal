/*
 * Created Jan 9, 2007
 *	Seller.java
 */
package com.iportal.cart.model.catalog;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Lista de Proveedores registrados para ofrecer productos en el
 * cat&aacute;logo del portal.
 *  
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_seller")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="seller",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Seller {

	private String address;
	
	private Long code;
	
	private Calendar creation;
	
	private String name;
	
	private String contactName;
	
	private String summary;
	
	private String description;
	
	private Boolean enabled;
	
	private String email;
	
	private String externalCode;
	
	private String image;
	
	private String logo;
	
	private String phone1;
	
	private String phone2;
	
	private String fax;
	
	private String ruc;
	
	private String legalName;
	
	private transient String imageExtension;
    
	private transient String imageNameNoExtension;

	
	
	/**
	 * 
	 */
	public Seller() {
		super();
		this.code  = null;
		this.name  = null;
		this.logo  = null;
		this.creation  = null;
		this.address   = null;
		this.description = null;
		this.enabled  = null;
		this.email  = null;
		this.externalCode  = null;
		this.image = null;
		this.logo  = null;
		this.phone1  = null;
		this.phone2  = null;
		this.fax  = null;
		this.ruc  = null;
		this.legalName    = null;
		this.contactName  = null;
		this.summary  = null;
		
		this.imageExtension = null;
		this.imageNameNoExtension = null;


	}

	@Transient
	public String getImageExtension() {
		if  (this.imageExtension == null) {
			if ( this.image != null && this.image.length() > 0) {
				setImageExtension(this.image.substring(this.image.lastIndexOf(".")+1).trim());
			}else {
				setImageExtension(null);
			}
		}
		return imageExtension;
	}
    
	/**
     * Devuelve el path del archivo sin extensi�n para presentaci�n de archivo FLASH.
     * @return
     */
    @Transient
	public String getImageNameNoExtension() {
		if  (this.imageNameNoExtension == null) {
			if ( this.image != null && this.image.length() > 0) {
				setImageNameNoExtension(this.image.substring(0,this.image.lastIndexOf(".")).trim());
			}else {
				setImageNameNoExtension(null);
			}
		}
		return imageNameNoExtension;
	}


	/**
	 * @return Returns the address.
	 */
    @Column(name="seller_address")
	public String getAddress() {
		return address;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="seller_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
    public Long getCode() {
		return code;
	}
    

	/**
	 * @return Returns the contactName.
	 */
    @Column(name="seller_contact_name")
	public String getContactName() {
		return contactName;
	}


	/**
	 * @return Returns the creation.
	 */
    @Column(name="seller_creation")
	public Calendar getCreation() {
		return creation;
	}
    

	/**
	 * @return Returns the description.
	 */
    @Column(name="seller_description")
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the email.
	 */
    @Column(name="seller_email")
	public String getEmail() {
		return email;
	}


	/**
	 * @return Returns the enabled.
	 */
    @Column(name="seller_enabled")
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @return Returns the externalCode.
	 */
    @Column(name="seller_external_code")
	public String getExternalCode() {
		return externalCode;
	}


	/**
	 * @return Returns the fax.
	 */
    @Column(name="seller_fax")
	public String getFax() {
		return fax;
	}
    


	/**
	 * @return Returns the image.
	 */
    @Column(name="seller_image")
	public String getImage() {
		return image;
	}


	/**
	 * @return Returns the legalName.
	 */
    @Column(name="seller_legal_name")
	public String getLegalName() {
		return legalName;
	}


	/**
	 * @return Returns the logo.
	 */
    @Column(name="seller_logo")
	public String getLogo() {
		return logo;
	}


	/**
	 * @return Returns the name.
	 */
    @Column(name="seller_name")
	public String getName() {
		return name;
	}


	/**
	 * @return Returns the phone1.
	 */
    @Column(name="seller_phone1")
	public String getPhone1() {
		return phone1;
	}


	/**
	 * @return Returns the phone2.
	 */
    @Column(name="seller_phone2")
	public String getPhone2() {
		return phone2;
	}


	/**
	 * @return Returns the ruc.
	 */
    @Column(name="seller_ruc")
	public String getRuc() {
		return ruc;
	}
    
    


	/**
	 * @return Returns the summary.
	 */
    @Column(name="seller_summary")
	public String getSummary() {
		return summary;
	}


	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}
	
	

	/**
	 * @param contactName The contactName to set.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}


	/**
	 * @param creation The creation to set.
	 */
	public void setCreation(Calendar creation) {
		this.creation = creation;
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
	 * @param externalCode The externalCode to set.
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
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
	 * @param legalName The legalName to set.
	 */
	public void setLegalName(String legalName) {
		this.legalName = legalName;
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


	/**
	 * @param summary The summary to set.
	 */
	public void setSummary(String sumary) {
		this.summary = sumary;
	}
	
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}

	public void setImageNameNoExtension(String imageNameNoExtension) {
		this.imageNameNoExtension = imageNameNoExtension;
	}

	
	

}
