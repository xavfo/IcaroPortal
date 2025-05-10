/*
 * Created on May 25, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.struts.plugin;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.yage.servlet.http.security.UserInventory;

/**
 * 
 * PlugIn para inicializar el inventario de usuarios conectados al sistema
 * 
 * @author hleon
 * @version 1.0
 *
 *  
 */
public class UserInventoryPlugIn extends BasePlugIn implements PlugIn {

	public final static String DEFAULT_ID = "DEFAULT_USER_INVENTORY";
    
    private static Log logger = LogFactory.getLog(UserInventoryPlugIn.class);
    
    private ServletContext context;
    
	/**
	 * 
	 */
	public UserInventoryPlugIn() {
		super();
		this.id = DEFAULT_ID;
		this.context = null;
	}
	
    public void destroy() {
        
    }
    

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		
        if (context == null) {
        	this.context = servlet.getServletContext();
        }
		
		try {
	        // Crea el manejador de pools
	        UserInventory inventory = new UserInventory();
	        this.context.setAttribute(this.id, inventory);
	        
			logger.info("User inventory PlugIn initilized....id:" + id);
		} catch (Exception e) {
			context.log(e.getMessage());
			logger.error(e.getMessage(), e);
			throw (new ServletException(e));
		}

    }
	
}
