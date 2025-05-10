/*
 * Created 14/02/2007
 *	CustomerForm.java
 */
package com.iportal.cart.ctrl.system.cart;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.iportal.Constants;
import com.iportal.cart.ctrl.AddressForm;
import com.yage.Globals;
import com.yage.commons.DateFormatUtils;


/**
 * @author hernan
 * @FIXME cuando se haga el registro de usuarios debe aumentarse los campos para
 *        manejo de informacion de factura y direccion de domicilio
 * 
 * 
 */
public class CustomerForm extends AddressForm {

    private static final long serialVersionUID = -2023164086572082333L;

      
    private String email;

    private Boolean enabled;

    private Integer enabledOption;

    private String firstName;

    private String lastName;
    
    private String userName;

    private String password;

    //private String nameAgent;

    //private String passwordAgent;
    
    //private Boolean agentLogin;

    private String passwordVerify;

    private Boolean gender;

    private Integer genderOption;

    private Boolean resetPassword;

    private Boolean suscribeBulletin;

    private Integer suscribeBulletinOption;

    private String birthdateDate;

    private String identity;

    private Integer idTypeCode;

    private String message;

    private String officePhone;

    private String countryCode;
    
    private String orderFrom;
    
    private String orderUntil;
    
    private String address;
    
    private String nextForward;

    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.email = null;
        this.enabled = Globals.TRUE;
    //    this.nameAgent = null;
        this.firstName = null;
        this.lastName = null;
        this.userName=null;
        this.password = null;
      //  this.passwordAgent = null;
        this.passwordVerify = null;
        this.gender = Globals.TRUE;
        //this.agentLogin = Globals.FALSE;
        this.resetPassword = Globals.FALSE;
        this.suscribeBulletin = Globals.FALSE;
        this.birthdateDate = null;
        this.idTypeCode = null;
        this.identity = null;
        this.officePhone = null;
        this.countryCode = null;
        this.enabledOption = null;
        this.genderOption = null;
        this.suscribeBulletinOption = null;
        this.message = null;
        this.address=null;
        this.nextForward=null;
        
