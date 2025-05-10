/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.hibernate;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * comment SessionFactoryManager
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class SessionFactoryManager {
    
    private static Log logger = LogFactory.getLog(SessionFactoryManager.class);

    private static Hashtable<String, SessionFactoryContainer> sessions = new Hashtable<String, SessionFactoryContainer>();
    
    //  Prevent the SessionFactoryManager class from being instantiated.
    private SessionFactoryManager() {
    }
    
    public static synchronized Session getSession(String id) {
    	SessionFactoryContainer sfc = null;
    	SessionFactory sf = null;
        Session sess = null;
        sfc = (SessionFactoryContainer)sessions.get(id);
        sf = sfc.getSessionFactory();
        if ( sf == null ) {
            return null;
        }
        try {
            sess = sf.openSession();
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return sess;
    }
    
    
	public static synchronized void registerSessionFactory(String id, SessionFactoryContainer sfc) {
    	sessions.put(id, sfc);
    }
    
    public static synchronized void deregisterDriver(String id) {
        sessions.remove(id);
    }
    
    public static synchronized SessionFactory getSessionFactory(String id) {
    	SessionFactoryContainer sfc = (SessionFactoryContainer) sessions.get(id);
    	return sfc.getSessionFactory();
    }
    
    public static synchronized Hashtable getSessionFactorys() {
        return sessions;
    }
    
    public static synchronized String getDialectClassName(String id) {
    	SessionFactoryContainer sfc = (SessionFactoryContainer) sessions.get(id);
    	return sfc.getDialectClassName();
    }
    public static synchronized Integer getJdbcBatchSize(String id) {
    	SessionFactoryContainer sfc = (SessionFactoryContainer) sessions.get(id);
    	return sfc.getJdbcBatchSize();
    }

}
