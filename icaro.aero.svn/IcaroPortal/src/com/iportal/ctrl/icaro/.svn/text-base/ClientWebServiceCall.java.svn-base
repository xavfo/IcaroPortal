/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.ctrl.icaro;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

/**
 * @author hleon
 * @version 1.0
 *
 *Interfaz para acciones que invocan a servicios Web Remotos
 */
public interface ClientWebServiceCall {

	 
	/**
	 * Inicializa el componente para llamar a un Web Service e inicualiza 
	 * el request que lo llama para poder almacenar los registros de auditoría
	 * de las llamadas a los Web Services
	 *  
	 * @param request Peticion que solicita la ejecución de un Web Service
	 * @throws ServiceException en caso de que ocurra un error al instanciar al localizador del servicio Web
	 */
	public void initManagerSkies ( HttpServletRequest request) throws ServiceException;
	public void initManagerCorp ( HttpServletRequest request) throws ServiceException;
	public void initManagerAgency ( HttpServletRequest request) throws ServiceException;
}