        Calendar gc = GregorianCalendar.getInstance();
        setOrderUntilDate(gc);
        gc.set(GregorianCalendar.DAY_OF_YEAR, 1);
        setOrderFromDate(gc);
    }


    public Date getBirthdate() {

        Date today = new Date();

        if (this.birthdateDate != null && this.birthdateDate.length() > 0) {
            today = DateFormatUtils.parseToDate(this.birthdateDate,
                    Constants.DATE_FORMAT);

            return today;
        }
        return null;

    }


    /**
     * @return Returns the birthdateDate.
     */
    public String getBirthdateDate() {
        return birthdateDate;
    }


    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }


    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }


    /**
     * @return Returns the enabled.
     */
    public Boolean getEnabled() {
        return enabled;
    }


    /**
     * @return Returns the enabledOption.
     */
    public Integer getEnabledOption() {
        return enabledOption;
    }


    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }


    /**
     * @return Returns the gender.
     */
    public Boolean getGender() {
        return gender;
    }


    /**
     * @return Returns the genderOption.
     */
    public Integer getGenderOption() {
        return genderOption;
    }


    /**
     * @return Returns the identity.
     */
    public String getIdentity() {
        return identity;
    }


    /**
     * @return the idTypeCode
     */
    public Integer getIdTypeCode() {
        return idTypeCode;
    }


    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }


    /**
     * @return Returns the officePhone.
     */
    public String getOfficePhone() {
        return officePhone;
    }


    /*/**
     * @return the nameAgent
     */
    /*public String getNameAgent() {
        return nameAgent;
    }*/


    /**
     * @return Returns the name.
     */
    public String getLoginEmail() {
        return userName;
    }


    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }


   /* /**
     * @return the passwordAgent
     */
    /*public String getPasswordAgent() {
        return passwordAgent;
    }*/


    /**
     * @return Returns the password.
     */
    public String getPasswordLogin() {
        return password;
    }


    /**
     * @return Returns the password.
     */
    public String getPasswordRegister() {
        return password;
    }


    /**
     * @return Returns the passwordVerify.
     */
    public String getPasswordVerify() {
        return passwordVerify;
    }


    /**
     * @return Returns the resetPassword.
     */
    public Boolean getResetPassword() {
        return resetPassword;
    }


    /**
     * @return Returns the suscribeBulletin.
     */
    public Boolean getSuscribeBulletin() {
        return suscribeBulletin;
    }


    /**
     * @return Returns the suscribeBulletinOption.
     */
    public Integer getSuscribeBulletinOption() {
        return suscribeBulletinOption;
    }


    public void setBirthdate(Date today) {
        if (today != null) {
            this.birthdateDate = DateFormatUtils.format(today,
                    Constants.DATE_FORMAT);
        }
    }


    /**
     * @param birthdateDate
     *            The birthdateDate to set.
     */
    public void setBirthdateDate(String birthdateDate) {
        this.birthdateDate = birthdateDate;
    }


    /**
     * @param countryCode
     *            the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @param enabled
     *            The enabled to set.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    /**
     * @param enabledOption
     *            The enabledOption to set.
     */
    public void setEnabledOption(Integer enabledOption) {
        this.enabledOption = enabledOption;
    }


    /**
     * @param firstName
     *            The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * @param gender
     *            The gender to set.
     */
    public void setGender(Boolean gender) {    	
        this.gender = gender;
    }


    /**
     * @param genderOption
     *            The genderOption to set.
     */
    public void setGenderOption(Integer genderOption) {
        this.genderOption = genderOption;
    }


    /**
     * @param identity
     *            The identity to set.
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }


    /**
     * @param idTypeCode
     *            the idTypeCode to set
     */
    public void setIdTypeCode(Integer idTypeCode) {
        this.idTypeCode = idTypeCode;
    }


    /**
     * @param lastName
     *            The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @param message
     *            The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @param officePhone
     *            The officePhone to set.
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }


    /*/**
     * @param nameAgent the nameAgent to set
     */
    /*public void setNameAgent(String nameAgent) {
        this.nameAgent = nameAgent;
    }*/


    /**
     * @param name
     *            The name to set.
     */
    public void setLoginEmail(String userName) {
        this.userName = userName;
    }


    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /*/**
     * @param passwordAgent the passwordAgent to set
     */
    /*public void setPasswordAgent(String passwordAgent) {
        this.passwordAgent = passwordAgent;
    }*/


    /**
     * @param password
     *            The password to set.
     */
    public void setPasswordLogin(String password) {
        this.password = password;
    }


    /**
     * @param password
     *            The password to set.
     */
    public void setPasswordRegister(String password) {
        this.password = password;
    }


    /**
     * @param passwordVerify
     *            The passwordVerify to set.
     */
    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }


    /**
     * @param resetPassword
     *            The resetPassword to set.
     */
    public void setResetPassword(Boolean resetPassword) {
        this.resetPassword = resetPassword;
    }


    /**
     * @param suscribeBulletin
     *            The suscribeBulletin to set.
     */
    public void setSuscribeBulletin(Boolean suscibeBulletin) {
        this.suscribeBulletin = suscibeBulletin;
    }


    /**
     * @param suscribeBulletinOption
     *            The suscribeBulletinOption to set.
     */
    public void setSuscribeBulletinOption(Integer suscibeBulletinOption) {
        this.suscribeBulletinOption = suscibeBulletinOption;
    }


    /*/**
     * @return the agentLogin
     */
    /*public Boolean getAgentLogin() {
        return agentLogin;
    }*/


    /*/**
     * @param agentLogin the agentLogin to set
     */
    /*public void setAgentLogin(Boolean agentLogin) {
        this.agentLogin = agentLogin;
    }*/

    
    public Calendar getOrderFromDate() {
        Calendar today = GregorianCalendar.getInstance();

        if (this.orderFrom != null && this.orderFrom.length() > 0) {
            today.setTime(DateFormatUtils.parseToDate(this.orderFrom,
                    Constants.DATE_FORMAT));
            return today;
        }
        return null;

    }


    public void setOrderFromDate(Calendar today) {
        if (today != null) {
            this.orderFrom = DateFormatUtils.format(today.getTime(),
                    Constants.DATE_FORMAT);
        }
    }

    
	/**
	 * @return the orderFrom
	 */
	public String getOrderFrom() {
		return orderFrom;
	}


	/**
	 * @param orderFrom the orderFrom to set
	 */
	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}


    public Calendar getOrderUntilDate() {
        Calendar today = GregorianCalendar.getInstance();

        if (this.orderUntil != null && this.orderUntil.length() > 0) {
            today.setTime(DateFormatUtils.parseToDate(this.orderUntil,
                    Constants.DATE_FORMAT));
            today.set(GregorianCalendar.HOUR_OF_DAY, 23);
            today.set(GregorianCalendar.MINUTE, 59);
            today.set(GregorianCalendar.SECOND, 59);
            today.set(GregorianCalendar.MILLISECOND, 999);
            return today;
        }
        return null;

    }

    public void setOrderUntilDate(Calendar today) {
        if (today != null) {
            this.orderUntil = DateFormatUtils.format(today.getTime(),
                    Constants.DATE_FORMAT);
        }
    }


	/**
	 * @return the orderUntil
	 */
	public String getOrderUntil() {
		return orderUntil;
	}


	/**
	 * @param orderUntil the orderUntil to set
	 */
	public void setOrderUntil(String orderUntil) {
		this.orderUntil = orderUntil;
	}


	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return Returns the address.
	 */
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
	 * @return Returns the nextForward.
	 */
	public String getNextForward() {
		return nextForward;
	}


	/**
	 * @param nextForward The nextForward to set.
	 */
	public void setNextForward(String nextForward) {
		this.nextForward = nextForward;
	}

}
