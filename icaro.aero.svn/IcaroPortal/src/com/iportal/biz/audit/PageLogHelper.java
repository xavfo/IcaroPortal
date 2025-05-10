/*
 * Created Aug 7, 2006
 *	PageLogHelper.java
 */
package com.iportal.biz.audit;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.biz.BaseHelper;
import com.iportal.model.PageLog;

/**
 * @author hernan
 *
 */
public class PageLogHelper extends BaseHelper {

	private static Log logger = LogFactory.getLog(PageLogHelper.class);
	
	/**
	 * 
	 */
	public PageLogHelper() {
		super();
	}
	
	
	
	/**
	 * Guarda registros de accesos a una secci�n, charla o articulo.
	 * 
	 * @param module		Instancia de una acci�n o componente que altera el contenido de una clase persistente
	 * @param request		Petici�n HTTP que se esta procesando
	 * @param className	    Nombre de la clase del Objeto
	 * @param Code			Codigo del objeto
	 * @param descripcion	Descripci�n del objeto (nombre, titulo, etc)
	 */
	public static synchronized void log(
	    HttpServletRequest request,
		String className, Long code, String description) {
			
		Session sess = null;
		Transaction tx = null;
			
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			log (request, className, code, description, sess);
			tx.commit();
				
		} catch (Exception e) {
			if (tx != null)
				try { 
					tx.rollback();
				} catch (Exception ex) {
				}
		
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.close();
				} catch (Exception ex) {
				}
		}
	}
	
	/**
	 * Registra cada acci�n realizada en un objeto persistente.
	 * 
	 * @param module		An action instance 
	 * @param request		The HTTP request we are processing
	 * @param className	    Class Name of a persistent object
	 * @param description	description of a persistent object
	 * @param sess		    Active Hibernate Session
	 * @see com.iportal.model.system.SysAudit 
	 */
	public static synchronized void log(
		HttpServletRequest request, 
		String className, Long code, 
		String description, Session sess) throws Exception {

		PageLog log = new PageLog();
		
		log.setDate( new GregorianCalendar() );
		log.setLanguage(request.getHeader("accept-language"));
		log.setReferrer(request.getHeader("referer"));
		log.setRemoteAddress(request.getRemoteAddr());
		log.setRemoteHost(request.getRemoteHost());
		log.setResourceCode(code);
		log.setResourceDescription(description);
		log.setResourceType(className);
        // BV 2007-04-24: The first request can be without session, so RequestedSessoniId 
        // will be null, in this case save the new SessionId
		log.setSessionId(null==request.getRequestedSessionId()?request.getSession().getId():request.getRequestedSessionId());
		log.setUrl(request.getRequestURL().toString());
		log.setUserAgent(request.getHeader("user-agent"));

		sess.save(log);
	}
	

}
