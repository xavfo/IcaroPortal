/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
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

import com.yage.struts.action.BaseTilesAction;

/**
 * Tiles para Auditoria del Sitio (<code>SysAudit</code>).<BR>
 * Carga todos los nombres de las clases del sitio. 
 * 
 * @author  YAGE 
 * @version 1.0
 * @see com.yage.struts.action.BaseTilesAction
 */
public class SysAuditTilesAction extends BaseTilesAction {
    
    private static Log logger = LogFactory.getLog(SysAuditTilesAction.class);
    
    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;

        List classList  = null;
        
        try {
            
        	sess = getHibernateSession();

          	StringBuffer sql = new StringBuffer ();
          	sql.append("select distinct new com.iportal.biz.RowItem (sysAudit.className) ");
          	sql.append("from SysAudit as sysAudit ");
            sql.append("order by sysAudit.className");

            query = sess.createQuery(sql.toString());
       		query.setCacheable(true);
       		
       		classList = query.list();	
       		
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
		request.setAttribute("classList", classList);
		return null;
    }
}