/*
 * Created 21/03/2007
 *	CheckoutStep.java
 */
package com.iportal.cart.model.cart;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Clase con la información de cada paso en el proceso de terminación
 * de una compra (Checkout)
 * @author YAGE (hernan)
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_checkout_step")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="checkout_step",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)								
public class CheckoutStep implements Serializable {

    private static final long serialVersionUID = 3718816163572141499L;

    private Long code;
	
	private String description ;
	
	private String icon;
	
	private String name;
	
	private Long nextStepCode;
	
	private Integer orderIndex;
	
	private String thumbnail;
	
	private String thumbnailHover;
	
	private String enName;
	
	private String enDescription ;
	
	/**
	 * 
	 */
	public CheckoutStep() {
		super();
		this.code = null;
		this.description = null;
		this.icon = null;
		this.name = null;
		this.nextStepCode = null;
		this.orderIndex = null;
		this.thumbnail = null;
		this.thumbnailHover = null;
		this.enName = null;
		this.enDescription = null;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="checkout_step_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
	

	
	@Column(name="checkout_step_en_description")
	public String getEnDescription() {
		return enDescription;
	}

	@Column(name="checkout_step_en_name")
	public String getEnName() {
		return enName;
	}


	/**
	 * @return Returns the description.
	 */
    @Column(name="checkout_step_description")
	public String getDescription() {
		return description;
	}


	/**
	 * @return Returns the icon.
	 */
    @Column(name="checkout_step_icon")
	public String getIcon() {
		return icon;
	}


	/**
	 * @return Returns the name.
	 */
    @Column(name="checkout_step_name")
	public String getName() {
		return name;
	}


	/**
	 * @return Returns the nextStepCode.
	 */
    @Column(name="checkout_step_next")
	public Long getNextStepCode() {
		return nextStepCode;
	}


	/**
	 * @return Returns the orderIndex.
	 */
    @Column(name="checkout_step_order")
	public Integer getOrderIndex() {
		return orderIndex;
	}


	/**
	 * @return Returns the thumbnail.
	 */
    @Column(name="checkout_step_thumbnail")
	public String getThumbnail() {
		return thumbnail;
	}
    
    
	/**
	 * @return Returns the thumbnailHover.
	 */
    @Column(name="checkout_step_thumbnail_rollover")
	public String getThumbnailHover() {
		return thumbnailHover;
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
	 * @param icon The icon to set.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}


	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @param nextStepCode The nextStepCode to set.
	 */
	public void setNextStepCode(Long nextStepCode) {
		this.nextStepCode = nextStepCode;
	}


	/**
	 * @param orderIndex The orderIndex to set.
	 */
	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}


	/**
	 * @param thumbnail The thumbnail to set.
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	/**
	 * @param thumbnailHover The thumbnailHover to set.
	 */
	public void setThumbnailHover(String thumbnailHover) {
		this.thumbnailHover = thumbnailHover;
	}


	public void setEnDescription(String enDescription) {
		this.enDescription = enDescription;
	}


	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	
	

}
