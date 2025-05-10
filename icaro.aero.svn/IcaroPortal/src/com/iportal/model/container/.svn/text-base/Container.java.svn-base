/*
 * Created Jul 11, 2006
 *	Container.java
 */
package com.iportal.model.container;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author hernan
 *
 */
@MappedSuperclass
public abstract class Container {
	

	protected Long code;
	
	protected String title;
	
	protected Boolean isEnabled;

	/**
	 * 
	 */
	public Container() {
		super();
		this.clear();
	}
	
	protected void clear () {
		this.code = null;
		this.title = null;
		this.isEnabled = null;
		
	}

	/**
	 * @return Returns the code.
	 */
	@Transient 
	public Long getCode() {
		return code;
	}

	/**
	 * @return Returns the isEnabled.
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * @return Returns the name.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @param isEnabled The isEnabled to set.
	 */
	public void setIsEnabled(Boolean enabled) {
		this.isEnabled = enabled;
	}

	/**
	 * @param name The name to set.
	 */
	public void setTitle(String name) {
		this.title = name;
	}

	
}
