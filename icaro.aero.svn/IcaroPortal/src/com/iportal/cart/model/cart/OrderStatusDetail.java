/*
 * Created 05/02/2007
 *	OrderStatusDetail.java
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

/**
 * Posibles estados de los pedidos.
 * 
 * @author hernan
 * @version 1.0
 */
@Entity
@Table(name="tb_order_status_detail")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="order_status_detail",
								allocationSize=20
								)
@javax.persistence.AttributeOverrides ( {
	@javax.persistence.AttributeOverride (name="creation", column = @Column( name="order_status_detail_creation" )),
	@javax.persistence.AttributeOverride (name="remoteAddress", column = @Column( name="order_status_detail_remote_address" )),
	@javax.persistence.AttributeOverride (name="remoteHost", column = @Column( name="order_status_detail_remote_host" ))
})
public class OrderStatusDetail extends StatusDetail {

	private Order order;
	
	private PaymentStatus paymentStatus;
	
	
	
	/**
	 * 
	 */
	public OrderStatusDetail() {
		super();
		this.clear();
	}
	
    protected void clear () {
    	super.clear();
    	this.order = null;
    	this.paymentStatus = null;
    }
    
    @Id
    @Column(name="order_status_detail_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")
   	public Long getCode() {
		return this.code;
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
	 * @return Returns the paymentStatus.
	 */
    @ManyToOne
    @JoinColumn(name="payment_status_code")
	 public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param order The order to set.
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @param paymentStatus The paymentStatus to set.
	 */
	public void setPaymentStatus(PaymentStatus status) {
		this.paymentStatus = status;
	}
    
    


}
