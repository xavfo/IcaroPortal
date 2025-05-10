package com.iportal.biz;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

public abstract class BaseBussinessLogic {

	protected Session openedSession = null;
	
	
	
	/**
	 * 
	 */
	public BaseBussinessLogic() {
		super();
		this.openedSession = null;
	}


	/**
	 * @param openedSession
	 */
	public BaseBussinessLogic(Session openedSession) {
		super();
		this.openedSession = openedSession;
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


	/**
	 * @return Returns the sess.
	 */
	public Session getOpenedSession() {
		return openedSession;
	}


	/**
	 * @param sess The sess to set.
	 */
	public void setOpenedSession(Session sess) {
		this.openedSession = sess;
	}
	
	
}
