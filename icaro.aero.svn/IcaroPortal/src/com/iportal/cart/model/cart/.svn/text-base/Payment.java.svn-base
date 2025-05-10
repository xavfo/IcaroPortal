/*
 * Created Jan 11, 2007
 *	Payment.java
 */
package com.iportal.cart.model.cart;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.yage.Globals;

/**
 * Datos del pago de una orden de pedido. Todos los datos de un pago se registran
 * en esta entidad.
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Embeddable
public class Payment {

	private PaymentStatus status;
	
	private PaymentType type;
	
	private Calendar lastStatus;
	
	private Calendar requestDate;
	
	private Calendar successDate;
	
	private Calendar rejectDate;
	
	private String extraInfo;
	
	private String requestSessionKey;
	
	private String responseSessionKey;
	
	private Calendar responseDate;
	
	private String authorizationCode;
	
	private String errorCode;
	
	private String resultMessage;
    
    private Boolean result;
	
    private Order order;
    
    private Long value;
	
	/**
	 * 
	 */
	public Payment() {
		super();
		this.status = null;
		this.type = null;
		this.lastStatus = null;
		this.requestDate = null;
		this.successDate = null;
		this.rejectDate = null;
		this.extraInfo = null;
		this.requestSessionKey = null;
		this.responseSessionKey = null;
		this.responseDate = null;
		this.authorizationCode = null;
		this.errorCode = null;
		this.resultMessage = null;
        this.result = Globals.FALSE;
        this.order = null;
        this.value = null;
	}


	/**
	 * @return Returns the authorizationCode.
	 */
	@Column(name="order_payment_response_authorization")
	public String getAuthorizationCode() {
		return authorizationCode;
	}


	/**
	 * @return Returns the errorCode.
	 */
	@Column(name="order_payment_response_error_code")
	public String getErrorCode() {
		return errorCode;
	}


	/**
	 * @return Returns the resultMessage.
	 */
	@Column(name="order_payment_response_message")
	public String getResultMessage() {
		return resultMessage;
	}


	/**
	 * @return Returns the extraInfo.
	 */
	@Column(name="order_payment_extra")
	public String getExtraInfo() {
		return extraInfo;
	}


	/**
	 * @return Returns the lastStatus.
	 */
	@Column(name="order_last_payment_status")
	public Calendar getLastStatus() {
		return lastStatus;
	}


    /**
     * @return the order
     */
    @OneToOne
    @JoinColumn(name="order_code")
    public Order getOrder() {
        return order;
    }


	/**
	 * @return Returns the rejectDate.
	 */
	@Column(name="order_payment_reject")
	public Calendar getRejectDate() {
		return rejectDate;
	}


	/**
	 * @return Returns the requestDate.
	 */
	@Column(name="order_payment_request_date")
	public Calendar getRequestDate() {
		return requestDate;
	}


	/**
	 * @return Returns the requestSessionKey.
	 */
	@Column(name="order_payment_request_session_key")
	public String getRequestSessionKey() {
		return requestSessionKey;
	}


	/**
	 * @return Returns the responseDate.
	 */
	@Column(name="order_payment_response_date")
	public Calendar getResponseDate() {
		return responseDate;
	}


	/**
	 * @return Returns the responseSessionKey.
	 */
	@Column(name="order_payment_response_session_key")
	public String getResponseSessionKey() {
		return responseSessionKey;
	}


	/**
	 * @return Returns the status.
	 */
	@ManyToOne
	@JoinColumn(name="payment_status_code")
	public PaymentStatus getStatus() {
		return status;
	}


	/**
	 * @return Returns the successDate.
	 */
	@Column(name="order_payment_success")
	public Calendar getSuccessDate() {
		return successDate;
	}


	/**
	 * @return Returns the type.
	 */
	@ManyToOne
	@JoinColumn(name="payment_type_code")
	public PaymentType getType() {
		return type;
	}


	/**
	 * @param authorizationCode The authorizationCode to set.
	 */
	public void setAuthorizationCode(String authorizationCode) {
		this.authorizationCode = authorizationCode;
	}


	/**
	 * @param errorCode The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	/**
	 * @param resultMessage The resultMessage to set.
	 */
	public void setResultMessage(String errorMessage) {
		this.resultMessage = errorMessage;
	}


	/**
	 * @param extraInfo The extraInfo to set.
	 */
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}


	/**
	 * @param lastStatus The lastStatus to set.
	 */
	public void setLastStatus(Calendar lastStatus) {
		this.lastStatus = lastStatus;
	}


    /**
     * @param order the order to set
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    
    
	/**
	 * @param rejectDate The rejectDate to set.
	 */
	public void setRejectDate(Calendar rejectDate) {
		this.rejectDate = rejectDate;
	}


	/**
	 * @param requestDate The requestDate to set.
	 */
	public void setRequestDate(Calendar requestDate) {
		this.requestDate = requestDate;
	}


	/**
	 * @param requestSessionKey The requestSessionKey to set.
	 */
	public void setRequestSessionKey(String requestDigitalSign) {
		this.requestSessionKey = requestDigitalSign;
	}


	/**
	 * @param responseDate The responseDate to set.
	 */
	public void setResponseDate(Calendar responseDate) {
		this.responseDate = responseDate;
	}


	/**
	 * @param responseSessionKey The responseSessionKey to set.
	 */
	public void setResponseSessionKey(String responseDigitalSign) {
		this.responseSessionKey = responseDigitalSign;
	}


	/**
	 * @param status The status to set.
	 */
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}


	/**
	 * @param successDate The successDate to set.
	 */
	public void setSuccessDate(Calendar successDate) {
		this.successDate = successDate;
	}


	/**
	 * @param type The type to set.
	 */
	public void setType(PaymentType type) {
		this.type = type;
	}


    /**
     * @return the result
     */
    @Transient
    public Boolean getResult() {
        return result;
    }


    /**
     * @param result the result to set
     */
    public void setResult(Boolean result) {
        this.result = result;
    }


    /**
     * @return the value
     */
    @Transient
    public Long getValue() {
        return value;
    }


    /**
     * @param value the value to set
     */
    public void setValue(Long value) {
        this.value = value;
    }	

}
