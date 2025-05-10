/*
 * Created on Apr 19, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yage.struts.plugin;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.hibernate.Session;

import com.iportal.Constants;
import com.iportal.biz.helper.SysParamHelper;
import com.iportal.cart.CartConstants;
import com.iportal.model.system.SysParam;
import com.yage.hibernate.SessionFactoryManager;

/**
 * @author hleon
 *
 * Clase de plugin para cargar constantes de sistema desde
 * un archivo de texto a un bean en memoria principal y
 * a la clase contenedora de constantes
 *  
 */
public class InitParametersPlugIn extends BasePlugIn implements PlugIn {

    public final static String DEFAULT_CONFIG_FILE = "/WEB-INF/init.properties";
    
    public final static String DEFAULT_ID = "initParameters";
    
    private static Log logger = LogFactory.getLog(InitParametersPlugIn.class);
    
    protected ServletContext context;
    
    private Boolean readConfigTable = new Boolean(false);
    
    private Properties initParameters;
    
    /* (non-Javadoc)
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
		    initParameters.clear();
		    initParameters = null;
		    if (this.context!= null && this.putInContext) {
		        context.removeAttribute(this.id);
		        context = null;
		    }
		    this.id = null;
		    this.configFile = null;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
     * 
     */
    @SuppressWarnings("unchecked")

    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
        //TODO algun dia: cambiar por el plugin usado en MCPSummit y 
    	//hacerlo mas generico para poder independizar las 
    	//constantes por modulos y poder recargarlas sin reiniciar la aplicacion
    	this.context = servlet.getServletContext();
		FileInputStream file = null;
		Session sess = null;
		try {
			
			String path = context.getRealPath(configFile);
            file = new FileInputStream(path);

            initParameters = new Properties();
            initParameters.load(file);
			
			Constants constants = new Constants ();
            CartConstants cartConstants = new CartConstants ();
			
			// Cargar más parametros de la tabla de parametros si esta configurado 
			if(true == this.readConfigTable.booleanValue() ) {
				sess = SessionFactoryManager.getSession(HibernatePlugIn.DEFAULT_ID);
				List<SysParam> params = sess.createQuery("from SysParam p order by p.name ").list();
				
				initParameters = SysParamHelper.addToProperties(params, initParameters);
				
			}
			
			BeanUtils.populate(constants, initParameters);
            BeanUtils.populate(cartConstants, initParameters);
			
			logger.info("InitParametersPlugIn initilized.....");
			
			if (this.putInContext) {
                context.setAttribute(id, initParameters);
			}
			
			/*if (this.putInContext) {
				initParameters = InitParameterUtils.initializeGlobalParameters(configFile, id, context);	
				
			} else {
				initParameters = InitParameterUtils.initializeGlobalParameters(configFile, id, null);
			}*/
			
		} catch (Exception e) {
			context.log(e.getMessage());
			logger.error(e.getMessage(), e);
			throw (new ServletException(e));
		} finally {
			if (sess != null) {
				sess.clear();
				sess.close();
			}
			if (file != null)
				try {
					file.close();
				} catch(Exception e) {
				}
		}
        
    }

	public String getReadConfigTable() {
		return readConfigTable.toString();
	}

	public void setReadConfigTable(String configTable) {
		try {
			this.readConfigTable = new Boolean(configTable);
		} catch (Exception e) {
			this.readConfigTable = new Boolean(false);
		}
	}

}
