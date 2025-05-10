/*
 * Created 16/04/2007
 *	AccessItemsList.java
 */
package com.iportal.biz.portal.bean;

import java.util.List;

/**
 * Elemento descendiente de ArrayList con una propiedad
 * para el título de la lista
 * 
 * @author YAGE(hernan)
 * @version 1.0
 *
 */
public class AccessItemsList {
	
	
	
	private String listTitle;
	
	private List results;

	
	
	/**
	 * 
	 */
	public AccessItemsList() {
		super();
		this.clear();
	}
	
	

	/**
	 * @param results
	 */
	public AccessItemsList(List results) {
		super();
		this.clear();
		this.results = results;
	}



	/**
	 * @param listTitle
	 * @param results
	 */
	public AccessItemsList(String listTitle, List results) {
		super();
		this.clear();
		this.listTitle = listTitle;
		this.results = results;
	}
	
	private void clear () {
		this.listTitle = null;
		this.results   = null;
		
	}



	/**
	 * @return Returns the listTitle.
	 */
	public String getListTitle() {
		return listTitle;
	}



	/**
	 * @return Returns the results.
	 */
	public List getResults() {
		return results;
	}



	/**
	 * @param listTitle The listTitle to set.
	 */
	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}



	/**
	 * @param results The results to set.
	 */
	public void setResults(List results) {
		this.results = results;
	}
	
	



}
