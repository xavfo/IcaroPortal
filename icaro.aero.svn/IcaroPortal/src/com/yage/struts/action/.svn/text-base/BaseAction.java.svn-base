/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.iportal.Constants;
import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * BaseAction Accion básica, que implemente metodos genéricos para acciones descendientes
 * como obtener una conexion a base de datos entre otras.
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class BaseAction extends Action {
	
	protected Locale setDefaultLocale( HttpServletRequest request ) {
		Locale current = this.getLocale(request);
		if (!Constants.DEFAULT_LOCALE.equals(current.getLanguage())){
			Locale locale = new Locale (Constants.DEFAULT_LOCALE);
			current = locale;
			this.setLocale(request,locale);
		}
		return current;
	}

    
	/**
	 * Remove the associated action form from the context
	 * 
	 * @param mapping The ActionMapping used to select this instance
	 * @param request The HTTP request we are processing
	 */
	protected void removeForm(
		ActionMapping mapping,
		HttpServletRequest request) {

		// Remove the obsolete form bean
		if (mapping.getAttribute() != null) {
			if ("request".equals(mapping.getScope())) {
				request.removeAttribute(mapping.getAttribute());
				request.removeAttribute(mapping.getName());
			} else {
				request.getSession().removeAttribute(mapping.getAttribute());
				request.getSession().removeAttribute(mapping.getName());
			}
		}
	}


	/**
	 * Gets a hibernate session from the SessionFactoryManager object.
	 * 
	 * @return	A hibernate session
	 * @throws HibernateException
	 */
	protected Session getHibernateSession() throws HibernateException {
		return getHibernateSession(HibernatePlugIn.DEFAULT_ID);
	}


	/**
	 * Gets a hibernate session from the SessionFactoryManager object.
	 * 
	 * @param id	The attribute under wich the SessionFactory object is stored
	 * 				in the application scope.
	 * @return		A hibernate session
	 * @throws HibernateException
	 */
	protected Session getHibernateSession(String id) throws HibernateException {

		return SessionFactoryManager.getSession(id);
	}
}
