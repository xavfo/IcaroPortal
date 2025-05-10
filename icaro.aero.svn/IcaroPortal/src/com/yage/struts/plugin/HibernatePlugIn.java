/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.plugin;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.util.PropertiesHelper;

import com.yage.hibernate.SessionFactoryContainer;
import com.yage.hibernate.SessionFactoryManager;

/**
 *  HibernatePlugIn utilizado para levantar conexiones a la base de datos
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class HibernatePlugIn extends BasePlugIn implements PlugIn {

    public final static String DEFAULT_CONFIG_FILE = "/WEB-INF/hibernate-config.xml";
    
    public final static String DEFAULT_ID = "DEFAULT_SESSION_FACTORY";
    
    private static Log logger = LogFactory.getLog(HibernatePlugIn.class);
    
    private SessionFactory sessionFactory;
    
    
    /**
     * Crea una nueva instnacia de HibernatePlugIn.
     */
    public HibernatePlugIn() {
        id = DEFAULT_ID;
        configFile = DEFAULT_CONFIG_FILE;
        putInContext = true;
    }
    
    public void destroy() {
		try {
		    SessionFactoryManager.deregisterDriver(id);
			sessionFactory.close();
		} catch (Exception e) {
		    logger.debug(e.getMessage(), e);
		}
    }

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		
        ServletContext context = servlet.getServletContext();
		
		try {
			String path = context.getRealPath(configFile);
			File file = new File(path);
			Configuration configuration = new AnnotationConfiguration().configure(file);
			//SQLExceptionConverter as = configuration.buildSettings().getDialect().buildSQLExceptionConverter();
			sessionFactory = configuration.buildSessionFactory();
			
			// Adds the new SessionFactory to the SessionFactoryManager
			SessionFactoryManager.registerSessionFactory(id, new SessionFactoryContainer(sessionFactory, configuration.getProperty("dialect"), new Integer(PropertiesHelper.getInt(Environment.STATEMENT_BATCH_SIZE, configuration.getProperties(), 0))));
			
			logger.info("HibernatePlugIn initilized...id: "+id);
			if (putInContext) {
				context.setAttribute(id, sessionFactory);
			}
		} catch (Exception e) {
		    logger.error(e.getMessage(), e);
			throw (new ServletException(e));
		}
    }
}
