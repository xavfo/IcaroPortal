/*
 * Created Jun 14, 2006
 *	FileExtensionPlugIn.java
 */
package com.yage.struts.plugin;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import com.yage.file.FileExtensionUtils;

/**
 * @author hernan
 *
 */
public class FileExtensionPlugIn extends BasePlugIn implements PlugIn {

    public static String DEFAULT_FILE_FOLDER = "/images/icons/docs";
    
    public static String DEFAULT_ID = "iconFileExtensions";

    private static Log logger = LogFactory.getLog(InitParametersPlugIn.class);
    
    protected ServletContext context;
    
    protected String extentionsFolder;
    
    
    /* (non-Javadoc)
     * @see org.apache.struts.action.PlugIn#destroy()
     */
    public void destroy() {
    	Properties extensions = FileExtensionUtils.getFileExtentions();
    	extensions.clear();
    	extensions = null;
	    if (this.context!= null && this.putInContext) {
	        context.removeAttribute(this.id);
	        context = null;
	    }
		this.id = null;
		this.extentionsFolder = null;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.PlugIn#init(org.apache.struts.action.ActionServlet, org.apache.struts.config.ModuleConfig)
     */
    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
		this.context = servlet.getServletContext();
		
		
		try {
			
			if (this.putInContext) {
				FileExtensionUtils.loadFileExtentionsAvailable(this.extentionsFolder, this.id, context);
			} else {
				FileExtensionUtils.loadFileExtentionsAvailable(this.extentionsFolder, this.id, context);
			}
			DEFAULT_FILE_FOLDER = this.extentionsFolder;
			DEFAULT_ID = this.id;
			
		} catch (Exception e) {
			context.log(e.getMessage());
			logger.error(e.getMessage(), e);
			throw (new ServletException(e));
		} finally {
		}
        
    }

	/**
	 * @return Returns the extentionsFolder.
	 */
	public String getExtentionsFolder() {
		return extentionsFolder;
	}

	/**
	 * @param extentionsFolder The extentionsFolder to set.
	 */
	public void setExtentionsFolder(String extentionsFolder) {
		this.extentionsFolder = extentionsFolder;
	}

    
}
