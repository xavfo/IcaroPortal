/*
 * Created on Aug 19, 2005
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iportal.view.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
 * Tiles para Log de Pagina (<code>PageLog</code>).<BR>
 * Carga los typos y descripciones de los recursos utilizados el Sitio.
 * 
 * @author YAGE
 * @version 1.0
 * @see com.yage.struts.action.BaseTilesAction
 */
public class PageLogTilesAction extends BaseTilesAction {

    private static Log logger = LogFactory.getLog(PageLogTilesAction.class);

    public ActionForward execute(
            ComponentContext context, 
            ActionMapping mapping,
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response)
    throws Exception {
        
        Session sess = null;
        Query query = null;

        List descriptionList  = null;
        List typeList  = null;
        
        try {
            
        	sess = getHibernateSession();
            HttpSession session = request.getSession();

          	StringBuffer sql = new StringBuffer ();
          	sql.append("select new com.iportal.biz.RowItem (pageLog.resourceType, pageLog.resourceDescription) ");
          	sql.append("from PageLog as pageLog ");
            sql.append("order by pageLog.resourceDescription");

            query = sess.createQuery(sql.toString());
       		query.setCacheable(true);
       		
       		descriptionList = query.list();	
       		
       		sql = new StringBuffer ();
       		sql.append("select distinct new com.iportal.biz.RowItem (pageLog.resourceType, pageLog.resourceType) ");
          	sql.append("from PageLog as pageLog ");
            sql.append("order by pageLog.resourceType");
            
            query = sess.createQuery(sql.toString());
    		query.setCacheable(true);

    		typeList = query.list();
            
       		
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
		request.setAttribute("descriptionList", descriptionList);
		request.setAttribute("typeList", typeList);
		return null;
    }
}