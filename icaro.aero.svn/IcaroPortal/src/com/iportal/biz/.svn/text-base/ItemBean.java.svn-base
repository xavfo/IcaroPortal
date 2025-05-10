package com.iportal.biz;

import java.util.Locale;

import org.apache.struts.util.MessageResources;

import com.iportal.Constants;


/**
 * La clase CalendarBean permite para crear mes calendario por mes. 
 * @author YAGE
 * @version 1.0 
 * */
public class ItemBean {
	
	private Long code;
	
	private String name;
	
	private String suffix;
	
	public ItemBean() {
		super();
	}
	
	
	public ItemBean(Long code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	public ItemBean(Long code, String name, String suffix) {
		super();
		this.code = code;
		this.name = name;
		this.suffix = suffix;
	}
	
	/**
	 * @return Returns the code.
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(Long code) {
		this.code = code;
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
	 * @return Returns the suffix.
	 */
	public String getSuffix() {
		return suffix;
	}
	
	/**
	 * @param suffix The suffix to set.
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getKey(){
		Locale locale = new Locale(this.suffix);
		return MessageResources.getMessageResources(Constants.DATABASE_BUNDLE).getMessage(locale, this.name);
	}
}