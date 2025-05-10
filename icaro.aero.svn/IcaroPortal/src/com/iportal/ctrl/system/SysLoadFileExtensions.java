/*
 * Created Jun 15, 2006
 *	SysLoadFileExtensions.java
 */
package com.iportal.ctrl.system;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.yage.Globals;
import com.yage.file.FileExtensionUtils;
import com.yage.struts.action.BaseAction;
import com.yage.struts.plugin.FileExtensionPlugIn;

/**
 * @author hernan
 *
 */
public class SysLoadFileExtensions extends BaseAction {

    private static Log logger = LogFactory.getLog(SysLoadFileExtensions.class);
    
    
    
    public ActionForward execute(
            ActionMapping mapping, 
            ActionForm form,
            HttpServletRequest request, 
            HttpServletResponse response) 
    throws Exception {
        
        
        String folder = FileExtensionPlugIn.DEFAULT_FILE_FOLDER;
        String id = FileExtensionPlugIn.DEFAULT_ID;
        
        ActionMessages messages = new ActionMessages();
        ServletContext context  = getServlet().getServletContext();        
        try {
        	FileExtensionUtils.loadFileExtentionsAvailable(folder, id, context);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("message.failure.msg", e.getMessage()));
			return mapping.findForward(Globals.FORWARD_FAILURE);
		}
		
		messages.add(Globals.MESSAGE_SUCCESS, new ActionMessage("message.success"));        
		return mapping.findForward(Globals.FORWARD_SUCCESS);
    }

}
