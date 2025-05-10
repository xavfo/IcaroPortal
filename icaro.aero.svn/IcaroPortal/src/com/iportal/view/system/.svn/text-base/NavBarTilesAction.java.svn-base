/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;

import com.iportal.biz.NavBarConfig;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author YAGE (mcnovillo)
 * @version 1.0
 *
 */
public class NavBarTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(NavBarTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
    	NavBarConfig navBarConfig = null;
        try {
        	navBarConfig = new NavBarConfig();
            
        	navBarConfig.setFormIndex(new Integer(context.getAttribute("formIndex").toString()));
            navBarConfig.setAdd(new Boolean(context.getAttribute("add").toString()));
            navBarConfig.setApply(new Boolean(context.getAttribute("apply").toString()));
            navBarConfig.setCancel(new Boolean(context.getAttribute("cancel").toString()));
            navBarConfig.setDelete(new Boolean(context.getAttribute("delete").toString()));
            navBarConfig.setEdit(new Boolean(context.getAttribute("edit").toString()));
            navBarConfig.setPreview(new Boolean(context.getAttribute("preview").toString()));
            navBarConfig.setReset(new Boolean(context.getAttribute("reset").toString()));
            navBarConfig.setSave(new Boolean(context.getAttribute("save").toString()));
            if (context.getAttribute("assign") != null){
                navBarConfig.setAssign(new Boolean(context.getAttribute("assign").toString()));
            }
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
		}
		
		//Save the Lists in request scope
		request.setAttribute("navBarConfig", navBarConfig);
		return null;
    }

}
