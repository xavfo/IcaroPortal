/*
 * Created 22/03/2007
 *	TaxDetail.java
 */
package com.iportal.cart.model.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.cart.model.tax.Tax;

/**
 * Detalle de valores pagados en impuestos en la presente orden de compras
 * @author YAGE(hernan)
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_tax_detail")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="tax_detail",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONE)								
public class TaxDetail {

	
	private Long code;
	
	private String name;
	
	private Tax tax;
	
	private Order order;
	
	private Double amount;
	
	private Double rate;
	
	
	
	public TaxDetail() {
		super();
		this.code = null;
		this.name = null;
		this.tax  = null;
		this.rate = null;
		this.order  = null;
		this.amount = null;
		
	}

	/**
	 * @return Returns the amount.
	 */
	@Column(name="taxt_detail_amount")
	public Double getAmount() {
		return amount;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="tax_detail_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
    
    

	/**
	 * @return Returns the name.
	 */
	@Column(name="tax_detail_name")
	public String getName() {
		return name;
	}

	/**
	 * @return Returns the order.
	 */
	@ManyToOne
	@JoinColumn(name="order_code")
	public Order getOrder() {
		return order;
	}

	/**
	 * @return Returns the rate.
	 */
	@Column(name="tax_detail_rate")
	public Double getRate() {
		return rate;
	}

	/**
	 * @return Returns the tax.
	 */
	@ManyToOne
	@JoinColumn(name="tax_code")
	public Tax getTax() {
		return tax;
	}

	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param order The order to set.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @param rate The rate to set.
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}

	/**
	 * @param tax The tax to set.
	 */
	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
	

}
