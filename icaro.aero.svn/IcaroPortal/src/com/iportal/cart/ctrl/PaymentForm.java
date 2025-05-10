/**
 * Yagé 2007
 */
package com.iportal.cart.ctrl;

import com.iportal.cart.model.cart.Order;
import com.yage.struts.action.BaseForm;

/**
 *
 * @author burkhard
 * @verion 1.0
 * created on Apr 4, 2007
 *
 */
public class PaymentForm extends BaseForm {

    private static final long serialVersionUID = 1575949099915890409L;
    
    private Long paymentTypeCode;
    
    private Long orderCode;
    
    private Order order;

    /**
     * 
     */
    public PaymentForm() {
        super();
        this.paymentTypeCode = null;
        this.orderCode = null;
        this.order = null;
    }

    /**
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * @return the paymentTypeCode
     */
    public Long getPaymentTypeCode() {
        return paymentTypeCode;
    }

    /**
     * @param paymentTypeCode the paymentTypeCode to set
     */
    public void setPaymentTypeCode(Long paymentType) {
        this.paymentTypeCode = paymentType;
    }

	/**
	 * @return the orderCode
	 */
	public Long getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}
    
    
    
    

}

