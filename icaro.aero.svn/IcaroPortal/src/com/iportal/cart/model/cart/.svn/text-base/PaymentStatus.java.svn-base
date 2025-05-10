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
 * Posibles estado del Pago de una orden de compra.
 * @author hernan
 * @version 1.2
 *
 */
@Entity
@Table(name="tb_payment_status")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="payment_status",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class PaymentStatus {
    
    public final static PaymentStatus PENDING = new PaymentStatus(new Long(1), "PENDIENTE", null, null);
    public final static PaymentStatus AUTHORIZED = new PaymentStatus(new Long(2), "EXITOSO", null, null);
    public final static PaymentStatus REJECTED = new PaymentStatus(new Long(3), "FALLADO", null, null);
    public final static PaymentStatus VOID = new PaymentStatus(new Long(4), "ANULADO / REVERSADO", null, null);
	
	private Long code;
	
	private String name;
	
	private String externalCode;
	
	private String message;

	/**
     * @param code
     * @param name
     * @param externalCode
     * @param message
     */
    private PaymentStatus(Long code, String name, String externalCode, String message) {
        super();
        this.code = code;
        this.name = name;
        this.externalCode = externalCode;
        this.message = message;
    }

    public PaymentStatus() {
		super();
		this.code     = null;
		this.name     = null;
		this.message  = null;
		this.externalCode = null;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="payment_status_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the externalCode.
	 */
    /**
     * @return
     */
    @Column(name="payment_status_external_code")
	public String getExternalCode() {
		return externalCode;
	}

	/**
	 * @return Returns the message.
	 */
    @Column(name="payment_status_message")
	public String getMessage() {
		return message;
	}

	/**
	 * @return Returns the name.
	 */
    @Column(name="payment_status_name")
	public String getName() {
		return name;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param externalCode The externalCode to set.
	 */
	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	

}
