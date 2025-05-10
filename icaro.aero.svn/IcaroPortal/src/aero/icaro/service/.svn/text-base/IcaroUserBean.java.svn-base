/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

import java.math.BigInteger;

import aero.icaro.service.IcaroServiceMessageBean;

import com.yage.servlet.http.security.Inventory;

/**
 * @author ftamayo
 * @version 1.0
 *
 * Objeto tipo envoltura (wrapper) para objeto remoto
 * @see aero.icaro.service.skies.stub.IcaroUser
 *  
 */
public class IcaroUserBean extends IcaroServiceMessageBean {

	private String name;
  
    private BigInteger level;
    
    private String levelDescription; 
    
    private String cardNumber;
    
    private String password;
    
    private Long userType;
    
    private float discount;
    
    private transient Inventory inventory;
    
    
        
    public void clear () {
    	this.name=null;
    	this.level=null;
    	this.levelDescription=null;
    	this.cardNumber=null;
    	this.password=null; 
    	this.userType=null;
    	this.discount=0;
    }
	
	/**
	 * @return Returns the discount.
	 */
	public float getDiscount() {
		return discount;
	}

	/**
	 * @param discount The discount to set.
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public IcaroUserBean() {
		super();
		this.clear();
	}

    /**
	 * 
	 */
	public IcaroUserBean(Inventory inventory) {
		super();
		this.clear();
		this.inventory = inventory;
	}
	
	
	
	/**
	 * @param statusCode
	 * @param response
	 */
	public IcaroUserBean(BigInteger statusCode, String response) {
		super(statusCode, response);
		this.clear();
	}

	
	/* Une a la session de usuario a este objeto
     * @see javax.servlet.http.HttpSessionBindingListener#valueBound(javax.servlet.http.HttpSessionBindingEvent)
     */
/*    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (this.inventory != null) {
            this.inventory.add(httpSessionBindingEvent.getSession(), this);
        }
    }*/

    /* Quita o elimina este objeto que estaba unido a la sesion cuando esta muere o expira 
     * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound(javax.servlet.http.HttpSessionBindingEvent)
     */
    /*public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        if (this.inventory != null) {
            this.inventory.remove(httpSessionBindingEvent.getSession().getId());
        }
    }*/

	/**
	 * @return Returns the inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @param inventory The inventory to set.
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return Returns the level.
	 */
	public BigInteger getLevel() {
		return level;
	}

	/**
	 * @param level The level to set.
	 */
	public void setLevel(BigInteger level) {
		this.level = level;
	}

	/**
	 * @return Returns the levelDescription.
	 */
	public String getLevelDescription() {
		return levelDescription;
	}

	/**
	 * @param levelDescription The levelDescription to set.
	 */
	public void setLevelDescription(String levelDescription) {
		this.levelDescription = levelDescription;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the cardNumber.
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * @param cardNumber The cardNumber to set.
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the userType.
	 */
	public Long getUserType() {
		return userType;
	}

	/**
	 * @param userType The userType to set.
	 */
	public void setUserType(Long userType) {
		this.userType = userType;
	}
    
	
	
}
