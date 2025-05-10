/*
 * Created Mar 27, 2006
 *	EmailItem.java
 */
package com.iportal.biz;

/**
 * Objeto para enviar listas de emails con nombres
 * para envío de notificaciones
 * @author hernan
 *
 */
public class EmailItem {

	private String name;
	
	private String email;
	
	
	
	/**
	 * @param name
	 * @param email
	 */
	public EmailItem(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	/**
	 * Crea un nuevo bean
	 */
	public EmailItem() {
		super();
		this.clear();
	}
	
	public void clear (){
		this.name  = null;
		this.email = null;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
