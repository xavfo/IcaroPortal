/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

import java.math.BigInteger;


/**
 * @author ftamayo
 * @version 1.0
 * 
 * Objeto tipo envoltura (wrapper) para objeto remoto
 * @see aero.icaro.service.skies.stub.IcaroPaq
 * 
 */
public class IcaroPaqBean extends IcaroServiceMessageBean {

	private String idPaq;

	private String estadoPaq;
	
	private String estadoPaqDesc;

	public void clear() {
		this.idPaq = null;
		this.estadoPaq = null;
		this.estadoPaqDesc = null;
	}

	public IcaroPaqBean() {
		super();
		this.clear();
	}

	/**
	 * @param statusCode
	 * @param response
	 */
	public IcaroPaqBean(BigInteger statusCode, String response) {
		super(statusCode, response);
		this.clear();
	}

	/**
	 * @return Returns the estadoPaq.
	 */
	public String getEstadoPaq() {
		return estadoPaq;
	}

	/**
	 * @param estadoPaq The estadoPaq to set.
	 */
	public void setEstadoPaq(String estadoPaq) {
		this.estadoPaq = estadoPaq;
	}

	/**
	 * @return Returns the estadoPaqDesc.
	 */
	public String getEstadoPaqDesc() {
		return estadoPaqDesc;
	}

	/**
	 * @param estadoPaqDesc The estadoPaqDesc to set.
	 */
	public void setEstadoPaqDesc(String estadoPaqDesc) {
		this.estadoPaqDesc = estadoPaqDesc;
	}

	/**
	 * @return Returns the idPaq.
	 */
	public String getIdPaq() {
		return idPaq;
	}

	/**
	 * @param idPaq The idPaq to set.
	 */
	public void setIdPaq(String idPaq) {
		this.idPaq = idPaq;
	}

	
}
