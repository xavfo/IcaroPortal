/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

import java.math.BigInteger;

import aero.icaro.service.IcaroServiceMessageBean;

/**
 * @author ftamayo
 * @version 1.0
 * 
 * Objeto tipo envoltura (wrapper) para objeto remoto
 * @see aero.icaro.service.skies.stub.MilleDetail
 * 
 */
public class MillesDetailBean extends IcaroServiceMessageBean {

	private String label;

	private Long milles;

	public void clear() {
		this.label = null;
		this.milles = null;
	}

	public MillesDetailBean() {
		super();
		this.clear();
	}


	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Returns the milles.
	 */
	public Long getMilles() {
		return milles;
	}

	/**
	 * @param milles The milles to set.
	 */
	public void setMilles(Long milles) {
		this.milles = milles;
	}

	/**
	 * @param statusCode
	 * @param response
	 */
	public MillesDetailBean(BigInteger statusCode, String response) {
		super(statusCode, response);
		this.clear();
	}


}
