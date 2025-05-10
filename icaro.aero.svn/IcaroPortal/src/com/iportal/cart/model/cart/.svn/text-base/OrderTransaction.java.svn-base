/*
 * Created on Nov 11, 2008
 * 
 */
package com.iportal.cart.model.cart;

import java.util.Calendar;

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

/**
 * Detalle de transacciones o solicitudes envidas
 * a la pasarela de pagos
 * @author Hernan Leon
 *
 */
@Entity
@Table(name="tb_order_transaction")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="order_transaction",
								allocationSize=1
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class OrderTransaction {

	private Long code;
	
	private OrderStatus status;
	
	private Order order;

	private Calendar creation;
	
	private Calendar lastUpdate;
	
	private void clear () {
	    this.code = null;
	    this.status = null;
	    this.order = null;
	    this.creation = null;
	    this.lastUpdate = null;
	}
	
	

	/**
	 * 
	 */
	public OrderTransaction() {
	    super();
	    this.clear();
	}



	/**
	 * @param code
	 * @param status
	 * @param order
	 * @param creation
	 */
	public OrderTransaction( OrderStatus status, Order order,
		Calendar creation) {
	    super();
	    this.clear();
	    this.status = status;
	    this.order = order;
	    this.creation = creation;
	    this.lastUpdate = creation;
	}

	public OrderTransaction( OrderStatus status, 
		Calendar creation) {
	    super();
	    this.clear();
	    this.status = status;
	    this.creation = creation;
	    this.lastUpdate = creation;
	}

	/**
	 * @return the code
	 */
	@Id 
	@Column(name="order_transaction_code")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
	    return code;
	}

	/**
	 * @return the status
	 */
	@ManyToOne
	@JoinColumn(name="order_status_code")
	public OrderStatus getStatus() {
	    return status;
	}

	/**
	 * @return the order
	 */
	@ManyToOne
	@JoinColumn(name="order_code")
	public Order getOrder() {
	    return order;
	}

	/**
	 * @return the creation
	 */
	@Column(name="order_transaction_creation")
	public Calendar getCreation() {
	    return creation;
	}

	/**
	 * @return the lastUpdate
	 */
	@Column(name="order_transaction_last_update")
	public Calendar getLastUpdate() {
	    return lastUpdate;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
	    this.code = code;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(OrderStatus status) {
	    this.status = status;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
	    this.order = order;
	}

	/**
	 * @param creation the creation to set
	 */
	public void setCreation(Calendar creation) {
	    this.creation = creation;
	}

	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Calendar lastUpdate) {
	    this.lastUpdate = lastUpdate;
	}
	
	
	
	

}
