/*
 * Created Jan 8, 2007
 *	Customer.java
 */
package com.iportal.cart.model.customer;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

/**
 * 
 * Datos de Clientes que pueden hacer pedidos en l�nea.
 * 
 * @author hernan
 * @version 1.0
 *
 */
@Entity
@Table(name="tb_customer")
@javax.persistence.TableGenerator(
								name="TABLE_GEN",
								table="tb_sequence",
								pkColumnName = "name",
								valueColumnName = "next_val",
								pkColumnValue="customer",
								allocationSize=20
								)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)								
public class Customer {
	

	private Long code;
	   
	private Calendar creation;
	
	private String email;
	
	private Boolean enabled;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String userName;
	
	private Boolean gender;
	
	private Boolean resetPassword;
	
	private Boolean suscribeBulletin;
	
	private Calendar lastModified;
	
	private Calendar lastLogin;
	
	private Date birthdate;
	
	private String identity;
	
	private BillInfo billInfo;
	
	private String officePhone;
	//private String mobile;
	
	private String message;
	
	private String address;
	
	private IDType idType;
	
	private HomeAddress homeAddress;
	
	//private Set<CustomerAddress> addresses;
	
	

	/**
	 * 
	 */
	public Customer() {
		super();
		this.homeAddress = null;
		this.address=null;
		this.code      = null;
		this.creation  = null;
		this.email     = null;
		this.enabled   = null;
		this.firstName = null;
		this.lastName  = null;
		this.password  = null;
		this.userName=null;
		this.gender    = null;
		this.resetPassword   = null;
		this.suscribeBulletin = null;
		this.lastModified    = null;
		this.lastLogin = null;
		this.birthdate = null;
		this.identity  = null;
		this.billInfo  = null;
		this.officePhone    = null;
		this.message   = null;
		this.idType    = null;
		
	}
	

	@Transient
	public String getFullName() {
		StringBuffer name = new StringBuffer ();
		if ( this.getFirstName() != null && this.getFirstName().length() > 0 ) {
			name.append(this.getFirstName());
			name.append(" ");
		}
		if ( this.getLastName() != null && this.getLastName().length() > 0 ) {
			name.append(this.getLastName());
		}
		return name.toString();
	}

	/*/**
	 * @return Returns the addresses.
	 */
	/*@OneToMany( mappedBy="customer" )
	public Set<CustomerAddress> getAddresses() {
		return addresses;
	}*/


	/**
	 * @return Returns the billInfo.
	 */
	@Embedded
	public BillInfo getBillInfo() {
		return billInfo;
	}


	/**
	 * @return Returns the birthdate.
	 */   
	@Column(name="customer_birthdate")
	public Date getBirthdate() {
		return birthdate;
	}


	/**
	 * @return Returns the code.
	 */
    @Id 
    @Column(name="customer_code")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")	
	public Long getCode() {
		return code;
	}



	/**
	 * @return Returns the creation.
	 */
    @Column(name="customer_creation")
    public Calendar getCreation() {
		return creation;
	}



	/**
	 * @return Returns the email.
	 */
    @Column(name="customer_email")
	public String getEmail() {
		return email;
	}



	/**
	 * @return Returns the enabled.
	 */
    @Column(name="customer_enabled")    
	public Boolean getEnabled() {
		return enabled;
	}



	/**
	 * @return Returns the firstName.
	 */
    @Column(name="customer_firstname")
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @return Returns the gender.
	 */
    @Column(name="customer_gender")
	public Boolean getGender() {
		return gender;
	}
    
	/**
	 * @return Returns the homeAddress.
	 */
	@Embedded
	public HomeAddress getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @return Returns the identity.
	 */
    @Column(name="customer_identity")
	public String getIdentity() {
		return identity;
	}
    

	/**
	 * @return Returns the idType.
	 */
	@ManyToOne
	@JoinColumn(name="id_type_code")
	public IDType getIdType() {
		return idType;
	}


	/**
	 * @return Returns the lastLogin.
	 */
    @Column(name="customer_last_login")
	public Calendar getLastLogin() {
		return lastLogin;
	}



	/**
	 * @return Returns the lastModified.
	 */
    @Column(name="customer_modified")
	public Calendar getLastModified() {
		return lastModified;
	}



	/**
	 * @return Returns the lastName.
	 */
    @Column(name="customer_lastname")
	public String getLastName() {
		return lastName;
	}
    

	/**
	 * @return Returns the message.
	 */
    @Column(name="customer_message")
	public String getMessage() {
		return message;
	}


	/**
	 * @return Returns the officePhone.
	 */
	@Column(name="customer_officePhone")
	public String getOfficePhone() {
		return officePhone;
	}

	/**
	 * @return Returns the password.
	 */
    @Column(name="customer_password")
	public String getPassword() {
		return password;
	}



	/**
	 * @return Returns the resetPassword.
	 */
    @Column(name="customer_password_reset")
	public Boolean getResetPassword() {
		return resetPassword;
	}



	/**
	 * @return Returns the suscribeBulletin.
	 */
	 @Column(name="customer_bulletin")
	public Boolean getSuscribeBulletin() {
		return suscribeBulletin;
	}

	 

	/*/**
	 * @param addresses The addresses to set.
	 */
	/*public void setAddresses(Set<CustomerAddress> addresses) {
		this.addresses = addresses;
	}*/


	

	/**
	 * @param billInfo The billInfo to set.
	 */
	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}



	/**
	 * @param birthdate The birthdate to set.
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @param enabled The enabled to set.
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @param gender The gender to set.
	 */
	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	/**
	 * @param homeAddress The homeAddress to set.
	 */
	public void setHomeAddress(HomeAddress homeAddress) {
		this.homeAddress = homeAddress;
	}


	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	/**
	 * @param idType The idType to set.
	 */
	public void setIdType(IDType idType) {
		this.idType = idType;
	}


	/**
	 * @param lastLogin The lastLogin to set.
	 */
	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}



	/**
	 * @param lastModified The lastModified to set.
	 */
	public void setLastModified(Calendar lastModified) {
		this.lastModified = lastModified;
	}



	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * @param officePhone The officePhone to set.
	 */
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}



	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}





	/**
	 * @param resetPassword The resetPassword to set.
	 */
	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}



	/**
	 * @param suscribeBulletin The suscribeBulletin to set.
	 */
	public void setSuscribeBulletin(Boolean suscibeBulletin) {
		this.suscribeBulletin = suscibeBulletin;
	}


	/**
	 * @return Returns the address.
	 */
	@Column(name="customer_address")
	public String getAddress() {
		return address;
	}


	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return Returns the userName.
	 */
	@Column(name="customer_userName")
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	

}
