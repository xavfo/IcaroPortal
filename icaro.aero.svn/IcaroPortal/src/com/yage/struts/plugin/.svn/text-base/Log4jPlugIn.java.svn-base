/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.struts.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

/**
 * 
 * 
 * @author YAGE (jtite)
 * @version 1.0
 *
 */
public class Log4jPlugIn extends BasePlugIn implements PlugIn {

    public final static String DEFAULT_CONFIG_FILE = "/WEB-INF/log4j-config.xml";
    
    public final static String DEFAULT_ID = "DEFAULT_LOG_FACTORY";
    
    private static Log logger = LogFactory.getLog(Log4jPlugIn.class);

    
    
    /**
     * Crea una nueva instancia de Log4jPlugIn
     */
    public Log4jPlugIn() {
        configFile = DEFAULT_CONFIG_FILE;
    }
    
    
    
    public void destroy() {
        LogManager.shutdown();
    }

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		
        ServletContext context = servlet.getServletContext();
		
		try {
			String path = context.getRealPath(configFile);
			DOMConfigurator.configure(path);
			
			logger.info("Lo4J initilized");
		} catch (Exception e) {
			context.log(e.getMessage());
			throw (new ServletException(e));
		}

    }
}
