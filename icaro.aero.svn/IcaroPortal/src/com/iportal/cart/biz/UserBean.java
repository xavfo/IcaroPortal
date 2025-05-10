/**
 * 
 */
package com.iportal.cart.biz;

import aero.icaro.service.IcaroUserBean;

import com.iportal.Constants;
import com.iportal.cart.model.customer.Customer;
import com.yage.Globals;

/**
 * @author hernan
 *
 */
public class UserBean {
	
	private IcaroUserBean icaroUserBean;
	
	private Customer customer;
	
	private Long customerType;
	
	private String name;
	
	private Boolean applyCommission;

	public UserBean() {
		super();
		this.applyCommission = null;
		this.icaroUserBean = null;
		this.customer = null;
		this.customerType = null;
		this.name = null;
	}
	
	public UserBean(Customer customer, Long customerType, String name){
		this.customer=customer;
		this.customerType=customerType;
		this.name=name;
	}
	
	public UserBean(IcaroUserBean icaroUserBean, Long customerType, String name){
		this.icaroUserBean=icaroUserBean;
		this.customerType=customerType;
		this.name=name;
	}

	public Boolean getApplyCommission() {
		if (this.customerType == null) {
			this.applyCommission =  null;
		} else {
			if (this.applyCommission == null){
				if (Constants.CLIENT_AGENCY_KEY.equals(this.customerType)  ) {
					this.applyCommission = Globals.TRUE;
				} else {
					this.applyCommission = Globals.FALSE;
				}
			}
		}
		return this.applyCommission;
	}

	public String getFullName() {
		
		return this.customer!= null? this.customer.getFullName():this.name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Long getCustomerType() {
		return customerType;
	}

	public IcaroUserBean getIcaroUserBean() {
		return icaroUserBean;
	}

	public String getName() {
		return name;
	}

	public void setApplyCommission(Boolean applyCommission) {
		this.applyCommission = applyCommission;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomerType(Long customerType) {
		this.customerType = customerType;
	}

	public void setIcaroUserBean(IcaroUserBean icaroUserBean) {
		this.icaroUserBean = icaroUserBean;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	


}
