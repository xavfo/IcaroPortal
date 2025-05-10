/**
 * 
 */
package com.iportal.cart.model.cart;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.iportal.cart.model.customer.IDType;
import com.iportal.model.icaro.fare.PaxType;

/**
 * Datos de pasajeros	
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_passenger")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="passenger",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Passenger {
	
	
	private Long code;
	
	private Order order;
	
	private IDType idType;
	
	private PaxType paxType;
	
	private String firstName;
	
	private String lastName;
	
	private Boolean requireWheelchair;
	
	private String nationality;
	
	private String identity;
	
	private Date birthdate;
	
	private String phone;
	
	private String mobile;
	
	private String email;
	
	private String frequenTravelNumber;
	
	private String classType;
	
	private String ticketNumber;
	
	private Double fareAmount;
	
	private Double ivaAmount;
	
	private Double taxATAmount;

	private Double taxWTAmount;
	
	private Double tax3Amount;
	
	private Double totalAmount;

	/**
	 * Devuelve la edad que se necesita para el envio de informacion
	 * a wordspan
	 * @return
	 */
	@Transient
	public Integer getWordspanAge() {
		Integer years = this.getAgeInYears();
		if (years < 2) {
			return this.getAgeInMonths();
		} else {
			return years;
		}
		
	}
	@Transient
	public Integer getAgeInMonths(){
		Integer resp = null;
		if (this.birthdate != null) {
			Calendar age = Calendar.getInstance();
		    age.setTimeInMillis(Math.abs(this.birthdate.getTime()-System.currentTimeMillis()));
		   // System.out.println("Your are "+(age.get(Calendar.YEAR)-1970)+" years, "+age.get(Calendar.MONTH)+" month, "+age.get(Calendar.DAY_OF_MONTH)+" days "+(System.currentTimeMillis()-this.birthdate.getTime()<0 ?"Not Born Yet" :"Old")+".");
		    int years = age.get(Calendar.YEAR)-1970;
		    int months = age.get(Calendar.MONTH);
		    resp = new Integer (years*12+months);
		}
		return resp;
	}

	
	@Transient
	public Integer getAgeInYears(){
		Integer resp = null;
		if (this.birthdate != null) {
			// get todays date
			Calendar now = Calendar.getInstance();
			// get a calendar representing their birth date
			Calendar cal = Calendar.getInstance();
			cal.setTime(this.birthdate);
			
			// calculate age as the difference in years.
			int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
			
			// now for the tricky bit.
			// set the "birth" calendar to be this year
			// this now represents the date of the birthday THIS year.
			// if that date has not occurred yet, subtract one from age 
			cal.set(Calendar.YEAR, now.get(Calendar.YEAR));
			if (now.before(cal)){
				age = age - 1;			
			}		
			resp = new Integer(age);
		}
		return resp;
	}

	@Transient
	public String getFullName () {
		return this.getFullName(" "); 
	}
	
	@Transient
	public String getFullName (String separator) {
		StringBuffer name = new StringBuffer ();
		if ( this.getLastName() != null && this.getLastName().length() > 0 ) {
			name.append(this.getLastName());
			name.append(separator);
		}
		if ( this.getFirstName() != null && this.getFirstName().length() > 0 ) {
			name.append(this.getFirstName());
			
		}
		return name.toString();

	}
	
	
	@Column(name="passenger_birthdate")
	public Date getBirthdate() {
		return birthdate;
	}

	@Column(name="passenger_class_type")
	public String getClassType() {
		return classType;
	}

    @Id 
    @Column(name="passenger_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}

    @Column(name="passenger_email")
    public String getEmail() {
		return email;
	}

    @Column(name="passenger_first_name")
    public String getFirstName() {
		return firstName;
	}

    @Column(name="passenger_frequent_travel")
    public String getFrequenTravelNumber() {
		return frequenTravelNumber;
	}

    @Column(name="passenger_identity")
    public String getIdentity() {
		return identity;
	}

    @ManyToOne
    @JoinColumn(name="id_type_code")
    public IDType getIdType() {
		return idType;
	}

    @Column(name="passenger_last_name")
    public String getLastName() {
		return lastName;
	}

    @Column(name="passenger_mobile")
    public String getMobile() {
		return mobile;
	}

    @Column(name="passenger_nationality")
    public String getNationality() {
		return nationality;
	}

    @ManyToOne
    @JoinColumn(name="order_code")
    public Order getOrder() {
		return order;
	}

    @ManyToOne
    @JoinColumn(name="pax_type_code")
    public PaxType getPaxType() {
		return paxType;
	}

    @Column(name="passenger_phone")
    public String getPhone() {
		return phone;
	}

    @Column(name="passenger_wheelchair")
    public Boolean getRequireWheelchair() {
		return requireWheelchair;
	}

    @Column(name="passenger_ticket_number")
    public String getTicketNumber() {
		return ticketNumber;
	}
    
    
    @Column(name="passenger_fare_amount")
	public Double getFareAmount() {
		return fareAmount;
	}

    @Column(name="passenger_iva_amount")
    public Double getIvaAmount() {
		return ivaAmount;
	}

    @Column(name="passenger_tax_at_amount")
    public Double getTax3Amount() {
		return tax3Amount;
	}

    @Column(name="passenger_tax_wt_amount")
    public Double getTaxATAmount() {
		return taxATAmount;
	}

    @Column(name="passenger_tax3_amount")
    public Double getTaxWTAmount() {
		return taxWTAmount;
	}
    
    @Column(name="passenger_total_amount")
    public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setFareAmount(Double fareAmount) {
		this.fareAmount = fareAmount;
	}

	public void setIvaAmount(Double ivaAmount) {
		this.ivaAmount = ivaAmount;
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

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = (firstName != null)?firstName.toUpperCase():null;
	}

	public void setFrequenTravelNumber(String frequenTravelNumber) {
		this.frequenTravelNumber = frequenTravelNumber;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public void setIdType(IDType idType) {
		this.idType = idType;
	}

	public void setLastName(String lastName) {
		this.lastName = (lastName != null)?lastName.toUpperCase():null;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setPaxType(PaxType paxType) {
		this.paxType = paxType;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRequireWheelchair(Boolean requireWheelchair) {
		this.requireWheelchair = requireWheelchair;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	
	
	
	

}
