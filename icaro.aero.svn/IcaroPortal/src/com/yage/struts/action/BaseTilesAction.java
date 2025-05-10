/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import org.apache.struts.tiles.actions.TilesAction;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class BaseTilesAction extends TilesAction {

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
