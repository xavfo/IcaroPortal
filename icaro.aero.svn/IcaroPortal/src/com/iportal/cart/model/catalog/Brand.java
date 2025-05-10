/*
 * Created Jan 9, 2007
 *	Brand.java
 */
package com.iportal.cart.model.catalog;

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
 * Datos de Marca o fabricante de productos ofrecidos en el
 * cat&aacuta;logo de compras.
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_brand")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="brand",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Brand {

	
	private Long code;
	
	private String description;
	
	private String image;
	
	private String name;
	
	private String logo;
	
	private String url;
	
	private transient String imageExtension;
    
	private transient String imageNameNoExtension;


	
	/**
	 * 
	 */
	public Brand() {
		super();
		this.code  = null;
		this.image = null;
		this.name  = null;
		this.logo  = null;
		this.url   = null;
		this.description = null;
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
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="brand_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	
	public Long getCode() {
		return code;
	}
    
    


	/**
	 * @return Returns the description.
	 */
    @Column(name="brand_description")
	public String getDescription() {
		return description;
	}

	/**
	 * @return Returns the image.
	 */
    @Column(name="brand_image")
	public String getImage() {
		return image;
	}


	/**
	 * @return Returns the logo.
	 */
    @Column(name="brand_logo")
	public String getLogo() {
		return logo;
	}


	/**
	 * @return Returns the name.
	 */
    @Column(name="brand_name")
	public String getName() {
		return name;
	}


	/**
	 * Link al sitio web del fabricante. preferible a sitio de atenci�n al cliente local.
	 * @return Returns the url.
	 */
    @Column(name="brand_url")
	public String getUrl() {
		return url;
	}


	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param description The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setImageExtension(String imageExtension) {
		this.imageExtension = imageExtension;
	}

	public void setImageNameNoExtension(String imageNameNoExtension) {
		this.imageNameNoExtension = imageNameNoExtension;
	}


}
