/*
 * Created on May 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package aero.icaro.service;

import java.math.BigInteger;

/** 
 * Objeto padre de toda respuesta producida por una 
 * llamada a servicios Web de Fybeca
 * 
 * @author hleon
 * @version 1.0
 *
 */
public class IcaroServiceMessageBean {

	private BigInteger statusCode;
    	    
	private String response;
    
    

	/**
	 * Crea un nuevo MessageBean con todas las propiedades vacías.
	 */
	public IcaroServiceMessageBean() {
		super();
		this.clear();
	}
	
	
	
	/**
	 * Crea un nuevo MessageBean con todas las propiedades
	 * cargadas con los valores indicados en los parámetros 
	 * de este constructor.
	 * @param isOk indicador de que la llamada al servicio Web se ejecutó exitosamente
	 * @param message mensaje del sistema hacia el usuario
	 */
	public IcaroServiceMessageBean(BigInteger statusCode, String response) {
		super();
		this.statusCode = statusCode;
		this.response = response;
	}
	
	/** 
	 * Borra todas las propiedades del MessageBean o componente. 
	 */
	public void clear () {
		statusCode = null;		
		response = null;
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
	public BigInteger getStatusCode() {
		return statusCode;
	}



	/**
	 * @param statusCode The statusCode to set.
	 */
	public void setStatusCode(BigInteger statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
