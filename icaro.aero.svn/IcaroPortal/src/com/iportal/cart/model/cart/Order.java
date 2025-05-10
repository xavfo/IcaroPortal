/*
 * Created Jan 9, 2007
 *	Order.java
 */
package com.iportal.cart.model.cart;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.cart.model.customer.BillInfo;
import com.iportal.cart.model.customer.Customer;
import com.iportal.cart.model.customer.OrderCustomerType;



/**
 * Registro de pedidos en l�nea y ordenes de compra con su respectiva
 * informaci�n y estado de pago.
 * 
 * @author hernan
 * @version 1.2 hernan
 *
 */
@Entity
@Table(name="tb_order")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="order",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Order {

	
	private Long code;
	
	private Long lastTransactionCode;
	
	private OrderStatus status;
	
	private Customer customer;
	
	private OrderCustomerType customerType;
	
	private String customerReference;
	
	private Payment payment;
	
	private Calendar creation;
	
	private Calendar lastStatus;
	
	private Calendar effectiveTo;
	
	private Boolean enabled;
	
	private Boolean sale;
	
	private Double beforeDiscount;
	
	private Double discount;
	
	private Double ivaAmount;
	
	private Double taxATAmount;

	private Double taxWTAmount;
	
	private Double tax3Amount;
	
	private Double totalAmount;
	
	private String recordLocator;
	
	private BillInfo billInfo;
	
	private Integer legCount;
	
	private String ticketNumbers;
	
	private String ticketDate; //order_eticket_date
	
	private Long languageCode;
	
	private Double  ivaPercentage;
	
	//private Set<TaxDetail> taxDetails;
	
	private List<Itinerary> itineraries;
	
	private List<Passenger> passengers;
	
	private List<OrderTransaction> orderTransactions;
	
	//private transient List<Itinerary> orderedItineraries;
	
//	private transient List<Passenger> orderedPassengers;

	/**
	 * 
	 */
	public Order() {
		super();
		this.code  = null;
		this.status  = null;
		this.customer  = null;
		this.customerType=null;
		this.customerReference=null;
		this.payment  = null;
		this.creation  = null;
		this.lastStatus  = null;
		this.effectiveTo=null;
		this.enabled  = null;
		this.sale = null;
		this.beforeDiscount  = null;
		this.discount  = null;
		this.totalAmount  = null;		
		//this.taxDetails = null;
		this.billInfo = null;

		this.ivaAmount   = null;
		this.taxATAmount = null;
		this.taxWTAmount = null;
		this.tax3Amount  = null;
		this.totalAmount = null;
		this.recordLocator = null;
		this.legCount = null;
		this.ticketNumbers= null;
		this.ticketDate = null;
		this.languageCode = null;
		this.ivaPercentage = null;
		this.itineraries = new ArrayList<Itinerary> ();
		this.passengers  = new ArrayList<Passenger> ();
		
		this.orderTransactions = new ArrayList<OrderTransaction> ();
		this.lastTransactionCode = null;
		//this.orderedItineraries = new ArrayList<Itinerary> ();
		
//		this.orderedPassengers = new ArrayList<Passenger> ();

	}
	
	

	/*	@Transient
	public List<Itinerary> getOrderedItineraries() {
		if((this.orderedItineraries == null || this.orderedItineraries.size() <1) && this.itineraries.size() > 1){
			this.orderedItineraries.addAll(this.itineraries);
		}

		return this.orderedItineraries;
	}*/


/*
	@Transient
	public List<Passenger> getOrderedPassengers() {
		if((this.orderedPassengers == null || this.orderedPassengers.size() <1) && this.passengers.size() > 1){
			this.orderedPassengers.addAll(this.passengers);
		}
		return this.orderedPassengers;
	}*/



	/*public void setOrderedItineraries(List<Itinerary> orderedItineraries) {
		this.orderedItineraries = orderedItineraries;
	}*/



	/*public void setOrderedPassengers(List<Passenger> orderedPassengers) {
		this.orderedPassengers = orderedPassengers;
	}*/



	public void addItinerary (Itinerary it) {
		
		if (it == null) {
			throw new IllegalArgumentException ("...Itinerary cannot be null!!");
		}
		if (it.getOrder() != null  && it.getOrder().getItineraries()!= null) {
			it.getOrder().getItineraries().remove(it);
			//it.getOrder().getOrderedItineraries().remove(it);
		}
		it.setOrder(this);
		
		
		
		this.itineraries.add(it);
		//this.orderedItineraries.add(it);
	}
	
	public void addOrderTransacion (OrderTransaction ot) {
		
		if (ot == null) {
			throw new IllegalArgumentException ("...Itinerary cannot be null!!");
		}
		if (ot.getOrder() != null  && ot.getOrder().getOrderTransactions()!= null) {
		    ot.getOrder().getOrderTransactions().remove(ot);
			//it.getOrder().getOrderedItineraries().remove(it);
		}
		ot.setOrder(this);
		
		
		
		this.orderTransactions.add(ot);
		//this.orderedItineraries.add(it);
	}

	public void addPassengers (Passenger passenger) {
		
		if (passenger == null) {
			throw new IllegalArgumentException ("...Passenger cannot be null!!");
		}
		if (passenger.getOrder() != null  && passenger.getOrder().getPassengers()!= null) {
			passenger.getOrder().getPassengers().remove(passenger);
			//passenger.getOrder().getOrderedPassengers().remove(passenger);
		}
		passenger.setOrder(this);
		
		this.passengers.add(passenger);
//		this.orderedPassengers.add(passenger);
	}


	/**
	 * @return Returns the beforeDiscount.
	 */
	@Column(name="order_before_discount")
	public Double getBeforeDiscount() {
		return beforeDiscount;
	}
	
	

	/**
	 * @return
	 */

	@Embedded
	@AttributeOverrides( {
		@AttributeOverride(name = "name", column = @Column(name="ORDER_BILL_TO") ),
		@AttributeOverride(name = "identity", column = @Column(name="ORDER_BILL_ID") ),
		@AttributeOverride(name = "address", column = @Column(name="ORDER_BILL_ADDRESS") ),
		@AttributeOverride(name = "phone", column = @Column(name="ORDER_BILL_PHONE") )
		})
	public BillInfo getBillInfo() {
		return billInfo;
	}

	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="order_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}
    
    



	/**
	 * @return Returns the creation.
	 */
    @Column(name="order_creation")
	public Calendar getCreation() {
		return creation;
	}

	/**
	 * @return Returns the customer.
	 */
	@ManyToOne
	@JoinColumn(name="customer_code")
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return Returns the customerType.
	 */
	@ManyToOne
	@JoinColumn(name="order_customer_type_code")
	public OrderCustomerType getCustomerType() {
		return customerType;
	}
	
	/**
	 * @return Returns the customerRefernece.
	 */
	@Column(name="order_customer_reference")
	public String getCustomerReference() {
		return customerReference;
	}
	

	/**
	 * @return Returns the discount.
	 */
	@Column(name="order_discount_amount")
	public Double getDiscount() {
		return discount;
	}
	
	@Column(name="order_effective_to")
	/**
	 * @return Returns the effectiveTo.
	 */
	public Calendar getEffectiveTo() {
		return effectiveTo;
	}

	/**
	 * @return Returns the enabled.
	 */
	@Column(name="order_enabled")
	public Boolean getEnabled() {
		return enabled;
	}


	/**
	 * @return Returns the lastStatus.
	 */
	@Column(name="order_last_status")
	public Calendar getLastStatus() {
		return lastStatus;
	}
	
	
	@Column(name="order_leg_count")
	public Integer getLegCount() {
		return legCount;
	}
	

	/**
	 * @return Returns the payment.
	 */
	@Embedded
	public Payment getPayment() {
		return payment;
	}

	
	@Column(name="order_is_sale")
	public Boolean getSale() {
		return sale;
	}


	/**
	 * @return Returns the status.
	 */
	@ManyToOne
	@JoinColumn(name="order_status_code")
	public OrderStatus getStatus() {
		return status;
	}
	
	

	/**
	 * @return Returns the taxDetails.
	 */
	/*
	@   
	OneToMany( mappedBy="order" )
	@ 
	org.hibernate.annotations.OrderBy(clause="tax_detail_name asc")
	public Set<TaxDetail> getTaxDetails() {
		return taxDetails;
	}*/

	
	@Column(name="order_eticket_date")
	public String getTicketDate() {
		return ticketDate;
	}

	
	@Column(name="order_eticket_numbers")
	public String getTicketNumbers() {
		return ticketNumbers;
	}
	

	/**
	 * @return Returns the totalAmount.
	 */
	@Column(name="order_total_amount")
	public Double getTotalAmount() {
		return totalAmount;
	}
	
	@Column(name="order_iva_amount")
	public Double getIvaAmount() {
		return ivaAmount;
	}

	@Column(name="order_record_locator")
	public String getRecordLocator() {
		return recordLocator;
	}

	@Column(name="order_tax3_amount")
	public Double getTax3Amount() {
		return tax3Amount;
	}

	@Column(name="order_tax_at_amount")
	public Double getTaxATAmount() {
		return taxATAmount;
	}

	@Column(name="order_tax_wt_amount")
	public Double getTaxWTAmount() {
		return taxWTAmount;
	}
	
	

	@OneToMany( mappedBy="order" )
	@org.hibernate.annotations.OrderBy(clause="itinerary_code asc")
	public List<Itinerary> getItineraries() {
		return itineraries;
	}

	@OneToMany( mappedBy="order" )
	@org.hibernate.annotations.OrderBy(clause="passenger_code asc")
	public List<Passenger> getPassengers() {
		return passengers;
	}
	
	/**
	 * @return the orderTransactions
	 */
	@OneToMany( mappedBy="order" )
	@org.hibernate.annotations.OrderBy(clause="order_transaction_code asc")
	public List<OrderTransaction> getOrderTransactions() {
	    return orderTransactions;
	}



	@Column(name="order_iva_percentage")
	public Double getIvaPercentage() {
		return ivaPercentage;
	}



	@Column(name="order_language_code")
	public Long getLanguageCode() {
		return languageCode;
	}
	
	

		
	/**
	 * @return the lastTransactionCode
	 */
	@Column(name="order_last_transaction_code")
	public Long getLastTransactionCode() {
	    return lastTransactionCode;
	}



	/**
	 * @param lastTransactionCode the lastTransactionCode to set
	 */
	public void setLastTransactionCode(Long lastTransacionCode) {
	    this.lastTransactionCode = lastTransacionCode;
	}



	public void setIvaPercentage(Double order_iva_percentage) {
		this.ivaPercentage = order_iva_percentage;
	}



	public void setLanguageCode(Long order_language) {
		this.languageCode = order_language;
	}



	public void setItineraries(List<Itinerary> itineraries) {
		this.itineraries = itineraries;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setIvaAmount(Double ivaAmount) {
		this.ivaAmount = ivaAmount;
	}

	public void setRecordLocator(String recordLocator) {
		this.recordLocator = recordLocator;
	}

	public void setTax3Amount(Double tax3Amount) {
		this.tax3Amount = tax3Amount;
	}

	public void setTaxATAmount(Double taxATAmount) {
		this.taxATAmount = taxATAmount;
	}

	public void setTaxWTAmount(Double taxWTAmount) {
		this.taxWTAmount = taxWTAmount;
	}

	/**
	 * @param beforeDiscount The beforeDiscount to set.
	 */
	public void setBeforeDiscount(Double beforeDiscount) {
		this.beforeDiscount = beforeDiscount;
	}
	
	

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param creation The creation to set.
	 */
	public void setCreation(Calendar creation) {
		this.creation = creation;
	}

	/**
	 * @param customer The customer to set.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	/**
	 * @param discount The discount to set.
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	/**
	 * @param lastStatus The lastStatus to set.
	 */
	public void setLastStatus(Calendar lastStatus) {
		this.lastStatus = lastStatus;
	}
	

	public void setLegCount(Integer legCount) {
		this.legCount = legCount;
	}

	
	
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}



	public void setTicketNumbers(String order_eticket_numbers) {
		this.ticketNumbers = order_eticket_numbers;
	}


	/**
	 * @param payment The payment to set.
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setSale(Boolean sale) {
		this.sale = sale;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	

	/*/**
	 * @param taxDetails The taxDetails to set.
	 */
	/*public void setTaxDetails(Set<TaxDetail> taxDetails) {
		this.taxDetails = taxDetails;
	}*/

	/**
	 * @param totalAmount The totalAmount to set.
	 */
	public void setTotalAmount(Double total) {
		this.totalAmount = total;
	}


	/**
	 * @param effectiveTo The effectiveTo to set.
	 */
	public void setEffectiveTo(Calendar effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	/**
	 * @param customerReference The customerReference to set.
	 */
	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}
	
	/**
	 * @param customerType The customerType to set.
	 */
	public void setCustomerType(OrderCustomerType customerType) {
		this.customerType = customerType;
	}



	/**
	 * @param orderTransactions the orderTransactions to set
	 */
	public void setOrderTransactions(List<OrderTransaction> orderTransactions) {
	    this.orderTransactions = orderTransactions;
	}
	
	




}
