/*
 * Created on Jun 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.ctrl.icaro;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.yage.struts.action.BaseForm;

/**
 * @author monica
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MessageForm extends BaseForm {

	
	/**
     * 
     */
    private static final long serialVersionUID = -8404616899894611738L;

    protected Long externalCode; // codigo en la base de Icaro
	
	protected Integer statusCode;
    
	protected String response;
	
	

	/**
	 * 
	 */
	public MessageForm() {
		super();
		
	}
	
	
	
	/**
	 * @param statusCode
	 * @param response
	 */
	public MessageForm(Integer statusCode, String response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}
	
	 
	
	/**
	 * @param externalCode
	 * @param isOk
	 * @param message
	 */
	public MessageForm(Long externalCode, Integer statusCode, String response) {
		super();
		this.externalCode = externalCode;
		this.statusCode = statusCode;
		this.response = response;
	}

	/**
     * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
		this.statusCode         = null;
		this.response      = null;
		this.externalCode = null;
    }



	/**
	 * @return Returns the externalCode.
	 */
	public Long getExternalCode() {
		return externalCode;
	}



	/**
	 * @param externalCode The externalCode to set.
	 */
	public void setExternalCode(Long externalCode) {
		this.externalCode = externalCode;
	}



	/**
	 * @return Returns the response.
	 */
	public String getResponse() {
		return response;
	}



	/**
	 * @param response The response to set.
	 */
	public void setResponse(String response) {
		this.response = response;
	}



	/**
	 * @return Returns the statusCode.
	 */
	public Integer getStatusCode() {
		return statusCode;
	}



	/**
	 * @param statusCode The statusCode to set.
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

    
	
}
