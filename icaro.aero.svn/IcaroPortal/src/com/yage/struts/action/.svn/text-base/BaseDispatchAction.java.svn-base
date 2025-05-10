/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.yage.hibernate.SessionFactoryManager;
import com.yage.struts.plugin.HibernatePlugIn;

/**
 * java comment BaseDispatchAction
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public abstract class BaseDispatchAction extends DispatchAction {

	/**
	 * Metodo que interpretada (parsea) los errores de SQL para mostrar al cliente
	 * final mensajes amigables.<br/>
	 * Devuelve true si se ha añadido un mensaje de error personalizado, para evitar colocar mas errores en pantalla. false si no se 
	 * ha colocado ningun error
	 * 
	 * @param e Excepción ocurrida que va a ser interpretada
	 * @param messages mensajes de error para la pantalla del usuario final
	 * @param objectName nombre de la clase persistente en la cual ha ocurrido un error
	 * @param descripcion codigo o dato descriptivo del registro donde ocurrio el error.
	 * @return true si se ha añadido errores a la lista de errores, false si no se a añadido ningun error a la lista
	 */
	public boolean checkControlledError(Exception e, ActionMessages messages, String objectName, String descripcion, String sessionId) {
		boolean resp = false;
		if (e.getCause() != null && e.getCause() instanceof SQLException) {
			SQLException se = (SQLException) e.getCause();
			String dialectName =(sessionId != null && sessionId.length()>0)? this.getDialectClassName(sessionId):this.getDialectClassName();
			int errorCode = se.getErrorCode();
			
			if("org.hibernate.dialect.MySQLDialect".equals(dialectName) || "org.hibernate.dialect.MySQLInnoDBDialect".equals(dialectName)) {
				switch (errorCode) {
				case 1217: case 1216: // Cannot delete or update a parent row: a foreign key constraint fails
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.foreignKey", descripcion, objectName));
					resp = true;
					break;
				case 1062:
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.uniqueKey", descripcion, objectName, se.getMessage()));
					resp = true;
					break;
				/*case 1062: // Duplicate entry '4' f
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.duplicateKey", code, objectName));		
					break;*/
				default:
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.generic", se.getMessage(), Integer.valueOf(se.getErrorCode())));
					resp = true;
					break;
				}
			}
			if("org.hibernate.dialect.OracleDialect".equals(dialectName)) {
				switch (errorCode) {
				case 1407:  // Cannot delete or update a parent row: a foreign key constraint fails
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.foreignKey", descripcion, objectName));
					resp = true;
					break;
				case 2292:  // Cannot delete or update a parent row: a foreign key constraint fails
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.foreignKey", descripcion, objectName));
					resp = true;
					break;
				case 1062:
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.uniqueKey", descripcion, objectName, se.getMessage()));
					resp = true;
					break;
				/*case 1062: // Duplicate entry '4' f
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.duplicateKey", code, objectName));		
					break;*/
				default:
					messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.sql.generic", se.getMessage(), Integer.valueOf(se.getErrorCode())));
					resp = true;
					break;
				}
				
			}
			
			return resp;
			
			
		}
		return false;
	
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

	protected String getDialectClassName() throws HibernateException {
		return getDialectClassName(HibernatePlugIn.DEFAULT_ID);
	}

	protected String getDialectClassName(String id) throws HibernateException {
		return SessionFactoryManager.getDialectClassName(id);
	}

	protected Integer getJdbcBatchSize() throws HibernateException {
		return getJdbcBatchSize(HibernatePlugIn.DEFAULT_ID);
	}

	protected Integer getJdbcBatchSize(String id) throws HibernateException {
		return SessionFactoryManager.getJdbcBatchSize(id);
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
	 * Gets a hibernate session from the SessionFactoryManager object.
	 * 
	 * @param id	The attribute under wich the SessionFactory object is stored
	 * 				in the application scope.
	 * @return		A hibernate session
	 * @throws HibernateException
	 */
	
	protected Object loadData(Class classType, Long code, Session sess) throws Exception {
		
		Object loadObject  = sess.load(classType, code);

		if (!Hibernate.isInitialized(loadObject))
				Hibernate.initialize(loadObject);
		return loadObject;
	}
}
