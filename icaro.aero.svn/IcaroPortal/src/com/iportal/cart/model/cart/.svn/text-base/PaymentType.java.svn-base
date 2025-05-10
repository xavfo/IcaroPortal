/*
 * Created Jan 9, 2007
 *	PaymentType.java
 */
package com.iportal.cart.model.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Posibles medios de pago mediante los cuales se puede 
 * cancelar por las compras efectuadas en el sitio de comercio electr�nico.
 * Cada tipo de pago puede tener su propio flujo de compra.
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_payment_type")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="payment_type",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class PaymentType {

	private Long code;
	
	private String name;
	
	private Boolean enabled;
	
	private String externalCode;
	
	private Float discount;
	
	private Boolean restricted;
	
	private String logo;
	
	
	
	//payment_type_restricted

	
	/**
	 * 
	 */
	public PaymentType() {
		super();
		this.code     = null;
		this.name     = null;
		this.enabled  = null;
		this.discount = null;
		this.logo     = null;
		this.externalCode = null;
		this.restricted = null;

	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="payment_type_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	

	public Long getCode() {
		return code;
	}


	/**
	 * Descuento asociado a toda la compra cuando se la efectua empleando
	 * este medio de pago.
	 * @return Returns the discount.
	 */
    @Column(name="payment_type_discount")
	public Float getDiscount() {
		return discount;
	}


	/**
	 * Indicador de si se encuentra activo o no el presente medio de pago.
	 * TRUE => activo
	 * FASE => inactivo
	 * @return Returns the enabled.
	 */
    @Column(name="payment_type_enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * C�digo indetificador externo del medio de pago.
	 * @return Returns the externalCode.
	 */
    @Column(name="payment_type_external_code")
	public String getExternalCode() {
		return externalCode;
	}
    
    


	/**
	 * @return Returns the logo.
	 */
    @Column(name="payment_type_logo")
	public String getLogo() {
		return logo;
	}


	/**
	 * @return Returns the name.
	 */
    @Column(name="payment_type_name")
	public String getName() {
		return name;
	}
    

	/**
	 * @return Returns the restricted.
	 */
    @Column(name="payment_type_restricted")
	public Boolean getRestricted() {
		return restricted;
	}



	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}


	/**
	 * Descuento asociado a toda la compra cuando se la efectua empleando
	 * este medio de pago.
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
	

}
