/**
 * 
 */
package com.iportal.biz.worldspan;

/**
 * Estructura de JavaBean base para procesar respuestas
 * a consultas XML a worldspan
 * @author hernan
 * @version 1.0
 */
public class WorldspanResponse {
	
	private Integer messageVersion; //MSG_VER
	
	private String errorMessage;
	
	

	public WorldspanResponse() {
		super();
		this.clear();
	}

	public WorldspanResponse(Integer version) {
		super();
		this.messageVersion = version;
	}

	public WorldspanResponse(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public void clear () {
		this.messageVersion = null;
		this.errorMessage   = null;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Integer getMessageVersion() {
		return messageVersion;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setMessageVersion(Integer messageVersion) {
		this.messageVersion = messageVersion;
	}

}
