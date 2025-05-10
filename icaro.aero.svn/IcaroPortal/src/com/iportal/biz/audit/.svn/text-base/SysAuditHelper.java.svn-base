/*
 * Created on May 9, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.biz.audit;

import java.lang.reflect.InvocationTargetException;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iportal.Constants;
import com.iportal.biz.BaseHelper;
import com.iportal.model.system.SysAudit;
import com.iportal.model.system.SysLog;
import com.iportal.model.system.SysUser;

/**
 * @author hleon
 * @version 1.0
 *
 * Helper para almacenar los registros de auditoría de 
 * toda modificación a los datos persistentes de la aplicación.
 * 
 * 
 * @see com.iportal.biz.BaseHelper
 * @see com.iportal.biz.audit.SysWebServiceAuditHelper
 */
public class SysAuditHelper extends BaseHelper {

	private static Log logger = LogFactory.getLog(SysAuditHelper.class);
    
	public static synchronized void openLog(SysUser sysUser)  throws HibernateException  {
        
        Session sess = BaseHelper.getHibernateSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            SysLog log = new SysLog();
            log.setCode(null);
            log.setUserCode(sysUser.getCode());
            log.setUserName(sysUser.getUserName());
            log.setRoleName(sysUser.getRole().getName());
            log.setRolCode(sysUser.getRole().getCode());
            log.setLoginDate(new GregorianCalendar());
            sess.save(log);
            tx.commit();
            // el usuario en sesión tiene una referencia del registro de log correspondiente
            sysUser.setLog(log);

        }catch (Exception e){
            if (tx != null){
                tx.rollback();
            }
            logger.error(e.getMessage(), e);
        }finally {
            if (sess != null){
                sess.close();
            }
        }
    }

    public static synchronized void closeLog(SysLog log)  throws HibernateException  {

        Session sess = BaseHelper.getHibernateSession();
        Transaction tx = null;
        try {
           tx = sess.beginTransaction();
           log.setLogoutDate(new GregorianCalendar());
           sess.save(log);
           tx.commit();
           
        } catch (Exception e){
           if (tx != null){
               tx.rollback();
           }
           logger.error(e.getMessage(), e);
        } finally {
           if (sess != null){
               sess.close();
           }
        }
      }
    
	/**
	 * 
	 * Guarda registros de auditoría de toda acción de modificación
	 * efectuada a objetos persistentes de la aplicación.
	 * 
	 * @param module		Instancia de una acción o componente que altera el contenido de una clase persistente
	 * @param request		Petición HTTP que se esta procesando
	 * @param persistent	Objeto persistente que esta siendo modificado
	 * @param action		La acción que se está ejecutando en el objeto persistente (guardar, borrar, actualizar)
	 */
    public static synchronized void audit(Action module, HttpServletRequest request, Object persistent, 
            String objectName,  String action) {
		
		Session sess = null;
		Transaction tx = null;
		
		try {
			sess = getHibernateSession();
			tx = sess.beginTransaction();
			audit (module, request, persistent, objectName, action, sess);
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
	 * Logs every action performed on a persistent object.
	 * Este metodo logea usando la misma transaccion que el proceso
	 * que ejecuta la accion
	 * @see com.iportal.model.system.SysAudit 
	 * 
	 * @param module		An action instance 
	 * @param request		The HTTP request we are processing
	 * @param persistent	A persistent object
	 * @param action		The action performed by the user
	 * @param sess		    Active Hibernate Session
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws HibernateException 
	 */	
	public static synchronized void audit(Action module, HttpServletRequest request, Object persistent, 
            String objectName, String action, Session sess) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, HibernateException  {
		
		
		// Look for the user in the Session Context
		HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(Constants.SYSTEM_USER_KEY);
        
		if ( user != null ) {
			
				Long code = (Long) PropertyUtils.getSimpleProperty(persistent, "code");
				SysAudit audit = new SysAudit();
		
				audit.setDate( new GregorianCalendar() );
                
				String className = persistent.getClass().getName();
				int index = className.indexOf("$");
				if (index > -1)
					audit.setClassName( className.substring(0,index) );
				else
					audit.setClassName( persistent.getClass().getName() );
				audit.setEntity( code );
				audit.setAction( action );
				audit.setModule( module.getClass().getName() );
				audit.setMethod( request.getMethod() );
				audit.setUri( request.getRequestURI() );
                if (request.getRequestURL()!= null){
                    audit.setUrl( request.getRequestURL().toString() );
                }
				audit.setRemoteAddress( request.getRemoteAddr() );
				audit.setRemoteHost( request.getRemoteHost() );
				audit.setLog(user.getLog());
                audit.setEntityName(objectName);
				sess.save( audit );
			
		}
	}

	
}
