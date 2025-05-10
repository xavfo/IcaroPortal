/*
 * Copyright � Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverr�a
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.tiles.ComponentContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.yage.Globals;
import com.yage.struts.action.BaseTilesAction;

/**
 * 
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class SysModuleTilesAction extends BaseTilesAction {

    
    private static Log logger = LogFactory.getLog(SysModuleTilesAction.class);
    
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;
        
        List parentList = null;
        List accessModeList = null;
        
        try {
            sess = getHibernateSession();
            
            query = sess.createQuery("from SysModule as sysModule where sysModule.group = ? order by sysModule.name");
            query.setBoolean(0, true);
    		query.setCacheable(true);
    		parentList = query.list();
    		
    		query = sess.createQuery("from SysAccessMode as sysAccessMode WHERE sysAccessMode.enabled = ? order by sysAccessMode.name");
    		query.setBoolean(0, Globals.TRUE);
    		query.setCacheable(true);
    		accessModeList = query.list();
    		
    		
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (sess != null)
				try {
					sess.clear();
					sess.close();
				} catch (Exception e) {
				}
		}
		
		//Save the Lists in request scope
		request.setAttribute("sysModuleParentList", parentList);
		request.setAttribute("sysAccessModeList", accessModeList);

		return null;
    }

}
